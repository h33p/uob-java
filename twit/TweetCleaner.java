package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class TweetCleaner {

	private static ArrayList<String> raw = new ArrayList<String>();
	private static ArrayList<String> cleaned = new ArrayList<String>();

	private static final int MIN_ELIPSIS_PERIODS = 3;
	
	public static void main(String[] args) throws IOException {
		new TweetCleaner();
		System.out.println("Done.");
	}
	
	public TweetCleaner() throws IOException {
		loadRaw();
		clean();
		saveClean();
	}

	private void clean() {
		for (String line: raw) {
			String cln = clean(line);

			if (cln != null) {
				String[] toks = cln.split(" ");
				
				for (String s: toks) {
					addClean(s);			
				}	
			}
		}
	}

	private String doClean(String input) {
		if (input == null || input.length() == 0)
			return null;

		//Reject retweets
		if (input.equals("rt") || input.equals("RT"))
			return null;

		//Reject digits, hashtags, names, URLs and other jazz. We let through only letters and punctuation characters
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isLetter(input.charAt(i))) {
				switch (input.charAt(i)) {
					case ',':
					case ':':
					case ';':
					case '.':
					case '?':
					case '!':
					case '(':
					case ')':
					case '"':
					case '\'':
						continue;
				}

				return null;
			}
		}

		//Clean the elipsis
		for (int i = 0; i < input.length() - MIN_ELIPSIS_PERIODS + 1; i++) {
			if (input.substring(i, i + MIN_ELIPSIS_PERIODS).equals("...")) {
				int end = i + MIN_ELIPSIS_PERIODS;
				for (; end < input.length(); end++) {
					if (input.charAt(end) != '.') {
						end--;
						break;
					}
				}
				return doClean(input.substring(0, i) + input.substring(end, input.length()));
			}
		}

		//Clean the punctuation
		if (input.length() == 1) {
			switch (input.charAt(0)) {
				case ',':
				case ':':
				case ';':
				case '.':
				case '?':
				case '!':
				case '(':
				case ')':
					return null;
			}
		}

		//More punctuation
		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
				case ',':
				case ':':
				case ';':
				case '.':
				case '(':
				case ')':
					return doClean(input.substring(0, i) + input.substring(i + 1, input.length()));
			}
		}

		//Remove hyphens
		for (int i = 0; i < input.length(); i++)
			if (input.charAt(i) == '-')
				return doClean(input.substring(0, i) + input.substring(i + 1, input.length()));

		return input;
	}

	public String clean(String input) {
		if (input == null)
			return null;

		String[] lines = input.split("\n");
		String ret = null;

		for (String line : lines) {
			String[] toks = line.split(" ");

			for (String tok : toks) {
				String retToken = doClean(tok.trim());

				if (retToken != null) {
					if (ret == null)
						ret = retToken;
					else
						ret += " " + retToken;
				}
			}
		}

		return ret;
	}
	

	private void addClean(String clean) {
		cleaned.add(clean);
	}
	
	private void saveClean() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("cleaned.txt");
		
		for (String s: cleaned) {
			pw.print(s + " ");
		}
		
		pw.close();
	}
	
	private void loadRaw() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("donald.txt")));
		
		String line = "";
		
		while ((line = br.readLine())!= null) {
			raw.add(line);
		}
		
		br.close();
	}
}
