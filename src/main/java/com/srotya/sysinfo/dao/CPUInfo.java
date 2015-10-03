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
