package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.util.concurrent.ThreadLocalRandom;

public class Card {
	public static final String[] CARD_SUITS = {
		"HEARTS",
		"SPADES",
		"CLUBS",
		"DIAMONDS"
	};

	public static final String[] CARD_VALUES = {
		"TWO",
		"THREE",
		"FOUR",
		"FIVE",
		"SIX",
		"SEVEN",
		"EIGHT",
		"NINE",
		"TEN",
		"JACK",
		"QUEEN",
		"KING",
		"ACE"
	};

	private String suit;
	private String value;

	public Card(String newSuit, String newValue) {
		setSuit(newSuit);
		setValue(newValue);
	}

	public ArrayList<Integer> getNumericalValue() {
		ArrayList<Integer> ret = new ArrayList<Integer>();

		for (int i = 0; i < CARD_VALUES.length; i++) {
			if (value.equals(CARD_VALUES[i])) {
				if (i < 9) {
					ret.add(Integer.valueOf(i + 2));
				} else if (i == 12) {
					ret.add(Integer.valueOf(11));
					ret.add(Integer.valueOf(1));
				} else {
					ret.add(Integer.valueOf(10));
				}

				break;
			}
		}

		return ret;
	}

	public static String getRandomSuit() {
		return CARD_SUITS[ThreadLocalRandom.current().nextInt(0, CARD_SUITS.length)];
	}

	public static String getRandomValue() {
		return CARD_VALUES[ThreadLocalRandom.current().nextInt(0, CARD_VALUES.length)];
	}

	public String getSuit() {
		return suit;
	}

	private void setSuit(String newSuit) {
		for (String i : CARD_SUITS) {
			if (newSuit.equals(i)) {
				suit = i;
				return;
			}
		}

		throw new IllegalArgumentException("Illegal suit string provided");
	}

	public String getValue() {
		return value;
	}

	private void setValue(String newValue) {
		for (String i : CARD_VALUES) {
			if (newValue.equals(i)) {
				value = i;
				return;
			}
		}

		throw new IllegalArgumentException("Illegal value string provided");
	}

	public String toString() {
		return String.format("%s %s", suit, value);
	}
}

