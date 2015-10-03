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

public class NetDeviceUsage implements Serializable {

	private static final long serialVersionUID = 1L;
	private String deviceName;
	
	private long rxBytes;
	private long rxPackets;
	private long rxErrs;
	private long rxDrop;
	private long rxFifo;
	private long rxframe;
	private long rxCompressed;
	private long rxMultiCast;
	
	private long txBytes;
	private long txPackets;
	private long txErrs;
	private long txDrop;
	private long txFifo;
	private long txColls;
	private long txCarrier;
	private long txCompressed;
	
	public NetDeviceUsage() {
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public long getRxPackets() {
		return rxPackets;
	}

	public void setRxPackets(long rxPackets) {
		this.rxPackets = rxPackets;
	}

	public long getRxErrs() {
		return rxErrs;
	}

	public void setRxErrs(long rxErrs) {
		this.rxErrs = rxErrs;
	}

	public long getRxDrop() {
		return rxDrop;
	}

	public void setRxDrop(long rxDrop) {
		this.rxDrop = rxDrop;
	}

	public long getRxFifo() {
		return rxFifo;
	}

	public void setRxFifo(long rxFifo) {
		this.rxFifo = rxFifo;
	}

	public long getRxframe() {
		return rxframe;
	}

	public void setRxframe(long rxframe) {
		this.rxframe = rxframe;
	}

	public long getRxCompressed() {
		return rxCompressed;
	}

	public void setRxCompressed(long rxCompressed) {
		this.rxCompressed = rxCompressed;
	}

	public long getRxMultiCast() {
		return rxMultiCast;
	}

	public void setRxMultiCast(long rxMultiCast) {
		this.rxMultiCast = rxMultiCast;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

	public long getTxPackets() {
		return txPackets;
	}

	public void setTxPackets(long txPackets) {
		this.txPackets = txPackets;
	}

	public long getTxErrs() {
		return txErrs;
	}

	public void setTxErrs(long txErrs) {
		this.txErrs = txErrs;
	}

	public long getTxDrop() {
		return txDrop;
	}

	public void setTxDrop(long txDrop) {
		this.txDrop = txDrop;
	}

	public long getTxFifo() {
		return txFifo;
	}

	public void setTxFifo(long txFifo) {
		this.txFifo = txFifo;
	}

	public long getTxColls() {
		return txColls;
	}

	public void setTxColls(long txColls) {
		this.txColls = txColls;
	}

	public long getTxCarrier() {
		return txCarrier;
	}

	public void setTxCarrier(long txCarrier) {
		this.txCarrier = txCarrier;
	}

	public long getTxCompressed() {
		return txCompressed;
	}

	public void setTxCompressed(long txCompressed) {
		this.txCompressed = txCompressed;
	}
	
}
