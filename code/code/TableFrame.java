package code;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TableFrame extends JFrame {

	public TableFrame() {
		
		//basic setting up GUI size to full screen
		Toolkit tools = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = tools.getScreenSize();
		
		setTitle("Texas Tips - Table");
		setSize(screenDimensions.width, screenDimensions.height);
		setLocation(new Point(screenDimensions.width/2, screenDimensions.height/2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//splitting screen into two parts; one being the hand, the other being the table
		JPanel handPanel = new JPanel();         // hand component
		handPanel.setLayout(new GridLayout(0,2));
        JPanel tablePanel = new JPanel();      // table component
        tablePanel.setLayout(new GridLayout(0,5));
        JPanel resultsPanel = new JPanel();      // results component
        resultsPanel.setLayout(new GridLayout(0,1));
        
		//setting up combo boxes and buttons
        JButton clear = new JButton("Clear");	//presses to clear all card options
		JButton submit = new JButton("Submit");	//presses to submit cards to determine results
		JTextArea textArea = new JTextArea();	//where the user will see results
		
		resultsPanel.add(clear);
		resultsPanel.add(submit);
		resultsPanel.add(textArea);

		
		JComboBox[] suitComboBoxes = new JComboBox[7];
		for (int i = 0; i < 7; i++) {
			suitComboBoxes[i] = new JComboBox();
			suitComboBoxes[i].addItem("null");
			suitComboBoxes[i].addItem("CLUBS");
			suitComboBoxes[i].addItem("SPADES");
			suitComboBoxes[i].addItem("HEARTS");
			suitComboBoxes[i].addItem("DIAMONDS");
			suitComboBoxes[i].setEditable(true);
			if (i < 2) {
				handPanel.add(suitComboBoxes[i]);
			} else {
				tablePanel.add(suitComboBoxes[i]);
			}
			
		}
		
		JComboBox[] valueComboBoxes = new JComboBox[7];
		for (int i = 0; i < 7; i++) {
			valueComboBoxes[i] = new JComboBox();
			valueComboBoxes[i].addItem("null");
			valueComboBoxes[i].addItem("ACE");
			valueComboBoxes[i].addItem("2");
			valueComboBoxes[i].addItem("3");
			valueComboBoxes[i].addItem("4");
			valueComboBoxes[i].addItem("5");
			valueComboBoxes[i].addItem("6");
			valueComboBoxes[i].addItem("7");
			valueComboBoxes[i].addItem("8");
			valueComboBoxes[i].addItem("9");
			valueComboBoxes[i].addItem("10");
			valueComboBoxes[i].addItem("JACK");
			valueComboBoxes[i].addItem("QUEEN");
			valueComboBoxes[i].addItem("KING");
			valueComboBoxes[i].setEditable(true);
			if (i < 2) {
				handPanel.add(valueComboBoxes[i]);
			} else {
				tablePanel.add(valueComboBoxes[i]);
			}
		}
		
		handPanel.setSize(new Dimension((screenDimensions.width/5)*2, screenDimensions.height/3));
		tablePanel.setSize(new Dimension(screenDimensions.width, screenDimensions.height/3));
		resultsPanel.setSize(new Dimension(screenDimensions.width, screenDimensions.height/3));
		this.setLayout(new GridLayout(0,1));
		this.add(handPanel); //, BorderLayout.NORTH
		this.add(tablePanel); //, BorderLayout.CENTER
		this.add(resultsPanel); //, BorderLayout.SOUTH
		
	}
	
	public static void main(String[] args) {
		TableFrame frame = new TableFrame();
		frame.setVisible(true);
	}
	
}
