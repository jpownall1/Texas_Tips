package code;

import java.util.ArrayList;
import java.util.List;

/**
 * Class what takes the cards on the (theoretical) table for one player and finds 
 * out their highest hand rank.
 * Designed to help people who do not have a good understanding of poker hand rankings.
 *
 * @version 1.0 24/08/2021
 *
 * @author Jordan Pownall (jord_pownall@hotmail.co.uk)
 *
 * Copyright (c) Jordan Pownall 2021
 */

public class Table {

	private final List<Card> hand;
    private final List<Card> table;
    private final int royalStraightVals[] = {1,10,11,12,13};
	
    /**
     * Constructor
     * @param h List of cards in the players hand (has to have length 2)
     * @param t List of cards currently on the table (has to have length 1-5)
     */
    public Table(List<Card> h, List<Card> t) {
    	this.hand = h;
    	this.table = t;
    }
    
    /**
     * get method
     * @return List of cards in players hand
     */
    public List<Card> getHand() {
		return hand;
	}
    
    /**
     * get method
     * @return List of cards on the table
     */
    public List<Card> getTable() {
		return table;
	}
    
    /**
     * combine method to combine cards in hand on table into list
     * @return List of cards in game for player
     */
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
    
    /**
     * converts combine() to list of the values
     * @return
     */
    public List<Integer> combineVals() {
    	List<Integer> tableVals = new ArrayList<>();
    	for (Card cH : this.combine()) { tableVals.add(cH.getValue()); }
    	if (tableVals.contains(1)) { tableVals.add(14); }
    	return tableVals;
    }
    
    /**
     * checks if there are any duplicates in the game for error checking
     * @return Boolean true if there are duplicate cards.
     */
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
    
    /**
     * this method checks how many matches of values each card in the hand has, so the first value says
     * how many the first hand card has, second vice versa. So checkFor(2) would check for a pair.
     * @param amount Integer representing what 'of a kind' to check for
     * @return Boolean true of the 'of a kind' is present in the game for the player
     */
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
    
    /**
     * checks if player has a two pair
     * @return Boolean true if player has a two pair
     */
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
    
    /**
     * method to find the straight of cards if the player has one
     * @return List of cards in straight for player.
     */
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
    
    /**
     * method to check if player has a Flush.
     * @return Boolean true if player has a flush
     */
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
	
    /**
     * method to check for a full house, so checks for a pair and a 3 of a kind.
     * @return Boolean true if player has a full house
     */
    public boolean checkForFull() {
    	if (this.checkFor(2) && this.checkFor(3)) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * method to check for a straight flush (if one card has a straight and
     * a flush
     * @return Boolean true if the player has a straight flush
     */
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
    
    /**
     * method to check for a straight flush (if one card has a straight and
     * a flush
     * @return Boolean true if the player has a royal flush
     */
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
    
    /**
     * toString method to return the string with the highest hand ranking to be displayed
     */
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