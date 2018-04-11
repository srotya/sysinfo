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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.srotya.sysinfo.dao.metrics.DiskDevUsage;
import com.srotya.sysinfo.dao.metrics.DiskUsage;

/**
 * Disk monitoring, uses iostat
 * 
 * @author ambud
 *
 */
public class DiskMon extends AbstractMon {

	private static final Logger logger = Logger.getLogger(DiskMon.class.getName());

	private AtomicReference<DiskDevUsage> usage;

	public DiskMon(AtomicBoolean loopControl, AtomicInteger sleepTime) {
		super(loopControl, sleepTime);
		this.usage = new AtomicReference<DiskDevUsage>(null);
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public void monitor() {
		try {
			usage.set(null);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to get disk usage", e);
		}
	}
	
	public static DiskUsage computeUsage(String lines) throws IOException {
		String[] split = lines.split("\\n");
		String line = split[0];
		int i = 1;
		DiskUsage usage = new DiskUsage();
		List<DiskDevUsage> devs = usage.getDevs();
		while (i < split.length) {
			line = split[i];
			if (line.startsWith("sda")) {
				devs.add(computeDevUsage(line));
			}
			i++;
		}
		return usage;
	}

	public static DiskDevUsage computeDevUsage(String line) throws IOException {
		String[] splits = line.split("\\s+");
		DiskDevUsage usage = new DiskDevUsage();
		usage.setDev(splits[0]);
		usage.setRrqms(Float.parseFloat(splits[1]));
		usage.setWrqms(Float.parseFloat(splits[2]));
		usage.setRs(Float.parseFloat(splits[3]));
		usage.setWs(Float.parseFloat(splits[4]));
		usage.setRmsec(Float.parseFloat(splits[5]));
		usage.setWmsec(Float.parseFloat(splits[6]));
		usage.setAvgrqsz(Float.parseFloat(splits[7]));
		usage.setAvgqusz(Float.parseFloat(splits[8]));
		usage.setAwait(Float.parseFloat(splits[9]));
		if (splits.length > 12) {
			usage.setSvctm(Float.parseFloat(splits[12]));
			usage.setPutil(Float.parseFloat(splits[13]));
		} else {
			usage.setSvctm(Float.parseFloat(splits[10]));
			usage.setPutil(Float.parseFloat(splits[11]));
		}
		return usage;
	}

}
