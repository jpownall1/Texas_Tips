package code;

/**
 * Public Enum class for different card suits.
 *
 * @version 1.0 24/08/2021
 *
 * @author Jordan Pownall (jord_pownall@hotmail.co.uk)
 *
 * Copyright (c) Jordan Pownall 2021
 */

public enum Suit {

    HEARTS("hearts"),
	DIAMONDS("diamonds"),
	CLUBS("clubs"),
	SPADES("spades");
	
	private final String suitName;
	
	Suit(String sName) { suitName = sName; }
	
	public String getName()
	{
	    return suitName;
	}
	    
}
