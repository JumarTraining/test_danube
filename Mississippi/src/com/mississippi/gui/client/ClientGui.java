package com.mississippi.gui.client;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ClientGui extends JPanel{

	public static void main(String args[]){
		JFrame a = new JFrame();
		ClientGui b=new AddClient();
		
		a.add(b);
		a.pack();
		a.setVisible(true);
	}
	
	String z="','";
	
	JLabel name;
	JLabel adress;
	JLabel phone;
	JLabel email;
	JLabel contact;
	
	JTextField nameBox;
	JTextField adressBox;
	JTextField phoneBox;
	JTextField emailBox;
	JTextField contactBox;
	
	JButton enter;
	JButton cancel;
	ClientGui(){
		
		name    = new JLabel("Company Name");
		adress  = new JLabel("Company Adress");
		phone   = new JLabel("Company Phone Number");
		email   = new JLabel("Company Email");
		contact = new JLabel ("Staff Contact ID");
		
		nameBox     = new JTextField(" ",20);
		adressBox   = new JTextField(" ",20);
		phoneBox    = new JTextField(" ",20);
		emailBox    = new JTextField(" ",20);
		contactBox  = new JTextField(" ",20);
		
		enter   = new JButton("Enter");
		cancel  = new JButton("Cancel");
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx=1;
		c.gridy=3;
		this.add(name,c);
		c.gridy=4;
		this.add(adress,c);
		c.gridy=5;
		this.add(phone,c);
		c.gridy=6;
		this.add(email,c);
		c.gridy=7;
		this.add(contact,c);
		
		c.gridx=3;
		c.gridy=3;
		this.add(nameBox,c);
		c.gridy=4;
		this.add(adressBox,c);
		c.gridy=5;
		this.add(phoneBox,c);
		c.gridy=6;
		this.add(emailBox,c);
		c.gridy=7;
		this.add(contactBox,c);
		
		c.gridx=1;
		c.gridy=8;
		this.add(enter, c);
		c.gridx=3;
		c.gridy=8;
		this.add(cancel,c);
	}	
}