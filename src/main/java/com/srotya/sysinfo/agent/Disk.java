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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;

import com.srotya.sysinfo.dao.metrics.DiskDevUsage;
import com.srotya.sysinfo.service.DiskMon;

public class Disk implements Runnable {

	static final String DISK_METRIC = "disk,host=%s,dev=%s rrqms=%f,wrqms=%f,rs=%f,ws=%f,rmsec=%f,wmsec=%f,avgrqsz=%f,avgqusz=%f,await=%f,svctm=%f,putil=%f %d";
	private CloseableHttpClient client;
	private String hostname;
	private String url;
	private ProcessBuilder builder;

	public Disk(CloseableHttpClient client, String hostname, String url) throws IOException {
		this.client = client;
		this.hostname = hostname;
		this.url = url;
		builder = new ProcessBuilder("iostat", "-xmd");
		System.out.println("Disk metrics initialized");
	}

	public String readAllLines(BufferedReader reader) throws IOException {
		StringBuilder builder = new StringBuilder();
		String tmp = null;
		while ((tmp = reader.readLine()) != null) {
			builder.append(tmp + "\n");
		}
		reader.close();
		return builder.toString();
	}

	@Override
	public void run() {
		try {
			StringBuilder builder = getData();
			SimpleAgent.extracted(client, builder.toString(), url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private StringBuilder getData() throws Exception {
		Process proc = builder.start();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(proc.getInputStream(), Charset.defaultCharset()));
		String lines = readAllLines(reader);
		proc.waitFor();
		StringBuilder builder = new StringBuilder();
		List<DiskDevUsage> disk = DiskMon.computeUsage(lines);
		long ts = System.currentTimeMillis();
		for (int i = 0; i < disk.size(); i++) {
			DiskDevUsage dev = disk.get(i);
			String format = String.format(DISK_METRIC, hostname, dev.getDev(), dev.getRrqms(), dev.getWrqms(),
					dev.getRs(), dev.getWs(), dev.getRmsec(), dev.getWmsec(), dev.getAvgrqsz(), dev.getAvgqusz(),
					dev.getAwait(), dev.getSvctm(), dev.getPutil(), ts * 1000 * 1000);
			builder.append(format + "\n");
		}
		return builder;
	}

}