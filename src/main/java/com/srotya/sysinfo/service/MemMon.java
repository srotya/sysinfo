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
package com.srotya.sysinfo.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.srotya.sysinfo.dao.metrics.MemoryUsage;

/**
 * Memory usage monitoring daemon that monitors the /proc/meminfo file to get memory
 * usage statistics.
 * 
 * @author ambudsharma
 *
 */
public class MemMon extends AbstractMon {
	
	public static final String MEM_STATS = "/proc/meminfo";
	
	private static final String MAPPED = "Mapped";
	private static final String PAGE_TABLES = "PageTables";
	private static final String SWAP_FREE = "SwapFree";
	private static final String SWAP_TOTAL = "SwapTotal";
	private static final String MLOCKED = "Mlocked";
	private static final String SWAP_CACHED = "SwapCached";
	private static final String CACHED = "Cached";
	private static final String BUFFERS = "Buffers";
	private static final String MEM_AVAILABLE = "MemAvailable";
	private static final String MEM_FREE = "MemFree";
	private static final String MEM_TOTAL = "MemTotal";
	private static final Logger logger = Logger.getLogger(MemMon.class.getName());
	
	private AtomicReference<MemoryUsage> usage;

	public MemMon(AtomicBoolean loopControl, AtomicInteger sleepTime) {
		super(loopControl, sleepTime);
		this.usage = new AtomicReference<MemoryUsage>(null);
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public void monitor() {
		try {
			usage.set(getMemoryUsage());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to get memory usage", e);
		}
	}
	
	public MemoryUsage getMemoryUsage() throws IOException {
		return computeMemoryUsage(MEM_STATS);
	}
	
	public static MemoryUsage computeMemoryUsage(String memInfoFile) throws IOException {
		List<String> lines = Util.readTextFileAsList(memInfoFile);
		long ts = System.currentTimeMillis();
		Map<String, Long> data = new HashMap<String, Long>();
		for(String line:lines) {
			String[] splits = line.split("\\s+");
			data.put(splits[0].replace(":", ""), Long.parseLong(splits[1]));
		}
		
		MemoryUsage usage = new MemoryUsage();
		usage.setTs(ts);
		usage.setMemTotal(data.get(MEM_TOTAL));
		usage.setMemFree(data.get(MEM_FREE));
		usage.setMemAvailable(data.get(MEM_AVAILABLE));
		usage.setBuffers(data.get(BUFFERS));
		usage.setCached(data.get(CACHED));
		usage.setSwapCached(data.get(SWAP_CACHED));
		usage.setmLocked(data.get(MLOCKED));
		usage.setSwapTotal(data.get(SWAP_TOTAL));
		usage.setSwapFree(data.get(SWAP_FREE));
		usage.setPageTables(data.get(PAGE_TABLES));
		usage.setMapped(data.get(MAPPED));
		
		return usage;
	}
	
	public MemoryUsage getUsage() {
		return usage.get();
	}
	
}
