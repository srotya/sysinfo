/*
 * Copyright 2015 Ambud Sharma
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 * You may obtain a copy of the License at
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.srotya.sysinfo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.srotya.sysinfo.dao.metrics.CPUUsage;
import com.srotya.sysinfo.dao.metrics.ProcessorUsage;

/**
 * @author ambudsharma
 *
 */
public class CPUMon extends AbstractMon {

	private static final Logger logger = Logger.getLogger(CPUMon.class.getName());
	public static final String CPU_STATS = "/proc/stat";
	private AtomicReference<CPUUsage> usage;
	
	public CPUMon(AtomicBoolean loopControl, AtomicInteger sleepTime) {
		super(loopControl, sleepTime);
		this.usage = new AtomicReference<CPUUsage>(null);
	}
	
	@Override
	public void monitor() {
		try{
			CPUUsage temp = getCPUUsage();
			if(temp==null) {
				// do nothing, just continue and wait for the sleep time to retry 
			}else if(usage==null) {
				usage.set(temp);
			}else {
				computeCPUPercentage(usage.get(), temp);
				usage.set(temp);
			}
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Failed to get CPU Usage", e);
		}
	}
	
	public CPUUsage getCPUUsage() throws IOException {
		return getCPUUsage(CPU_STATS);
	}
	
	public static CPUUsage getCPUUsage(String statFile) throws IOException {
		List<String> stats = Util.readTextFileAsList(statFile);
		String cpuUsage = stats.get(0);
		CPUUsage usage = new CPUUsage();
		parseAndGetCPUUsage(cpuUsage, usage);
		
		// get individual CPUs and Cores
		List<ProcessorUsage> procs = new ArrayList<ProcessorUsage>();
		int i = 1;
		while((cpuUsage=stats.get(i)).startsWith("cpu")) {
			ProcessorUsage procUsage = new ProcessorUsage();
			parseAndGetCPUUsage(cpuUsage, procUsage);
			procs.add(procUsage);
			i++;
		}
		usage.setProcessors(procs.toArray(new ProcessorUsage[1]));
		return usage;
	}
	
	/**
	 * 		user    nice   system  idle      iowait irq   softirq  steal  guest  guest_nice
	 * cpu  74608   2520   24433   1117073   6176   4054  0        0      0      0
	 * 
	 * @param procLine
	 * @param usage
	 */
	public static void parseAndGetCPUUsage(String procLine, ProcessorUsage usage) {
		String[] splits = procLine.split("\\s+");
		// ignore zero
		usage.setUser(Long.parseLong(splits[1]));
		usage.setNice(Long.parseLong(splits[2]));
		usage.setSystem(Long.parseLong(splits[3]));
		usage.setIdle(Long.parseLong(splits[4]));
		usage.setIowait(Long.parseLong(splits[5]));
		usage.setIrq(Long.parseLong(splits[6]));
		usage.setSoftirq(Long.parseLong(splits[7]));
		if(splits.length>8)
			usage.setSteal(Long.parseLong(splits[8]));
	}
	
	/**
	 * 
	 * 
	 * @param currUsage
	 * @return
	 */
	public static void computeCPUPercentage(CPUUsage prevUsage, CPUUsage currUsage) {
		computeProcessorPercentage(prevUsage, currUsage);
		if(currUsage.getProcessors().length!=prevUsage.getProcessors().length) {
			// CPU count has changed, can't compute percentage for individual cores
			// This should not happen for most cases but modern virtualization systems allow hot plug
			logger.warning("CPU count has changed, can't compute percentage for individual cores");
		}else {
			for(int i=0;i<currUsage.getProcessors().length;i++) {
				computeProcessorPercentage(prevUsage.getProcessors()[i], currUsage.getProcessors()[i]);
			}
		}
	}
	
	/**
	 * Source: http://stackoverflow.com/questions/23367857/accurate-calculation-of-cpu-usage-given-in-percentage-in-linux
	 * @param prev
	 * @param curr
	 */
	public static void computeProcessorPercentage(ProcessorUsage prev, ProcessorUsage curr) {
		long prevIdle = prev.getIdle() + prev.getIowait();
		long currIdle = curr.getIdle() + curr.getIowait();
		
		long prevNonIdle = prev.getUser() + prev.getNice() + prev.getSystem() + prev.getIrq() + prev.getSoftirq() + prev.getSteal();
		long currNonIdle = curr.getUser() + curr.getNice() + curr.getSystem() + curr.getIrq() + curr.getSoftirq() + curr.getSteal();
		
		long prevTotal = prevIdle + prevNonIdle;
		long currTotal = currIdle + currNonIdle;
		
		long totalDelta = currTotal - prevTotal;
		long idleDelta = currIdle - prevIdle;
		
		double percentage = ((double)(totalDelta - idleDelta))/totalDelta;
		
		curr.setCurrentPercentage(percentage*100);
	}

	public CPUUsage getUsage() {
		return usage.get();
	}

	public Logger getLogger() {
		return logger;
	}
}
