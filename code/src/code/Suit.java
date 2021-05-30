package code;

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
