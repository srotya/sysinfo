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

import com.srotya.sysinfo.dao.config.NetworkInfo;
import com.srotya.sysinfo.dao.metrics.NetworkUsage;
import com.srotya.sysinfo.service.SysInfoRESTController;

@Path("/network")
public class ProcNetworkInfo {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public NetworkInfo getInfo() {
		return new NetworkInfo();
	}
	
	@Path("/stats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public NetworkUsage getUsage() {
		return SysInfoRESTController.getNetMon().getUsage();
	}
	
}
