package com.srotya.sysinfo.dao.metrics;

public class NetworkUsage {

	private long ts;
	private NeDeviceUsage[] devices;
	
	public NetworkUsage() {
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public NeDeviceUsage[] getDevices() {
		return devices;
	}

	public void setDevices(NeDeviceUsage[] devices) {
		this.devices = devices;
	}
	
}
