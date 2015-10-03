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

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.srotya.sysinfo.dao.metrics.NetDeviceUsage;
import com.srotya.sysinfo.dao.metrics.NetworkUsage;

/**
 * @author ambudsharma
 *
 */
public class TestNetMon {
	
	private static final String NETSTAT_LINE = "  eth0: 2679999    2260    2    5    9"
			+ "     4          1        47   201622    2321"
			+ "    20    51    89     99       21          27";
	private static final String NET_STATS_FILE = "/networkstats.txt";

	@Test
	public void testComputeNDeviceUsage() {
		NetDeviceUsage usage = NetMon.computeNDeviceUsage(NETSTAT_LINE);
		
		assertEquals("eth0", usage.getDeviceName());
		assertEquals(2679999, usage.getRxBytes());
		assertEquals(2260, usage.getRxPackets());
		assertEquals(2, usage.getRxErrs());
		assertEquals(5, usage.getRxDrop());
		assertEquals(9, usage.getRxFifo());
		assertEquals(4, usage.getRxframe());
		assertEquals(1, usage.getRxCompressed());
		assertEquals(47, usage.getRxMultiCast());
		
		assertEquals(201622, usage.getTxBytes());
		assertEquals(2321, usage.getTxPackets());
		assertEquals(20, usage.getTxErrs());
		assertEquals(51, usage.getTxDrop());
		assertEquals(89, usage.getTxFifo());
		assertEquals(99, usage.getTxColls());
		assertEquals(21, usage.getTxCarrier());
		assertEquals(27, usage.getTxCompressed());
	}

	@Test
	public void testComputeUsage() throws IOException {
		NetworkUsage usage = NetMon.computeUsage(getClass().getResource(NET_STATS_FILE).getFile());
		NetDeviceUsage[] devs = usage.getDevices();
		assertEquals(2, devs.length);
		
		assertEquals("eth0", devs[0].getDeviceName());
		assertEquals(2679999, devs[0].getRxBytes());
		assertEquals(2260, devs[0].getRxPackets());
		assertEquals(0, devs[0].getRxErrs());
		assertEquals(0, devs[0].getRxDrop());
		assertEquals(0, devs[0].getRxFifo());
		assertEquals(0, devs[0].getRxframe());
		assertEquals(0, devs[0].getRxCompressed());
		assertEquals(47, devs[0].getRxMultiCast());
		
		assertEquals(201622, devs[0].getTxBytes());
		assertEquals(2321, devs[0].getTxPackets());
		assertEquals(0, devs[0].getTxErrs());
		assertEquals(0, devs[0].getTxDrop());
		assertEquals(0, devs[0].getTxFifo());
		assertEquals(0, devs[0].getTxColls());
		assertEquals(0, devs[0].getTxCarrier());
		assertEquals(0, devs[0].getTxCompressed());
		
		assertEquals("lo", devs[1].getDeviceName());
		assertEquals(6539914, devs[1].getRxBytes());
		assertEquals(23131, devs[1].getRxPackets());
		assertEquals(0, devs[1].getRxErrs());
		assertEquals(0, devs[1].getRxDrop());
		assertEquals(0, devs[1].getRxFifo());
		assertEquals(0, devs[1].getRxframe());
		assertEquals(0, devs[1].getRxCompressed());
		assertEquals(0, devs[1].getRxMultiCast());
		
		assertEquals(6539914, devs[1].getTxBytes());
		assertEquals(23131, devs[1].getTxPackets());
		assertEquals(0, devs[1].getTxErrs());
		assertEquals(0, devs[1].getTxDrop());
		assertEquals(0, devs[1].getTxFifo());
		assertEquals(0, devs[1].getTxColls());
		assertEquals(0, devs[1].getTxCarrier());
		assertEquals(0, devs[1].getTxCompressed());
	}
	
}
