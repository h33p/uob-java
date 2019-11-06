package com.bham.pij.assignments.pontoon;

import com.bham.pij.assignments.pontoon.Card;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private static final int DECK_SIZE = 52;

	private ArrayList<Card> cards;
	private boolean[] dealtStatus;

	public Deck() {
		cards = new ArrayList<Card>(DECK_SIZE);
		dealtStatus = new boolean[DECK_SIZE];

		for (int i = 0; i < DECK_SIZE; i++) {
			int suit = i / 13;
			int value = i % 13;
			cards.add(new Card(Card.CARD_SUITS[suit], Card.CARD_VALUES[value]));
		}

		Collections.shuffle(cards);
	}

	public Card dealCard(int i) {
		if (dealtStatus[i])
			return null;

		dealtStatus[i] = true;
		return getCard(i);
	}

	public Card getCard(int i) {
		return cards.get(i);
	}

	public int getDeckSize() {
		return DECK_SIZE;
	}

	public void reset() {
		for (int i = 0; i < DECK_SIZE; i++)
			dealtStatus[i] = false;
	}
}

