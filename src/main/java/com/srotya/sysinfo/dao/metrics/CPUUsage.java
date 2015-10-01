package com.srotya.sysinfo.dao.metrics;

import java.io.Serializable;

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
