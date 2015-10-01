package com.srotya.sysinfo.dao.metrics;

import java.io.Serializable;

/**
 * Extends from ProcessorUsage to capture the usage for the entire CPU.
 * 
 * CPU in the context of Sysinfo refers to any and all processors that Linux kernel registers in /proc.
 * 
 * This object encapsulates all CPUs but itself contains the averages as contained in /proc
 * 
 * @author ambudsharma
 *
 */
public class CPUUsage extends ProcessorUsage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProcessorUsage[] processors;
	
	public ProcessorUsage[] getProcessors() {
		return processors;
	}

	public void setProcessors(ProcessorUsage[] processors) {
		this.processors = processors;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
