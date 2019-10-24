package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
import java.util.Map;

public class TweetGenerator {

	private static final int TWEET_LENGTH = 30;
	private static ArrayList<Word> words;
	private static Random random = new Random();

	public static void main(String[] args) throws IOException {
		new TweetGenerator();
		System.out.println("Done.");
	}

	public TweetGenerator() throws IOException {
		ArrayList<String> cleaned = loadData();
		words = findWords(cleaned);
		System.out.println(createTweet(TWEET_LENGTH));
	}

	private ArrayList<String> loadData() throws IOException {
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File("cleaned.txt")));
		String line = "";

		while ((line = br.readLine())!= null) {
			String[] tokens = line.split(" ");

			for (String t: tokens) {
				data.add(t);
			}
		}

		br.close();

		return data;
	}

	public String createTweet(int numWords) {
		String ret = null;

		int randIdx = random.nextInt(words.size() - 1);
		Word curWord = words.get(randIdx);

		for (int i = 0; i < numWords; i++) {
			//Handle null pointer
			while (curWord == null && words.size() > 1)
				curWord = words.get(random.nextInt(words.size() - 1));
			if (ret == null)
				ret = curWord.getWord();
			else
				ret += " " + curWord.getWord();
			curWord = getWord(curWord.getRandomFollower());
		}

		return ret;
	}

	private Word getWord(String word) {
		for (Word w: words) {
			if (w.getWord().equalsIgnoreCase(word)) {
				return w;
			}
		}
		return null;
	}

	public ArrayList<Word> findWords(ArrayList<String> cleaned) {
		Hashtable<String, Integer> wordIndexTable = new Hashtable<String, Integer>();
		ArrayList<Word> retList = new ArrayList<Word>();
		int prevWordIndex = -1;

		for (String word : cleaned) {
			String wordLC = word.toLowerCase();
			Integer wordIndex = wordIndexTable.get(wordLC);

			if (wordIndex == null) {
				retList.add(new Word(word));
				wordIndex = Integer.valueOf(retList.size() - 1);
				wordIndexTable.put(wordLC, wordIndex);
			}

			Word wordObject = retList.get(wordIndex.intValue());
			wordObject.incrementFrequency();
			retList.set(wordIndex.intValue(), wordObject);

			//Add a follower to the previous word
			if (prevWordIndex >= 0) {
				Word prevWordObject = retList.get(prevWordIndex);
				/*
				 * We would run this check if we used the default implementation of Word class.
				 * The behavior of duplicate entries was not defined in the default implementation.
				 * Modified implementation keeps track of the frequency of following words and avoids duplicate entries.
				 * This allows for more realistic tweet generation at low performance penalty.
				*/
				//if (!prevWordObject.followerExists(word))
				prevWordObject.addFollower(word);
				retList.set(prevWordIndex, prevWordObject);
			}

			prevWordIndex = wordIndex.intValue();
		}

		return retList;
	}    
}
