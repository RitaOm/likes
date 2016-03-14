package com.epam.jmp.util;

public class ArrayUtils {

	public static int[] stringArrayToInt(String[] strArray) {
		int[] newArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			int parsedString = Integer.valueOf(strArray[i]);
			newArray[i] = parsedString;
		}
		return newArray;
	}

}
