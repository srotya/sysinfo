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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.srotya.sysinfo.dao.metrics.CPUUsage;

/**
 * Cuurent entry point of Sysinfo. 
 * 
 * @author ambudsharma
 *
 */
public class SysInfoService {

	public static void main(String[] args) {
		String osName = System.getProperty("os.name");
		if(!osName.toLowerCase().contains("linux")) {
			System.err.println("Sysinfo currently supports Linux based operating systems only.");
			System.exit(-1);
		}
		
		final AtomicBoolean loopControl = new AtomicBoolean(true);
		AtomicInteger sleepTime = new AtomicInteger(500);
		
		CPUMon cpuMon = new CPUMon(loopControl, sleepTime);
		final ExecutorService es = Executors.newFixedThreadPool(4);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			
			@Override
			public void run() {
				loopControl.set(false);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { }
				es.shutdownNow();
			}
			
		});
		
		// CPU monitoring task
		es.submit(cpuMon);
		
		// shouldn't be accepting any new tasks
		es.shutdown();
		
		while(loopControl.get()) {
			CPUUsage usage = cpuMon.getUsage();
			if(usage!=null)
				System.out.println("CPU Usage:"+cpuMon.getUsage().getCurrentPercentage()+"% core1:"+usage.getProcessors()[0].getCurrentPercentage()+" core2:"+usage.getProcessors()[1].getCurrentPercentage());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
		}
	}
	
}
