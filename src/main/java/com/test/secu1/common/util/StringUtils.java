package com.test.secu1.common.util;

public class StringUtils {
		public static String getExt(String fileName) {
			int idx =fileName.lastIndexOf(".");
			if(idx ==-1) {
				return "";
			}
			return fileName.substring(idx);
		}
}
