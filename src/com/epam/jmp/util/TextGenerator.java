package com.epam.jmp.util;

import java.util.Random;

public class TextGenerator {

	private int nameMaxLength;

	private int nameMinLength;

	private int postMaxLength;

	private String vowels = "aeiouy";

	private String consonants = "bcdfghjklmnpqrstvwxz";

	private String[] space = { " " };

	private String[] insidePunctuation = { " ", ", ", " - ", ": " };

	private String[] endPunctuation = { ". ", "! ", "? " };

	private String[] smiles = { " =) ", " =/ ", " T_T ", " ^_^ ", " *-* ",
			" =( " };

	private Random random = new Random();

	public TextGenerator() {
		this.nameMaxLength = Properties.getNumber("nameMaxLength");
		this.nameMinLength = Properties.getNumber("nameMinLength");
		this.postMaxLength = Properties.getNumber("postMaxLength");
	}

	public String generateString(boolean isUpper) {
		StringBuilder str = new StringBuilder();
		if (isUpper) {
			str.append(getRandomLetter().toUpperCase());
		} else {
			str.append(getRandomLetter());
		}
		int strLength = random.nextInt(nameMaxLength - nameMinLength + 1)
				+ nameMinLength;
		for (int i = 0; i < strLength - 1; i++) {
			str.append(getRandomLetter());
		}
		return str.toString();
	}
	
	public String generateName() {
		return generateString(true);
	}
	
	public String generateTextMessage() {
		StringBuilder str = new StringBuilder();
		while (true) {
			StringBuilder sentence = generateSentence();
			if (str.length() + sentence.length() <= postMaxLength) {
				str.append(sentence);
			} else if (str.length()==0){
				continue;
			} else {
				break;
			}
		}
		return str.toString();
	}

	public StringBuilder generateSentence() {
		StringBuilder str = new StringBuilder();
		str.append(generateString(true));		
		while (true) {
			int indexMeaningUseSmilesOrNot = random.nextInt(2) == 0 ? 6 : 5;
			int delimiter = random.nextInt(indexMeaningUseSmilesOrNot);
			switch (delimiter) {
			case 0:
			case 1:
			case 2:
				str = appendDelimiter(str, space);
				break;
			case 3:
				str = appendDelimiter(str, insidePunctuation);
				break;
			case 4:
				str = appendDelimiter(str, endPunctuation);
				return str;
			case 5:
				str = appendDelimiter(str, smiles);
				break;
			}
			str.append(generateString(false));
		}
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
	
	private StringBuilder appendDelimiter(StringBuilder str,
			String[] delimiterArr) {
		int index = random.nextInt(delimiterArr.length);
		str.append(delimiterArr[index]);
		return str;
	}
}
