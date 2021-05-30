package code;

import java.util.ArrayList;
import java.util.List;

public class Project {

	public static void main(String[] args) {
		
		List<Card> t = new ArrayList<>();
		List<Card> h = new ArrayList<>();
		t.add(new Card(5, Suit.CLUBS));
		t.add(new Card(10, Suit.CLUBS));
		t.add(new Card(12, Suit.CLUBS));
		t.add(new Card(13, Suit.CLUBS));
		t.add(new Card(14, Suit.CLUBS));
		h.add(new Card(1, Suit.CLUBS));
		h.add(new Card(9, Suit.CLUBS));
		
		
		Table game = new Table(h,t);
		System.out.println(game);
		
	}
	
}
