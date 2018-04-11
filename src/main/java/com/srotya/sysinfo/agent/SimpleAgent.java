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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.instrument.Instrumentation;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 * Agent takes
 */
public class SimpleAgent {

	private static final String URL = "http://%s:%d/influx?db=%s";
	static final String CPU_METRICS = "cpu,host=%s percentage=%f,user=%di,system=%di,nice=%di,iow=%di,irq=%di %d";
	static final String NET = "net,host=%s,dev=%s rxb=%d,rxp=%d,rxe=%d,txb=%d,txp=%d,txe=%d %d";

	public static void premain(String args, Instrumentation instrumentation) {
		try {
			String hostname = InetAddress.getLocalHost().getHostName();
			ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

			String destination = System.getProperty("destination", "localhost");
			int port = Integer.parseInt(System.getProperty("port", "8080"));
			String db = System.getProperty("dbname", "spark");
			String url = String.format(URL, destination, port, db);
			CloseableHttpClient client = buildClient(url, 2000, 5000);
			es.scheduleAtFixedRate(new CPU(client, hostname, url), 0, 1, TimeUnit.SECONDS);
			System.out.println("Agent started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void extracted(CloseableHttpClient client, String format, String url)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		HttpPost request = new HttpPost(url);
		request.setEntity(new StringEntity(format));
		CloseableHttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 204) {
			throw new IOException("Request failed:" + response.getStatusLine().getReasonPhrase());
		}
	}

	public static CloseableHttpClient buildClient(String baseURL, int connectTimeout, int requestTimeout)
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		HttpClientBuilder clientBuilder = HttpClients.custom();
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(requestTimeout).build();

		return clientBuilder.setDefaultRequestConfig(config).build();
	}

}
