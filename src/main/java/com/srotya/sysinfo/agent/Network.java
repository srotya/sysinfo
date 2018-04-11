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

import com.srotya.sysinfo.dao.metrics.NetDeviceUsage;
import com.srotya.sysinfo.dao.metrics.NetworkUsage;
import com.srotya.sysinfo.service.NetMon;

public class Network implements Runnable {

	private CloseableHttpClient client;
	private String hostname;
	private String url;

	public Network(CloseableHttpClient client, String hostname, String url) {
		this.client = client;
		this.hostname = hostname;
		this.url = url;
	}

	@Override
	public void run() {
		try {
			NetworkUsage net = NetMon.computeUsage(NetMon.NETWORK_STATS);
			StringBuilder payload = new StringBuilder();
			for (NetDeviceUsage dev : net.getDevices()) {
				String format = String.format(SimpleAgent.NET, hostname, dev.getDeviceName(), dev.getRxBytes(),
						dev.getRxPackets(), dev.getRxErrs(), dev.getTxBytes(), dev.getTxPackets(), dev.getTxErrs(),
						net.getTs() * 1000 * 1000);
				payload.append(format);
			}
			SimpleAgent.extracted(client, payload.toString(), url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}