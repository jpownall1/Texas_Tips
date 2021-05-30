package code;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private final List<Card> hand;
    private final List<Card> table;
	
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
    
    public boolean checkForStraight() {
    	
    	int fVal = hand.get(0).getValue();
    	int sVal = hand.get(1).getValue();
    	List<Integer> allCards = new ArrayList<>();
    	for (Card cH : this.combine()) {
    		allCards.add(cH.getValue());
    	}
    	List<Integer> setOne = new ArrayList<>();
    	List<Integer> setTwo = new ArrayList<>();
    	for (int i=-4; i<=4; i++) {
    		if (i!=0) {
    			if (((fVal + i) > 0) && ((fVal + i) < 14) && (!setOne.contains(fVal + i))) {
    				setOne.add(fVal + i);
    			}
    			if (((fVal + i) == 14) && (!setOne.contains(fVal + i))) {
    				setOne.add(1);
    			}
    			if (((sVal + i) > 0) && ((sVal + i) < 14) && (!setTwo.contains(sVal + i))) {
    				setTwo.add(sVal + i);
    			}
    			if (((sVal + i) == 14) && (!setOne.contains(sVal + i))) {
    				setTwo.add(1);
    			}
    		}
    		if (i == 0) {
				setOne.add(fVal);
				setTwo.add(sVal);
    		}
    	}
    	for (int j = 0; j<=4; j++) {
    		List<Integer> straightOne = new ArrayList<>();
    		List<Integer> straightTwo = new ArrayList<>();
    		int countOne = 0;
        	int countTwo = 0;
    		for (int l = 0; l<=4; l++) {
    			if (l + j < setOne.size()) { straightOne.add(setOne.get(l + j)); }
    			if (l + j < setTwo.size()) { straightTwo.add(setTwo.get(l + j)); }
        	}
    		for (Integer comp : allCards) {
    			if (straightOne.contains(comp)) {
    				countOne++;
    			}
    			if (straightTwo.contains(comp)) {
    				countTwo++;
    			}
    		}
    		if (countOne == 5 || countTwo == 5) {
    			return true;
    		}
    		straightOne.clear();
    		straightTwo.clear();
    	}
    	
    	return false;
    }
    
    public boolean checkForFlush() {
    	
    	Integer[] counts = {0,0};
    	for (Card c : this.combine()) {
    		if (c.getSuit() == hand.get(0).getSuit()) {
    			counts[0] = counts[0] + 1;
    		}
    		if (c.getSuit() == hand.get(1).getSuit()) {
    			counts[1] = counts[1] + 1;
    		}
    	}
    	if (counts[0] >= 5 || counts[1] >= 5) {
    		return true;
    	}
    	
    	return false;
    }
	
    public boolean checkForFull() {
    	if (this.checkFor(2) && this.checkFor(3)) {
    		return true;
    	}
    	return false;
    }
    
    public boolean checkForSF() {
    	if (this.checkForFlush() && this.checkForStraight()) {
    		return true;
    	}
    	return false;
    }
    
    public boolean checkForRF() {
    	
    	List<Integer> straightVals = new ArrayList<>();
    	List<Integer> gameVals = new ArrayList<>();
    	int count = 0;
    	for (Card c : this.combine()) { gameVals.add(c.getValue()); };
    	straightVals.add(1);
    	for (int i=0; i<=3; i++) { straightVals.add(i + 10); }
    	for (Integer i : straightVals) {
    		if (gameVals.contains(i)) {
    			count++;
    		}
    	}
    	if ((straightVals.contains(hand.get(0).getValue()) || straightVals.contains(hand.get(1).getValue())) && count == 5) {
    		return true;
    	}
    	return false;
    	
    }
    
    public String toString() {
    	
    	if (this.checkForRF()) { return "You have a Royal Flush!"; }
    	if (this.checkForSF()) { return "You have a Straight Flush!"; }
    	if (this.checkFor(4)) { return "You have 4 of a Kind!"; }
    	if (this.checkForFull()) { return "You have a Full House!"; }
    	if (this.checkForFlush()) { return "You have a Flush!"; }
    	if (this.checkForStraight()) { return "You have a Straight!"; }
    	if (this.checkFor(3)) { return "You have Three of a Kind!"; }
    	if (this.checkForTwoPair()) { return "You have a Two Pair!"; }
    	if (this.checkFor(2)) { return "You have a Pair!"; }
    	if (hand.get(0).getValue() > hand.get(1).getValue()) {
    		return " You have high card " + hand.get(0).getValue();
    	}
    	return " You have high card " + hand.get(1).getValue();
    	
    	
    	
    }
    
}
