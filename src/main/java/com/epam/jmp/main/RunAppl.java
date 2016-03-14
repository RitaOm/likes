package com.epam.jmp.main;

import java.sql.Date;
import java.sql.Timestamp;

import com.epam.jmp.util.DateGenerator;


public class RunAppl {

	public static void main(String[] args) throws Exception{
		DateGenerator dg = new DateGenerator();
		Timestamp rand = dg.generateDateTimeFromInitDate();
		Date date = new Date(rand.getTime());
		System.out.println(date);
	}

}
