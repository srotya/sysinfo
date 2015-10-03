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
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

public class TestUtil {

	private static final String TEST_FILE_PATH = "/simpletextfile.txt";

	@Test
	public void testReadTextAsString() throws IOException, URISyntaxException {
		String result = Util.readTextFileAsString(getClass().getResource(TEST_FILE_PATH).getFile());
		String[] splits = result.split("\n");
		assertEquals(5, splits.length);
		assertArrayEquals(new String[] {"cpu 1", "memo 2", "disk 3", "", "file 4"},  splits);
	}
	
	@Test
	public void testReadTextAsList() throws IOException, URISyntaxException {
		List<String> result = Util.readTextFileAsList(getClass().getResource(TEST_FILE_PATH).getFile());
		assertEquals(5, result.size());
	}
}
