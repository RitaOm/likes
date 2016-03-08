package com.epam.jmp.generator;

public class Main {

	public static void main(String[] args) {
		NameGenerator gen = new NameGenerator(2,15);
		while (true) {
			System.out.println(gen.generateNameString() + " " + gen.generateNameString());
		}

	}

}
