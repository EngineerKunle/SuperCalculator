package com.tutorial.calulator;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

	// variables
	JPanel[] row = new JPanel[5];
	JButton[] button = new JButton[19];

	String[] btnString = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2",
			"3", "*", ".", "/", "C", "√", "+/-", "=", "0" };
	
	int[] dimW ={300, 45, 100, 90};
	int[] dimH = {35, 40};
	
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
	Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
	
	boolean[] function = new boolean[4];
	double[] temporary = {0, 0};
	
	JTextArea display = new JTextArea(1,20);
	
	Font font = new Font("Times new Roman", Font.BOLD, 14);
	
	Calculator(){
		super("Super Calculator");
		setDesign();
		
		//layout sections
		setSize(380, 250);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GridLayout gbl = new GridLayout(5,5);
		setLayout(gbl);
		
		for(int i = 0; i < 4; i++){
			function[i] = false;
		}
		
		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
		
		for(int i = 0; i < 5; i++){
			row[i] = new JPanel();
		}
		
		for(int i = 1; i < 5; i++ ){
			row[i].setLayout(f2);
		}
		
		for(int i = 0; i < 19; i++){
			button[i] = new JButton();
			button[i].setText(btnString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}
		display.setFont(font);
		display.setEditable(false);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setPreferredSize(displayDimension);
		
		for(int i = 0; i < 14; i++)
			button[i].setPreferredSize(regularDimension);
		
		for(int i = 14; i < 18; i++)
		    button[i].setPreferredSize(rColumnDimension);
		
		button[18].setPreferredSize(zeroButDimension);
		
		row[0].add(display);
		add(row[0]);
		
		for(int i = 0; i < 4; i++)
			row[1].add(button[i]);
		row[1].add(button[14]);
		add(row[1]);
		
		for(int i = 4; i < 8; i++)
			row[2].add(button[i]);
		row[2].add(button[15]);
		add(row[2]);
		
		for(int i = 8; i < 12; i++)
			row[3].add(button[i]);
		row[3].add(button[16]);
		add(row[3]);
		
		row[4].add(button[18]);
		
		for(int i = 12; i < 14; i++)
			row[4].add(button[i]);
		row[4].add(button[17]);
		add(row[4]);
		
		setVisible(true);
		
	}
	
	public void setDesign(){
		try {
			 UIManager.setLookAndFeel(
					 "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e) { 
			System.out.println("we have an error here " + e);
		}
	}
	
	public void clear(){
		try {
			display.setText(""); // Sets the display blank
			for(int i = 0; i < 4; i++)
				function[i] = false; // Sets the functions back to false
			for(int i = 0; i < 2; i++)
				 temporary[i] = 0; // Sets our temporary variables back to 0

		}catch(NullPointerException e) {  
			System.out.println("we have an error here" + e);
		}
	}
	
	public void getSqrt(){
		try {
			
			double value = Math.sqrt(Double.parseDouble(display.getText())); // Create a variable for value, and use Math's square root to find value
			display.setText(Double.toString(value));
			
		}catch(NumberFormatException e) {
			System.out.println("we have an error here" + e);
		}
	}
	
	public void getPosNeg() {
		try {
			 double value = Double.parseDouble(display.getText()); // again we create a variable for our current value
			
			 if(value != 0) { // if the value isn't 0
				 value = value * (-1); // we multiply it by -1 to get it's opposite value
				 display.setText(Double.toString(value)); // set the text to the new value.
			 }
			 else{
				 
			 } 
			 } catch(NumberFormatException e) {
				 System.out.println("we have an error here" + e);
		}
	}
	
	public void getResult() {
		
		double result = 0;  // variable for result
		temporary[1] = Double.parseDouble(display.getText()); //our second temporary number from display
		
		String temp0 = Double.toString(temporary[0]); //necessary string for text of first temp
		String temp1 = Double.toString(temporary[1]); //necessary string for text of second temp
		
		 try {
			 if(temp0.contains("-")) { //if first string contains -
				    String[] temp00 = temp0.split("-", 2); //split into two strings at -
				    temporary[0] = (Double.parseDouble(temp00[1]) * -1); //puts string back in double with the real value.
			 }
			 if(temp1.contains("-")) {// same as above with second temporary
				 
				 String[] temp11 = temp1.split("-", 2);
				 temporary[1] = (Double.parseDouble(temp11[1]) * -1);
			 }	 
			 }catch(ArrayIndexOutOfBoundsException e) {
				
				 System.out.println("we have an error here" + e);
			 }try{
				 if(function[2] == true)  //we start off with multiplication obviously
					 result = temporary[0] * temporary[1]; //sets result to multiplication of function
			 else if(function[3] == true)  //now division
				 result = temporary[0] / temporary[1];
			 else if(function[0] == true) //now addition
				 result = temporary[0] + temporary[1];
			 else if(function[1] == true) //now subtraction
				 result = temporary[0] - temporary[1];
			 display.setText(Double.toString(result)); //display now has result
			 for(int i = 0; i < 4; i++)
				 function[i] = false; //set all the functions back to false
		 }catch(NumberFormatException e) {
			 System.out.println("we have an error here" + e);
		 }
	}

	// here is the main method
	public static void main(String[] args) {
		System.out.println("we have a main method");
		Calculator c = new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == button[0])
			display.append("7");
		
		if(ae.getSource() == button[1])
			 display.append("8");
		
		if(ae.getSource() == button[2])
			 display.append("9");
		
		if(ae.getSource() == button[3]){
			 //add function[0]
			temporary[0] = Double.parseDouble(display.getText());
			function[0] = true;
			display.setText("");
		}
		
		if(ae.getSource() == button[4])
			display.append("4");
		
		if(ae.getSource() == button[5])
			display.append("5");
		
		if(ae.getSource() == button[6])
			display.append("6");
		
		if(ae.getSource() == button[7]){
			//subtract function
			temporary[0] = Double.parseDouble(display.getText());
			function[1] = true;
			display.setText("");
		}
		
		if(ae.getSource() == button[8])
			display.append("1");
		
		if(ae.getSource() == button[9])
			display.append("2");
		
		if(ae.getSource() == button[10])
			display.append("3");
		
		if(ae.getSource() == button[11]){
			 //multiply function[2]
			temporary[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		
		if(ae.getSource() == button[12])
			display.append(".");
		
		if(ae.getSource() == button[13]){
			 //divide function[3]
			temporary[0] = Double.parseDouble(display.getText());
			function[3] = true;
			display.setText("");
		}
		if(ae.getSource() == button[14])
			clear();
		
		if(ae.getSource() == button[15])
			getSqrt();
		
		if(ae.getSource() == button[16])
			getPosNeg();
		
		if(ae.getSource() == button[17])
			getResult();
		
		if(ae.getSource() == button[18])
			display.append("0");
		
	}

}
