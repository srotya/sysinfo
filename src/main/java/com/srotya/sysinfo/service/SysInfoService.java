package com.srotya.sysinfo.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.FINEST);
		Logger.getAnonymousLogger().addHandler(consoleHandler);
		
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
