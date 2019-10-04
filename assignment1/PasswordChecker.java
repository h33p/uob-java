package com.bham.pij.assignments.passwordchecker;

import java.util.Scanner;

public class PasswordChecker {
	public static void main(String[] args) {
		PasswordChecker instance = new PasswordChecker();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter a password to check.");
		String password = in.nextLine();

		System.out.println(instance.checkPassword(password));
	}

	public String checkPassword(String input) {
		
		int length = input.length();

		if (length < 8)
			return "TOO SHORT";
		else if (length > 12)
			return "TOO LONG";

		boolean hasLower = false;
		boolean hasUpper = false;

		for (int i = 0; i < length; i++) {
			char c = input.charAt(i);
			
			if (Character.isLowerCase(c))
				hasLower = true;
			else if (Character.isUpperCase(c))
				hasUpper = true;
			else if (c != '_' && !Character.isDigit(c))
				return "WRONG CHARACTERS";
		}

		if (Character.isDigit(input.charAt(0)))
			return "LEADING DIGIT";

		if (!hasLower || !hasUpper)
			return "NOT MIXED CASE";

		return "OK";
	}
}

