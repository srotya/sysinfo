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

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.srotya.sysinfo.dao.metrics.MemUsage;

/**
 * @author ambudsharma
 *
 */
public class TestMemMon {
	
	private static final String TEST_MEM_INFO_FILE = "/meminfo.txt";
	
	@Test
	public void testComputeMemoryUsage() throws IOException {
		MemUsage usage = MemMon.computeMemoryUsage(getClass().getResource(TEST_MEM_INFO_FILE).getFile());
		assertEquals(1016928, usage.getMemTotal());
		assertEquals(557616, usage.getMemFree());
		assertEquals(594840, usage.getMemAvailable());
		assertEquals(26880, usage.getBuffers());
		assertEquals(126196, usage.getCached());
		assertEquals(0, usage.getSwapCached());
		assertEquals(0, usage.getmLocked());
		assertEquals(1046524, usage.getSwapTotal());
		assertEquals(1046524, usage.getSwapFree());
		assertEquals(57420, usage.getMapped());
		assertEquals(7656, usage.getPageTables());
	}
	
}
