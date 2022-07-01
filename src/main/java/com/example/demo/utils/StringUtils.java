package com.example.demo.utils;

public class StringUtils {

	public static boolean isNotEmpty(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isAllNotEmpty(String ...values ) {
		if (values == null || values.length == 0) {
			return false;
		}
		
		for (String value : values) {
			if (!isNotEmpty(value)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isAllEqual(String ...values ) {

		if (!isAllNotEmpty(values)) {
			return false;
		}
		String compareString = values[0];
		for (String value : values) {
			if (!compareString.equals(value)) {
				return false;
			}
		}
		return true;
	}
}
