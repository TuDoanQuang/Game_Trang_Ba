package com.example.demo.utils;

import com.example.demo.enums.PointStatus;

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
	
	public static boolean isEnumsEqual(PointStatus ...values ) {
		if (values == null || values.length == 0) {
			return false;
		}
		PointStatus comPareEnum = values[0];
		for (PointStatus value : values) {
			if (value == null || comPareEnum != value) {
				return false;
			}
		}
		
		return true;
	}
	
}
