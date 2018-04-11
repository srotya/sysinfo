/**
 * Copyright 2017 Ambud Sharma
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.srotya.sysinfo.agent;

import org.apache.http.impl.client.CloseableHttpClient;

import com.srotya.sysinfo.dao.metrics.CPUUsage;
import com.srotya.sysinfo.dao.metrics.ProcessorUsage;
import com.srotya.sysinfo.service.CPUMon;

public class CPU implements Runnable {

	private CloseableHttpClient client;
	private String hostname;
	private String url;

	public CPU(CloseableHttpClient client, String hostname, String url) {
		this.client = client;
		this.hostname = hostname;
		this.url = url;
	}

	@Override
	public void run() {
		try {
			StringBuilder builder = new StringBuilder();
			CPUUsage cpu = CPUMon.getCPUUsage(CPUMon.CPU_STATS);
			for (int i = 0; i < cpu.getProcessors().length; i++) {
				ProcessorUsage processorUsage = cpu.getProcessors()[i];
				String format = String.format(SimpleAgent.CPU_METRICS, hostname, processorUsage.getCurrentPercentage(),
						processorUsage.getUser(), processorUsage.getSystem(), processorUsage.getNice(),
						processorUsage.getIowait(), processorUsage.getIrq(), cpu.getTs() * 1000 * 1000);
				builder.append(format + "\n");
			}
			SimpleAgent.extracted(client, builder.toString(), url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}