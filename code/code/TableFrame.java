package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

/**
 * Extends class AbstractTableFrame. Provides functionality for components of GUI.
 *
 * version 1.0 27/08/2021
 *
 * @author Jordan Pownall (jord_pownall@hotmail.co.uk)
 *
 * Copyright (c) Jordan Pownall 2021
 */

public class TableFrame extends AbstractTableFrame implements ActionListener {

	public int convertToInt(String i) {
		
		if (i == "ACE") { return 1; }
		else if (i == "JACK") { return 11; }
		else if (i == "QUEEN") { return 12; }
		else if (i == "KING") { return 13; }
		else { return Integer.valueOf(i); }
		
	}
	
	
	public void addListeners() {
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateResults();
        	}
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFilters();
        	}
		});
		
	}
	
	//this method converts inputs from combo boxes into cards and puts them into the Table to present
	//results in appropriate section
	public void updateResults() {
		
		textArea.setText("");
		List<Card> hand = new ArrayList<>();
        List<Card> table = new ArrayList<>();
        
        for (int i = 0; i < 7; i++) {
        	String suitS = suitComboBoxes[i].getSelectedItem().toString();
        	String value = valueComboBoxes[i].getSelectedItem().toString();
        	if (suitS != "null" && value != "null") {
	        	Suit suit = Suit.valueOf(suitS.toUpperCase());
	        	if (i < 2) {
	        		hand.add(new Card(convertToInt(value), suit));
	        	} else {
	        		table.add(new Card(convertToInt(value), suit));
	        	}
        	}
        }
        String result = new Table(hand, table).toString();
        textArea.append(result);
	}
	
	public void clearFilters() {
		
		for (JComboBox i : suitComboBoxes) {
			i.setSelectedItem("null");
		}
		for (JComboBox i : valueComboBoxes) {
			i.setSelectedItem("null");
		}
		
	}
	
}
