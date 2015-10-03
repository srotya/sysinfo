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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.srotya.sysinfo.dao.metrics.NetDeviceUsage;
import com.srotya.sysinfo.dao.metrics.NetworkUsage;

public class NetMon extends AbstractMon {

	public NetMon(AtomicBoolean loopControl, AtomicInteger sleepTime) {
		super(loopControl, sleepTime);
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void monitor() {
		// TODO Auto-generated method stub

	}
	
	public static NetworkUsage computeUsage(String fileName) throws IOException {
		NetworkUsage usage = new NetworkUsage();
		List<String> lines = Util.readTextFileAsList(fileName);
		usage.setTs(System.currentTimeMillis());
		// ignore lines 0 & 1
		List<NetDeviceUsage> devices = new ArrayList<NetDeviceUsage>();
		for(int i=2;i<lines.size();i++) {
			devices.add(computeNDeviceUsage(lines.get(i)));
		}
		usage.setDevices(devices.toArray(new NetDeviceUsage[1]));
		return usage;
	}
	
	public static NetDeviceUsage computeNDeviceUsage(String deviceLine) {
		NetDeviceUsage device = new NetDeviceUsage();
		String[] splits = deviceLine.split("\\s+");
		
		device.setDeviceName(splits[1].replace(":", ""));
		
		device.setRxBytes(Long.parseLong(splits[2]));
		device.setRxPackets(Long.parseLong(splits[3]));
		device.setRxErrs(Long.parseLong(splits[4]));
		device.setRxDrop(Long.parseLong(splits[5]));
		device.setRxFifo(Long.parseLong(splits[6]));
		device.setRxframe(Long.parseLong(splits[7]));
		device.setRxCompressed(Long.parseLong(splits[8]));
		device.setRxMultiCast(Long.parseLong(splits[9]));
		
		device.setTxBytes(Long.parseLong(splits[10]));
		device.setTxPackets(Long.parseLong(splits[11]));
		device.setTxErrs(Long.parseLong(splits[12]));
		device.setTxDrop(Long.parseLong(splits[13]));
		device.setTxFifo(Long.parseLong(splits[14]));
		device.setTxColls(Long.parseLong(splits[15]));
		device.setTxCarrier(Long.parseLong(splits[16]));
		device.setTxCompressed(Long.parseLong(splits[17]));
		
		return device;
	}

}
