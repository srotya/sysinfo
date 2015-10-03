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
package com.srotya.sysinfo.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.srotya.sysinfo.dao.config.CPUInfo;
import com.srotya.sysinfo.dao.metrics.CPUUsage;
import com.srotya.sysinfo.service.SysInfoRESTController;

/**
 * REST endpoint for CPU Info and Stats   		
 * 
 * @author ambudsharma
 *
 */
@Path("/cpu")
public class ProcCPUInfo {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CPUInfo getInfo() {
		CPUInfo info = new CPUInfo();
		return info;
	}
	
	@Path("/stats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CPUUsage getUsage() {
		return SysInfoRESTController.getCpuMon().getUsage();
	}
	
}
