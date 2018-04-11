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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import com.srotya.sysinfo.dao.metrics.DiskDevUsage;
import com.srotya.sysinfo.dao.metrics.DiskUsage;

/**
 * @author ambudsharma
 *
 */
public class TestDiskMon {

	private static final String TEST_FILE_PATH = "/diskstat1.txt";
	private static final String TEST_FILE_PATH2 = "/diskstat2.txt";

	@Test
	public void testDiskDevUsage() throws IOException {
		DiskDevUsage usage = DiskMon.computeDevUsage(
				"sda               0.00     0.09    0.45    0.51     0.01     0.04   110.41     0.01   14.94    0.56   0.05");
		assertEquals(0.0f, usage.getRrqms(), 0);
		assertEquals(0.09f, usage.getWrqms(), 0);
		assertEquals(0.45f, usage.getRs(), 0);
		assertEquals(0.51f, usage.getWs(), 0);
		assertEquals(0.01f, usage.getRmsec(), 0);
		assertEquals(0.04f, usage.getWmsec(), 0);
		assertEquals(110.41f, usage.getAvgrqsz(), 0);
		assertEquals(0.01f, usage.getAvgqusz(), 0);
		assertEquals(14.94f, usage.getAwait(), 0);
	}

	@Test
	public void testDiskUsage() throws IOException {
		String lines = new String(
				Files.readAllBytes(new File(getClass().getResource(TEST_FILE_PATH).getFile()).toPath()));
		DiskUsage devs = DiskMon.computeUsage(lines);
		assertEquals(1, devs.getDevs().size());
		for (DiskDevUsage dev : devs.getDevs()) {
			assertEquals(0.0f, dev.getRrqms(), 0);
			assertEquals(0.09f, dev.getWrqms(), 0);
			assertEquals(0.44f, dev.getRs(), 0);
			assertEquals(0.51f, dev.getWs(), 0);
			assertEquals(0.01f, dev.getRmsec(), 0);
			assertEquals(0.04f, dev.getWmsec(), 0);
			assertEquals(110.33f, dev.getAvgrqsz(), 0);
			assertEquals(0.01f, dev.getAvgqusz(), 0);
			assertEquals(14.93f, dev.getAwait(), 0);
		}

		lines = new String(Files.readAllBytes(new File(getClass().getResource(TEST_FILE_PATH2).getFile()).toPath()));
		devs = DiskMon.computeUsage(lines);
		assertEquals(1, devs.getDevs().size());
		for (DiskDevUsage dev : devs.getDevs()) {
			assertEquals(0.0f, dev.getRrqms(), 0);
			assertEquals(0.09f, dev.getWrqms(), 0);
			assertEquals(0.44f, dev.getRs(), 0);
			assertEquals(0.51f, dev.getWs(), 0);
			assertEquals(0.01f, dev.getRmsec(), 0);
			assertEquals(0.04f, dev.getWmsec(), 0);
			assertEquals(110.33f, dev.getAvgrqsz(), 0);
			assertEquals(0.01f, dev.getAvgqusz(), 0);
			assertEquals(14.93f, dev.getAwait(), 0);
		}
	}

}
