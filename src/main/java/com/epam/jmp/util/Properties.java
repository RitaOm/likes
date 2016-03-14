package com.epam.jmp.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Properties {

	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("appl");

	private Properties() {
	}

	public static String getString(String key) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static int getNumber(String key) {
		try { 
			return Integer.parseInt(resourceBundle.getString(key));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String[] getStringArr(String key) {
		try { 
			String str = resourceBundle.getString(key);
			return str.split(",");
		} catch (Exception e) {
			return new String[0];
		}
	}

}
