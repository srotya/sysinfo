package com.srotya.sysinfo.dao;

import java.io.Serializable;

public class CPUInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProcessorInfo[] cpus;
	
	public CPUInfo() {
	}

	public ProcessorInfo[] getCpus() {
		return cpus;
	}

	public void setCpus(ProcessorInfo[] cpus) {
		this.cpus = cpus;
	}
	
}
