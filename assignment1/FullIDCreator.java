package com.bham.pij.assignments.fullidcreator;

import java.util.Scanner;
import com.bham.pij.assignments.shortidcreator.ShortIDCreator;

public class FullIDCreator {
	
	private short[] counts = new short[26 * 26 * 26];

	public static void main(String[] args) {
		FullIDCreator instance = new FullIDCreator();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter student names.");

		while (true) {
			String input = in.nextLine();
			String fullID = instance.createFullID(input);

			if (fullID == null)
				break;

			System.out.println(fullID);
		}
	}

	public String createFullID(String input) {
		String shortID = new ShortIDCreator().createID(input);

		if (input == null)
			return null;

		int index = 0;

		for (int i = 0; i < 3; i++) {
			index *= 26;
			index += shortID.charAt(i) - 'a';
		}

		System.out.println(index);

		return String.format("%s%04d", shortID, counts[index]++);
	}
}

