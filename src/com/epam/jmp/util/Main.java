package com.epam.jmp.util;

import java.sql.Date;
import java.sql.Timestamp;


public class Main {

	public static void main(String[] args) throws Exception{
		DateGenerator dg = new DateGenerator();
		while (true){
			Timestamp rand =dg.generateDateTimeFromInitDate();
			Date date = new Date(rand.getTime());
			System.out.println(date);
		}

	}

}
