package com.epam.jmp.generator;

import java.util.Random;

public class NameGenerator {

	private int nameMaxLength;

	private int nameMinLength;

	private String vowels = "aeiouy";

	private String consonants = "bcdfghjklmnpqrstvwxz";

	private Random random = new Random();

	public NameGenerator() {
	}

	public NameGenerator(int nameMinLength, int nameMaxLength) {
		this.nameMaxLength = nameMaxLength;
		this.nameMinLength = nameMinLength;
	}

	public String generateNameString() {
		StringBuilder name = new StringBuilder();
		name.append(getRandomLetter().toUpperCase());
		int nameLength = random.nextInt(nameMaxLength - nameMinLength + 1)
				+ nameMinLength;
		for (int i = 0; i < nameLength - 1; i++) {
			name.append(getRandomLetter());
		}
		return name.toString();
	}

	private String getRandomLetter() {
		int vowelsOrConsonants = random.nextInt(2);
		String randomLetter = null;
		switch (vowelsOrConsonants) {
		case 0:
			randomLetter = substringLetter(vowels);
			break;
		case 1:
			randomLetter = substringLetter(consonants);
			break;
		}
		return randomLetter;
	}

	private String substringLetter(String str) {
		int randomIndex = random.nextInt(str.length());
		return str.substring(randomIndex, randomIndex + 1);
	}
}
