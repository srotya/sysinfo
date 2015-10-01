package com.srotya.sysinfo.dao;

import java.io.Serializable;

/**
 * CPU Info is essentially an array of {@link ProcessorInfo}. To be used for pretty JSON
 * 
 * @author ambudsharma
 *
 */
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
