package com.bham.pij.assignments.legacycleaner;

import java.util.Scanner;

public class LegacyCleaner {
	public static void main(String[] args) {
		LegacyCleaner instance = new LegacyCleaner();
		Scanner in = new Scanner(System.in);

		while (true) {
			String line = in.nextLine();
			String[] cleaned = instance.clean(line);

			if (cleaned == null)
				break;

			System.out.print(cleaned[0]);

			for (int i = 1; i < 4; i++)
				System.out.print(String.format(", %s", cleaned[i]));

			System.out.print("\n");
		}
	}

	public String[] clean(String input) {
		if (input == null)
			return null;

		String[] ret = new String[4];
		String[] spstr = input.split(",");

		for (int i = 0; i < spstr.length; i++) {
			String str = spstr[i].trim();

			if (ret[0] == null && isName(str))
				ret[0] = str;
			else if (ret[1] == null && checkMask(str, "aaadddd".toCharArray()))
				ret[1] = str;
			else if (ret[2] == null && isResult(str))
				ret[2] = str;
			else if (ret[3] == null && isAddress(str))
				ret[3] = str;
		}

		for (int i = 0; i < 4; i++)
			if (ret[i] != null)
				return ret;

		return null;
	}

	private boolean isResult(String str) {
		return (str.equals("MERIT") || str.equals("PASS") || str.equals("FAIL"));
	}

	private boolean isName(String str) {
		int slen = str.length();

		if (isResult(str))
			return false;

		for (int i = 0; i < slen; i++) {
			char sc = str.charAt(i);
			if (sc != ' ' && !Character.isLetter(sc))
				return false;
		}

		return slen != 0;
	}

	private boolean checkMask(String str, char[] mask) {
		if (str.length() != mask.length)
			return false;

		int plen = str.length();

		for (int i = 0; i < plen; i++) {
			char sc = str.charAt(i);
			char mc = mask[i];
			if (mc == 'a' && Character.isLetter(sc))
				continue;
			else if (mc == 'd' && Character.isDigit(sc))
				continue;
			return false;
		}

		return true;
	}

	private boolean isAddress(String str) {
		int slen = str.length();

		if (isResult(str))
			return false;

		for (int i = 0; i < slen; i++) {
			char sc = str.charAt(i);
			if (!Character.isDigit(sc) && !Character.isLetter(sc))
				return false;
		}

		return slen != 0;
	}
}

