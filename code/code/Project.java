package code;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to run my projects graphical user interface and CLI.
 *
 * @version 1.0 24/08/2021
 *
 * @author Jordan Pownall (jord_pownall@hotmail.co.uk)
 *
 * Copyright (c) Jordan Pownall 2021
 */

public class Project {
	
	public static void main(String[] args) {
		
		startCLI();
		startGUI();
		
	}
	
	/**
	 * Start the simple CLI test values in method subject to change.
	 */
	public static void startCLI() {
		
		List<Card> t = new ArrayList<>();
		List<Card> h = new ArrayList<>();
		t.add(new Card(4, Suit.SPADES));
		t.add(new Card(5, Suit.HEARTS));
		t.add(new Card(10, Suit.CLUBS));
		t.add(new Card(8, Suit.CLUBS));
		t.add(new Card(9, Suit.CLUBS));
		h.add(new Card(12, Suit.CLUBS));
		h.add(new Card(11, Suit.CLUBS));
		
		Table game = new Table(h,t);
		System.out.println(game);
		
	}
	
	public static void startGUI() {
		
		AbstractTableFrame run = new TableFrame();
		run.setVisible(true);
		
	}
	
}
