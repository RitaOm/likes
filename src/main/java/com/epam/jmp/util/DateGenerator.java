package com.epam.jmp.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

public class DateGenerator {

	private AgeStatistics statistics;

	private Random random = new Random();

	public DateGenerator() {
		String[] ageRanges = Properties.getStringArr("ageRanges");
		String[] ageStatistics = Properties.getStringArr("ageStatistics");
		statistics = new AgeStatistics(ageRanges, ageStatistics);
	}

	public Date generateBirthDate() {
		Date date = null;
		int ageMode = random.nextInt(100);
		for (int i = 0; i < statistics.getAgeStatistics().length - 1; i++) {
			if (ageMode >= statistics.getAgeStatistics()[i]
					&& ageMode <= statistics.getAgeStatistics()[i + 1]) {
				int upperBound_yearsAgo = statistics.getAgeRanges()[i];
				int yearsInterval = statistics.getAgeRanges()[i+1] - upperBound_yearsAgo;				
				date = generateDate(upperBound_yearsAgo, yearsInterval);
			}
		}
		return date;
	}

	public Date generateDate(int upperBound_yearsAgo, int yearsInterval) {
		Timestamp ts = generateDateTime(upperBound_yearsAgo, yearsInterval);
		Date date = new Date(ts.getTime());
		return date;
	}

	public Timestamp generateDateTime(int upperBound_yearsAgo, int yearsInterval) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -upperBound_yearsAgo);
		long end = cal.getTime().getTime();
		cal.add(Calendar.YEAR, -yearsInterval);
		long offset = cal.getTime().getTime();
		long diff = end - offset + 1;
		Timestamp ts = new Timestamp(offset + (long)(Math.random() * diff));
		return ts;
	}
	
	public Timestamp generateDateTimeFromInitDate(long offset) {
		Calendar cal = Calendar.getInstance();
		long end = cal.getTime().getTime();	
		long diff = end - offset + 1;
		Timestamp ts = new Timestamp(offset + (long)(Math.random() * diff));
		return ts;
	}
	
	public Timestamp generateDateTimeFromInitDate() {
		String initDateTime = Properties.getString("initDateTime");	
		long offset = Timestamp.valueOf(initDateTime).getTime();
		return generateDateTimeFromInitDate(offset);
	}

	class AgeStatistics {
		private int[] ageRanges;
		private int[] ageStatistics;

		public AgeStatistics(String[] ageRanges, String[] ageStatistics) {
			this.ageRanges = ArrayUtils.stringArrayToInt(ageRanges);
			this.ageStatistics = ArrayUtils.stringArrayToInt(ageStatistics);
		}

		public int[] getAgeRanges() {
			return ageRanges;
		}

		public int[] getAgeStatistics() {
			return ageStatistics;
		}

	}
}
