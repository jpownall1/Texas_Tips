package code;

public class Card {

    private final int value;
    private final Suit suit;
    
    public Card(int v, Suit s) throws IllegalArgumentException {
        if (v <= 13 && v >= 1) {
        	this.value = v;
        	this.suit = s;
        } else {
        	throw new IllegalArgumentException("Card value must be between 1 and 13.");
        }
    }

    public int getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public String toString() {
        
    	String s = "";
    	if (value == 1) { s.concat("Ace"); } 
    	else if (value == 11) { s.concat("Jack"); }
    	else if (value == 12) { s.concat("Queen"); }
    	else if (value == 13) { s.concat("King"); }
    	else { s.concat(String.valueOf(value)); }
    	s.concat(" of " + suit);
    	return s;
    	
    }
    
}