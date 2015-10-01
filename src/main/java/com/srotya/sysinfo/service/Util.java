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
	
	public static String readTextFileAsString(String fileName) throws IOException {
		return readTextFileAsString(new File(fileName));
	}
	
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
	
	public static List<String> readTextFileAsList(String fileName) throws IOException {
		return readTextFileAsList(new File(fileName));
	}
	
}
