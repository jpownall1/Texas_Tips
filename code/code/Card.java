package code;

/**
 * Public class to create objects what represent a card.
 *
 * @version 1.0 24/08/2021
 *
 * @author Jordan Pownall (jord_pownall@hotmail.co.uk)
 *
 * Copyright (c) Jordan Pownall 2021
 */

public class Card {

    private final int value;
    private final Suit suit;
    
    /**
     * Constructor - takes value and suit to create a card object
     * @param v The value of the card (between 1 - 13)
     * @param s The suit of the card
     * @throws IllegalArgumentException if the value is out of bounds.
     */
    public Card(int v, Suit s) throws IllegalArgumentException {
        if (v <= 13 && v >= 1) {
        	this.value = v;
        	this.suit = s;
        } else {
        	throw new IllegalArgumentException("Card value must be between 1 and 13.");
        }
    }

    /**
     * get method
     * @return value of card
     */
    public int getValue() {
        return this.value;
    }

    /**
    * get method
    * @return value of card
    */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * toString method presents card details using official names along with suits.
     */
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