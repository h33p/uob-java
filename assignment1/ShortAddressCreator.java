package com.bham.pij.assignments.shortaddresscreator;

import java.util.Scanner;

public class ShortAddressCreator {
	public static void main(String[] args) {
		ShortAddressCreator instance = new ShortAddressCreator();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter the address to shorten.");
		String address = in.nextLine();

		System.out.println(instance.createShortAddress(address));
	}

	public String createShortAddress(String input) {
		if (input == null)
			return null;

		/* BUG: we need to add whitespace checking after the comma */
		String[] parts = input.split(",");

		if (parts.length < 2)
			return null;

		for (int i = 0; i < parts.length; i++)
			parts[i] = parts[i].trim();

		String postcode = parts[parts.length - 1];
		int plen = postcode.length();
		char[] format = "adddaa".toCharArray();

		if (plen != format.length)
			return null;

		for (int i = 0; i < plen; i++) {
			if (format[i] == 'a' && Character.isLetter(postcode.charAt(i)))
				continue;
			else if (format[i] == 'd' && Character.isDigit(postcode.charAt(i)))
				continue;
			return null;
		}

		return parts[0] + " " + postcode;
	}
}

