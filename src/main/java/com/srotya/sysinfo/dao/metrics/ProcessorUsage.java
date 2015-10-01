package com.srotya.sysinfo.dao.metrics;

import java.io.Serializable;

/**
 * Pojo to capture Processor and {@link CPUUsage}. Numbers here are measured in USER_HZ (as stated in /proc/stat definition)
 * 
 * @author ambudsharma
 *
 */
public class ProcessorUsage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long user;
	private long nice;
	private long system;
	private long idle;
	private long iowait;
	private long irq;
	private long softirq;
	private double currentPercentage;
	private long steal;
	
	public ProcessorUsage() {
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getNice() {
		return nice;
	}

	public void setNice(long nice) {
		this.nice = nice;
	}

	public long getSystem() {
		return system;
	}

	public void setSystem(long system) {
		this.system = system;
	}

	public long getIdle() {
		return idle;
	}

	public void setIdle(long idle) {
		this.idle = idle;
	}

	public long getIowait() {
		return iowait;
	}

	public void setIowait(long iowait) {
		this.iowait = iowait;
	}

	public long getIrq() {
		return irq;
	}

	public void setIrq(long irq) {
		this.irq = irq;
	}

	public long getSoftirq() {
		return softirq;
	}

	public void setSoftirq(long softirq) {
		this.softirq = softirq;
	}

	public double getCurrentPercentage() {
		return currentPercentage;
	}

	public void setCurrentPercentage(double currentPercentage) {
		this.currentPercentage = currentPercentage;
	}

	public long getSteal() {
		return steal;
	}

	public void setSteal(long steal) {
		this.steal = steal;
	}

	@Override
	public String toString() {
		return "ProcessorUsage [user=" + user + ", nice=" + nice + ", system=" + system + ", idle=" + idle + ", iowait="
				+ iowait + ", irq=" + irq + ", softirq=" + softirq + ", currentPercentage=" + currentPercentage
				+ ", steal=" + steal + "]";
	}

}
