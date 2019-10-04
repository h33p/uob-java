package com.bham.pij.assignments.shortidcreator;

import java.util.Scanner;

public class ShortIDCreator {
	public static void main(String[] args) {
		ShortIDCreator instance = new ShortIDCreator();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter student's name.");
		String line = in.nextLine();

		System.out.println(instance.createID(line)); 
	}

	public String createID(String input) {

		if (input == null)
			return null;

		input = input.toLowerCase();
		String[] parts = input.split(" ");

		if (parts.length == 2)
			return parts[0].substring(0, 1) + "x" + parts[1].substring(0, 1);
		else if (parts.length == 3)
			return parts[0].substring(0, 1) + parts[1].substring(0, 1) + parts[2].substring(0, 1);

		return null;
	}
}

