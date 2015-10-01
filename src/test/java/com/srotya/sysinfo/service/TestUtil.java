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
