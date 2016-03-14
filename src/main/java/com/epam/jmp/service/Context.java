package com.epam.jmp.service;

import java.util.HashMap;
import java.util.Map;

public class Context {

	private Map<String, Object> parameters;

	public Context(HashMap<String, Object> params) {
		if (params != null) {
			parameters = params;
		} else {
			parameters = new HashMap<String, Object>();
		}
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

}
