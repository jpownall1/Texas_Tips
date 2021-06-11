package code;

import java.util.ArrayList;
import java.util.List;

public class Project {

	public static void main(String[] args) {
		
		List<Card> t = new ArrayList<>();
		List<Card> h = new ArrayList<>();
		t.add(new Card(4, Suit.SPADES));
		t.add(new Card(5, Suit.HEARTS));
		t.add(new Card(6, Suit.CLUBS));
		t.add(new Card(7, Suit.SPADES));
		t.add(new Card(8, Suit.SPADES));
		h.add(new Card(9, Suit.CLUBS));
		h.add(new Card(10, Suit.CLUBS));
		
		
		Table game = new Table(h,t);
		System.out.println(game);
		
	}
	
}
