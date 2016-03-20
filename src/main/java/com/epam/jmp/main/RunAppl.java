package com.epam.jmp.main;

import com.epam.jmp.service.impl.CreateRandomService;

public class RunAppl {

	public static void main(String[] args) throws Exception{
		CreateRandomService service = new CreateRandomService();
		service.createAll(null);
	}

}
