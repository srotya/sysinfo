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

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract Monitoring daemon class definition
 * 
 * @author ambudsharma
 *
 */
public abstract class AbstractMon implements Runnable {
	
	private AtomicBoolean loopControl = null;
	private AtomicInteger sleepTime;
	
	public AbstractMon(AtomicBoolean loopControl, AtomicInteger sleepTime) {
		this.loopControl = loopControl;
		this.sleepTime = sleepTime;
	}

	public void run() {
		do {
			monitor();
			try {
				Thread.sleep(sleepTime.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
				getLogger().log(Level.SEVERE, "CPU Monitor thread interrupted, exitting monitoring loop");
				break;
			}
		}while(loopControl.get());
	}

	public abstract Logger getLogger();

	public abstract void monitor();

}
