package com.wipdev.snegge.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static File loadFile(String path) {
		return new File(path);
	}
	
	/**
	 * Creates a new File if not existent
	 * @param path the path and filename of the File
	 * @return if the file is created or was existent
	 */
	public static boolean createNewFile(String path) {
		File file =loadFile(path);
		if(file.exists()) {
			return false;
		}else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
	public static List<String> readLinesFormFile(File file){
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line=br.readLine()) !=null) {
				lines.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static File saveStringToFile(String data,File file) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
}
