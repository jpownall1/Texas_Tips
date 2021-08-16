package code;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private final List<Card> hand;
    private final List<Card> table;
    private final int royalStraightVals[] = {1,10,11,12,13};
	
    public Table(List<Card> h, List<Card> t) {
    	this.hand = h;
    	this.table = t;
    }
    
    public List<Card> getHand() {
		return hand;
	}
    
    public List<Card> getTable() {
		return table;
	}
    
    //returns array list of all the cards on the table for various uses
    public List<Card> combine() {
    	List<Card> allCards = new ArrayList<>();
    	for (Card cH : hand) {
    		allCards.add(cH);
    	}
    	for (Card cT : table) {
    		allCards.add(cT);
    	}
    	return allCards;
    }
    
    public List<Integer> combineVals() {
    	List<Integer> tableVals = new ArrayList<>();
    	for (Card cH : this.combine()) { tableVals.add(cH.getValue()); }
    	if (tableVals.contains(1)) { tableVals.add(14); }
    	return tableVals;
    }
    
    //checks if there are any duplicates in the game for error checking
    public boolean isThereDuplicates() {
    	boolean duplicates = false;
    	for (int j=0; j < this.combine().size(); j++) {
    		for (int k=j+1; k < this.combine().size(); k++) {
    			if (k!=j && combine().get(k).getSuit() == combine().get(j).getSuit() 
    					&& combine().get(k).getValue() == combine().get(j).getValue())
    				duplicates = true;
    		}
    	}
    	return duplicates;
    }
    
    //this method checks how many matches of values each card in the hand has, so the first value says
    //how many the first hand card has, second vice versa. So checkFor(2) would check for a pair.
    public boolean checkFor(int amount) {
    	Integer[] counts = {0,0};
    	for (int i=0;  i < 2; i++) {
    		for (Card cT : this.combine()) {
    			if (cT.getValue() == hand.get(i).getValue()) {
    				counts[i] = counts[i] + 1;
    			}
    		}
    	}
    	for (Integer count : counts) {
    		if (count == amount) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //checks for a two pair hand
    public boolean checkForTwoPair() {
    	Boolean[] pairs = {false,false};
    	for (int i=0;  i < hand.size(); i++) {
    		for (Card cT : table) {
    			if (cT.getValue() == hand.get(i).getValue()) {
    				pairs[i] = true;
    			}
    		}
    	}
		if (pairs[0] && pairs[1]) {
			return true;
		}
		return false;
		
    }
    
    //this method returns an array of the straight values.
    public List<Integer> checkForStraight() {
    	
    	List<Integer> straight = new ArrayList<>();
    	for (Card c : this.hand) {
    		straight.add(c.getValue());
    		boolean below = true;
    		boolean above = true;
    		int count = 1;
    		int aboveV = c.getValue()+1;
			int belowV = c.getValue()-1;
    		
    		while (below == true || above == true) {
    			if (combineVals().contains(aboveV) && above == true) {
    				straight.add(aboveV);
    				count++;
    				aboveV++;
    			} else { above = false; }
    			if (combineVals().contains(belowV) && below == true) {
    				straight.add(belowV);
    				count++;
    				belowV--;
    			} else { below = false; }
    		}
    		if (count < 5) { straight.clear(); } else { break; }
    	}
    	
    	return straight;
    }
    
    //this method checks for a flush
    public boolean checkForFlush() {
    	
    	Integer[] counts = {0,0};
    	for (Card c : combine()) {
    		if (c.getSuit() == hand.get(0).getSuit()) {
    			counts[0] = counts[0] + 1;
    		}
    		if (c.getSuit() == hand.get(1).getSuit()) {
    			counts[1] = counts[1] + 1;
    		}
    	}
    	if (counts[0] >= 5) { return true; }
    	if (counts[1] >= 5) { return true; }
    	
    	return false;
    }
	
    //this method checks for a full house, so checks for a pair and a 3 of a kind.
    public boolean checkForFull() {
    	if (this.checkFor(2) && this.checkFor(3)) {
    		return true;
    	}
    	return false;
    }
    
    //checks for a straight flush
    public boolean checkForSF() {
    	
    	if (checkForStraight().size() >= 5) {
    		for (Card c : hand) {
    			int count = 0;
    			if (checkForStraight().contains(c.getValue())) {
    				
    				for (Card i : combine()) {
    					if (checkForStraight().contains(i.getValue())) {
    						if (c.getSuit() == i.getSuit()) {
    							count++;
    						}
    					}
    				}
    			}
    			if (count >= 5) { return true; }
    		}
    	}
    	
    	return false;
    }
    
    //method to check for royal flush
    public boolean checkForRF() {
    	
    	if (!(checkForStraight().size() >= 5) || !checkForFlush()) {
    		return false;
    	}
    	boolean initial = false;
    	for (Card i : hand) {
    		for (int j : royalStraightVals) {
    			if (i.getValue() == j) {
    				initial = true;
    			}
    		}
    	}
    	if (initial == false) { return false; }
    	int straightCount = 0;
    	for (int i : royalStraightVals) {
    		if (this.combineVals().contains(i)) {
    			straightCount++;
    		}
    	}
    	if (straightCount < 5) { return false; }
    	int flushCount = 0;
    	for (Card i : hand) {
    		Suit suit = i.getSuit();
    		for (Card j : this.combine()) {
    			if (j.getSuit() == suit) {
    				flushCount++;
    			}
    		}
    	}
    	if (flushCount < 5) { return false; }
    	
    	return true;
    	
    }
    
    //toString method to return the string with the highest hand ranking to be displayed
    public String toString() {
    	
    	if (hand.size() != 2) { return "You need 2 cards in your hand."; }
    	else if (isThereDuplicates()) { return "You have duplicated cards."; }
    	else if (this.checkForRF()) { return "You have a Royal Flush!"; }
    	else if (this.checkForSF()) { return "You have a Straight Flush!"; }
    	else if (this.checkFor(4)) { return "You have 4 of a Kind!"; }
    	else if (this.checkForFull()) { return "You have a Full House!"; }
    	else if (this.checkForFlush()) { return "You have a Flush!"; }
    	else if (this.checkForStraight().size() >= 5) { return "You have a Straight!"; }
    	else if (this.checkFor(3)) { return "You have Three of a Kind!"; }
    	else if (this.checkForTwoPair()) { return "You have a Two Pair!"; }
    	else if (this.checkFor(2)) { return "You have a Pair!"; }
    	else if (hand.get(0).getValue() == 1 || hand.get(1).getValue() == 1) { return "You have high card Ace"; }
    	else if (hand.get(0).getValue() > hand.get(1).getValue()) {
    		return "You have high card " + hand.get(0).toString();
    	}
    	return "You have high card " + hand.get(1).toString();
    }
    
}