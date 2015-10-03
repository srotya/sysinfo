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
	
	private long ts;
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

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

}
