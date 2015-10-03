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

import org.junit.Test;

import com.srotya.sysinfo.dao.metrics.NetDeviceUsage;

public class TestNetMon {
	
	private static final String NETSTAT_LINE = "  eth0: 2679999    2260    2    5    9"
			+ "     4          1        47   201622    2321"
			+ "    20    51    89     99       21          27";

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
		
		assertEquals(201622, usage.getTxBytes());
		assertEquals(2321, usage.getTxPackets());
		assertEquals(20, usage.getTxErrs());
		assertEquals(51, usage.getTxDrop());
		assertEquals(89, usage.getTxFifo());
		assertEquals(99, usage.getTxColls());
		assertEquals(21, usage.getTxCarrier());
		assertEquals(27, usage.getTxCompressed());
	}

}
