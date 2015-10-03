package com.srotya.sysinfo.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class SysInfoRESTController {
	
	private final AtomicBoolean loopControl = new AtomicBoolean(true);
	private final AtomicInteger sleepTime = new AtomicInteger(500);
	private final ExecutorService es = Executors.newFixedThreadPool(3);
	private static CPUMon cpuMon;
	private static MemMon memMon;
	private static NetMon netMon;
	
	public SysInfoRESTController() {
	}
	
	public void init() {
		cpuMon = new CPUMon(loopControl, sleepTime);
		memMon = new MemMon(loopControl, sleepTime);
		netMon = new NetMon(loopControl, sleepTime);
		
		es.submit(cpuMon);
		es.submit(memMon);
		es.submit(netMon);
	}
	
	public static void main(String[] args) {
		String osName = System.getProperty("os.name");
		if(!osName.toLowerCase().contains("linux")) {
			System.err.println("Sysinfo currently supports Linux based operating systems only.");
			System.exit(-1);
		}
		
		final SysInfoRESTController controller = new SysInfoRESTController();
		// initialize the controller
		controller.init();
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
 
        final Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);
        
        Runtime.getRuntime().addShutdownHook(new Thread(){
			
			@Override
			public void run() {
				controller.getLoopControl().set(false);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { }
				controller.getEs().shutdownNow();
				jettyServer.destroy();
			}
			
		});
 
        ServletHolder jerseyServlet = context.addServlet(
             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.srotya.sysinfo.api, com.fasterxml.jackson.jaxrs.json");
 
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            jettyServer.destroy();
        }
	}

	public AtomicBoolean getLoopControl() {
		return loopControl;
	}

	public AtomicInteger getSleepTime() {
		return sleepTime;
	}

	public ExecutorService getEs() {
		return es;
	}

	public static CPUMon getCpuMon() {
		return cpuMon;
	}

	public static MemMon getMemMon() {
		return memMon;
	}

	public static NetMon getNetMon() {
		return netMon;
	}

}
