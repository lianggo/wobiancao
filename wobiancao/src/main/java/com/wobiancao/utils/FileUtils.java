package com.wobiancao.utils;

import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	private static List<String> imageExtensionNameList = new ArrayList<String>();

	static {
		imageExtensionNameList.add("png");
		imageExtensionNameList.add("gif");
		imageExtensionNameList.add("jpg");
	}

	public static String getFileExtension(String filename) {
		if (filename == null) {
			return "";
		}
		if (filename.lastIndexOf(".") == -1 || filename.lastIndexOf(".") == 0) {
			return "";
		}

		return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	}
	
	public static String appendFileExtension(String filename) {
		if (filename == null) {
			return "";
		}
		if (filename.lastIndexOf(".") == -1 || filename.lastIndexOf(".") == 0) {
			return "";
		}

		return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	}

	public static boolean isImage(String filename) {
		String fileExtension = getFileExtension(filename);
		if (!imageExtensionNameList.contains(fileExtension)) {
			return false;
		}

		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(isImage("test.jpg"));
		System.out.println(isImage("test.txt"));
	}

}
