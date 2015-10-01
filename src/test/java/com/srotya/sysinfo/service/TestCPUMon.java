package com.srotya.sysinfo.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.srotya.sysinfo.dao.metrics.CPUUsage;
import com.srotya.sysinfo.dao.metrics.ProcessorUsage;

/**
 * @author ambudsharma
 *
 */
public class TestCPUMon {
	
	private static final String TEST_FILE_PATH = "/cpustats1.txt";
	private static final String TEST_FILE_PATH2 = "/cpustats2.txt";
	
	@Test
	public void testComputeProcessorPercentage() {
		ProcessorUsage curr = new ProcessorUsage();
		curr.setUser(16725);
		curr.setNice(0);
		curr.setSystem(4932);
		curr.setIdle(1782697);
		curr.setIowait(1240);
		curr.setIrq(3);
		curr.setSoftirq(350);
		curr.setSteal(0);
		
		ProcessorUsage prev = new ProcessorUsage();
		prev.setUser(16724);
		prev.setNice(0);
		prev.setSystem(4931);
		prev.setIdle(1782499);
		prev.setIowait(1240);
		prev.setIrq(3);
		prev.setSoftirq(350);
		prev.setSteal(0);
		
		CPUMon.computeProcessorPercentage(prev, curr);
		
		assertEquals(1.00, curr.getCurrentPercentage(), 0);
	}
	
	@Test
	public void testParseAndGet() throws IOException {
		List<String> result = Util.readTextFileAsList(getClass().getResource(TEST_FILE_PATH).getFile());
		String line = result.get(0);
		
		ProcessorUsage usage = new ProcessorUsage();
		CPUMon.parseAndGetCPUUsage(line, usage);
		assertEquals(16724, usage.getUser());
		assertEquals(0, usage.getNice());
		assertEquals(4931, usage.getSystem());
		assertEquals(1782499, usage.getIdle());
		assertEquals(1240, usage.getIowait());
		assertEquals(3, usage.getIrq());
		assertEquals(350, usage.getSoftirq());
		assertEquals(0, usage.getSteal());
		
		line = result.get(1);
		CPUMon.parseAndGetCPUUsage(line, usage);
		assertEquals(8220, usage.getUser());
		assertEquals(0, usage.getNice());
		assertEquals(262, usage.getSoftirq());
	}
	
	@Test
	public void testGetCPUUsage() throws IOException {
		CPUUsage usage = CPUMon.getCPUUsage(getClass().getResource(TEST_FILE_PATH).getFile());
		assertEquals(16724, usage.getUser());
		assertEquals(0, usage.getNice());
		assertEquals(4931, usage.getSystem());
		assertEquals(1782499, usage.getIdle());
		assertEquals(1240, usage.getIowait());
		assertEquals(3, usage.getIrq());
		assertEquals(350, usage.getSoftirq());
		assertEquals(0, usage.getSteal());
		
		ProcessorUsage[] processors = usage.getProcessors();
		assertEquals(2, processors.length);
		ProcessorUsage usage2 = processors[0];
		assertEquals(8220, usage2.getUser());
		assertEquals(0, usage2.getNice());
		assertEquals(262, usage2.getSoftirq());
	}
	
	public void testComputeUsage() throws IOException {
		CPUUsage usage1 = CPUMon.getCPUUsage(getClass().getResource(TEST_FILE_PATH).getFile());
		CPUUsage usage2 = CPUMon.getCPUUsage(getClass().getResource(TEST_FILE_PATH2).getFile());
		CPUMon.computeCPUPercentage(usage1, usage2);
		assertEquals(1.00, usage2.getCurrentPercentage(), 0);
	}

}
