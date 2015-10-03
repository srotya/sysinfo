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
package com.srotya.sysinfo.dao.metrics;

import java.io.Serializable;

/**
 * @author ambudsharma
 *
 */
public class MemUsage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long ts;
	private long memTotal;
	private long memFree;
	private long memAvailable;
	private long buffers;
	private long cached;
	private long swapCached;
	private long mLocked;
	private long swapTotal;
	private long swapFree;
	private long mapped;
	private long pageTables;

	public MemUsage() {
	}

	public long getMemTotal() {
		return memTotal;
	}

	public void setMemTotal(long memTotal) {
		this.memTotal = memTotal;
	}

	public long getMemFree() {
		return memFree;
	}

	public void setMemFree(long memFree) {
		this.memFree = memFree;
	}

	public long getMemAvailable() {
		return memAvailable;
	}

	public void setMemAvailable(long memAvailable) {
		this.memAvailable = memAvailable;
	}

	public long getBuffers() {
		return buffers;
	}

	public void setBuffers(long buffers) {
		this.buffers = buffers;
	}

	public long getCached() {
		return cached;
	}

	public void setCached(long cached) {
		this.cached = cached;
	}

	public long getSwapCached() {
		return swapCached;
	}

	public void setSwapCached(long swapCached) {
		this.swapCached = swapCached;
	}

	public long getmLocked() {
		return mLocked;
	}

	public void setmLocked(long mLocked) {
		this.mLocked = mLocked;
	}

	public long getSwapTotal() {
		return swapTotal;
	}

	public void setSwapTotal(long swapTotal) {
		this.swapTotal = swapTotal;
	}

	public long getSwapFree() {
		return swapFree;
	}

	public void setSwapFree(long swapFree) {
		this.swapFree = swapFree;
	}

	public long getMapped() {
		return mapped;
	}

	public void setMapped(long mapped) {
		this.mapped = mapped;
	}

	public long getPageTables() {
		return pageTables;
	}

	public void setPageTables(long pageTables) {
		this.pageTables = pageTables;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}
}
