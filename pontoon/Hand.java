package com.bham.pij.assignments.pontoon;

import com.bham.pij.assignments.pontoon.Card;
import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card c) {
		cards.add(c);
	}
	
	public Card getCard(int i) {
		return cards.get(i);
	}

	public int getHandSize() {
		return cards.size();
	}

	public ArrayList<Integer> getNumericalValue() {
		ArrayList<Integer> ret = new ArrayList<Integer>();

		ret.add(0);

		for (int i = 0; i < getHandSize(); i++) {
			ArrayList<Integer> cardValues = getCard(i).getNumericalValue();

			ArrayList<Integer> newRet = new ArrayList<Integer>();

			//This will split the numerical values when an ace is encountered
			for (Integer o : cardValues) {
				int cardValue = o.intValue();

				for (Integer u : ret) {
					int totalValue = u.intValue() + cardValue;

					if (!newRet.contains(totalValue)) {
						newRet.add(totalValue);
					}
				}
			}

			ret = newRet;
		}

		return ret;
	}

	public String showHand() {
		String ret = null;

		for (int i = 0; i < getHandSize(); i++) {
			if (ret == null) {
				ret = "";
			} else {
				ret += "; ";
			}
			ret += getCard(i).toString();
		}

		return ret != null ? ret : "Empty";
	}
}

