package com.srotya.sysinfo.dao;

import java.io.Serializable;

/**
 * Processor DAO. Captures fields exposed by /proc/cpuinfo like CPU vendor, cores, flags etc.
 * 
 * @author ambudsharma
 *
 */
public class ProcessorInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int processorNumber;
	private String vendorId;
	private String cpuFamily;
	private String model;
	private String modelName;
	private int stepping;
	private String microCode;
	private int physicalId;
	private int siblings;
	private int coreId;
	private int cpuCores;
	private int apiCid;
	private boolean fpu;
	private boolean fpuException;
	private String flags;
	private String addressSize;
	
	public ProcessorInfo() {
	}

	public int getProcessorNumber() {
		return processorNumber;
	}

	public void setProcessorNumber(int processorNumber) {
		this.processorNumber = processorNumber;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getCpuFamily() {
		return cpuFamily;
	}

	public void setCpuFamily(String cpuFamily) {
		this.cpuFamily = cpuFamily;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getStepping() {
		return stepping;
	}

	public void setStepping(int stepping) {
		this.stepping = stepping;
	}

	public String getMicroCode() {
		return microCode;
	}

	public void setMicroCode(String microCode) {
		this.microCode = microCode;
	}

	public int getPhysicalId() {
		return physicalId;
	}

	public void setPhysicalId(int physicalId) {
		this.physicalId = physicalId;
	}

	public int getSiblings() {
		return siblings;
	}

	public void setSiblings(int siblings) {
		this.siblings = siblings;
	}

	public int getCoreId() {
		return coreId;
	}

	public void setCoreId(int coreId) {
		this.coreId = coreId;
	}

	public int getCpuCores() {
		return cpuCores;
	}

	public void setCpuCores(int cpuCores) {
		this.cpuCores = cpuCores;
	}

	public int getApiCid() {
		return apiCid;
	}

	public void setApiCid(int apiCid) {
		this.apiCid = apiCid;
	}

	public boolean isFpu() {
		return fpu;
	}

	public void setFpu(boolean fpu) {
		this.fpu = fpu;
	}

	public boolean isFpuException() {
		return fpuException;
	}

	public void setFpuException(boolean fpuException) {
		this.fpuException = fpuException;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public String getAddressSize() {
		return addressSize;
	}

	public void setAddressSize(String addressSize) {
		this.addressSize = addressSize;
	}
	
}
