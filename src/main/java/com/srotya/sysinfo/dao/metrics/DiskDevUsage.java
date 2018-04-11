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
 * @author ambud
 */
public class DiskDevUsage implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dev;
	private float rrqms;
	private float wrqms;
	private float rs;
	private float ws;
	private float rmsec;
	private float wmsec;
	private float avgrqsz;
	private float avgqusz;
	private float await;
	private float svctm;
	private float putil;

	/**
	 * @return the dev
	 */
	public String getDev() {
		return dev;
	}

	/**
	 * @param dev the dev to set
	 */
	public void setDev(String dev) {
		this.dev = dev;
	}

	/**
	 * @return the rrqms
	 */
	public float getRrqms() {
		return rrqms;
	}

	/**
	 * @param rrqms
	 *            the rrqms to set
	 */
	public void setRrqms(float rrqms) {
		this.rrqms = rrqms;
	}

	/**
	 * @return the wrqms
	 */
	public float getWrqms() {
		return wrqms;
	}

	/**
	 * @param wrqms
	 *            the wrqms to set
	 */
	public void setWrqms(float wrqms) {
		this.wrqms = wrqms;
	}

	/**
	 * @return the rs
	 */
	public float getRs() {
		return rs;
	}

	/**
	 * @param rs
	 *            the rs to set
	 */
	public void setRs(float rs) {
		this.rs = rs;
	}

	/**
	 * @return the ws
	 */
	public float getWs() {
		return ws;
	}

	/**
	 * @param ws
	 *            the ws to set
	 */
	public void setWs(float ws) {
		this.ws = ws;
	}

	/**
	 * @return the rmsec
	 */
	public float getRmsec() {
		return rmsec;
	}

	/**
	 * @param rmsec
	 *            the rmsec to set
	 */
	public void setRmsec(float rmsec) {
		this.rmsec = rmsec;
	}

	/**
	 * @return the wmsec
	 */
	public float getWmsec() {
		return wmsec;
	}

	/**
	 * @param wmsec
	 *            the wmsec to set
	 */
	public void setWmsec(float wmsec) {
		this.wmsec = wmsec;
	}

	/**
	 * @return the avgrqsz
	 */
	public float getAvgrqsz() {
		return avgrqsz;
	}

	/**
	 * @param avgrqsz
	 *            the avgrqsz to set
	 */
	public void setAvgrqsz(float avgrqsz) {
		this.avgrqsz = avgrqsz;
	}

	/**
	 * @return the avgqusz
	 */
	public float getAvgqusz() {
		return avgqusz;
	}

	/**
	 * @param avgqusz
	 *            the avgqusz to set
	 */
	public void setAvgqusz(float avgqusz) {
		this.avgqusz = avgqusz;
	}

	/**
	 * @return the await
	 */
	public float getAwait() {
		return await;
	}

	/**
	 * @param await
	 *            the await to set
	 */
	public void setAwait(float await) {
		this.await = await;
	}

	/**
	 * @return the svctm
	 */
	public float getSvctm() {
		return svctm;
	}

	/**
	 * @param svctm
	 *            the svctm to set
	 */
	public void setSvctm(float svctm) {
		this.svctm = svctm;
	}

	/**
	 * @return the putil
	 */
	public float getPutil() {
		return putil;
	}

	/**
	 * @param putil
	 *            the putil to set
	 */
	public void setPutil(float putil) {
		this.putil = putil;
	}

}
