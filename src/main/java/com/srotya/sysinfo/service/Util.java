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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility to class.
 * 
 * @author ambudsharma
 *
 */
public class Util {

	private Util() {
	}
	
	/**
	 * Read text file as {@link String}
	 * @param file {@link File}
	 * @return file contents as string
	 * @throws IOException
	 */
	public static String readTextFileAsString(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder builder = new StringBuilder();
		String temp = null;
		try{
			while((temp=reader.readLine())!=null) {
				builder.append(temp+"\n");
			}
		}finally{
			reader.close();
		}
		return builder.toString();
	}
	
	/**
	 * Read text file as {@link String}
	 * @param fileName or path to file
	 * @return file contents as string
	 * @throws IOException
	 */
	public static String readTextFileAsString(String fileName) throws IOException {
		return readTextFileAsString(new File(fileName));
	}
	
	/**
	 * Read text file as {@link String}
	 * @param file {@link File}
	 * @return file contents as {@link List}
	 * @throws IOException
	 */
	public static List<String> readTextFileAsList(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String temp = null;
		try{
			while((temp=reader.readLine())!=null) {
				lines.add(temp);
			}
		}finally{
			reader.close();
		}
		return lines;
	}
	
	/**
	 * Read text file as {@link String}
	 * @param fileName or path to file
	 * @return file contents as {@link List}
	 * @throws IOException
	 */
	public static List<String> readTextFileAsList(String fileName) throws IOException {
		return readTextFileAsList(new File(fileName));
	}
	
}
