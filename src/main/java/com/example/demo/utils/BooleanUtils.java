package com.example.demo.utils;

public class BooleanUtils {
	public static boolean isTrue(Boolean value) {
		if (value == null || value == false) {
				return false;
	}
		
		return true;
	}
}
