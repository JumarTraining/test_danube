package com.mississippi.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mississippi.gui.client.AddClient;
import com.mississippi.gui.client.EditClient;
import com.mississippi.gui.client.SearchClient;
import com.mississippi.gui.staff.AddStaffGUI;
import com.mississippi.gui.staff.ChangePassGUI;
import com.mississippi.gui.staff.ChangeRoleGUI;
import com.mississippi.gui.staff.EditStaffGUI;
import com.mississippi.gui.staff.UpdatePayGUI;

public class ClientCaller extends JFrame{

	static JPanel index = new JPanel();
	JPanel[] cards = new JPanel[6];
	String[] text = new String[6];
	JButton[] buttons = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
	static protected CardLayout cl;
	
	
	public static void main(String args[]){

		new ClientCaller();
	}
	public ClientCaller(){
		super("gui");

		this.setSize(new Dimension(500,500));
		index.setLayout(new CardLayout());
		cl = (CardLayout)(index.getLayout());
		
		ActionListener a = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cl.show(index, text[0]);
			}

		};
		
		cards[0] = new JPanel();
		text[0] = "start";
		text[1] = "Add Client";
		text[2] = "Edit Client";
		text[3] = "Search Client";
		
		cards[0].setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		for(int i = 0;i < 3; i++){
			final int b = i+1;
			buttons[i].setText(text[i+1]);
			buttons[i].setSize(300, 80);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    cl.show(index, text[b]);
				}

			});
			cards[0].add(buttons[i],c);
			c.gridy++;
		}
		
		
		cards[1] = new AddClient(a);
		cards[2] = new EditClient(a,1);
		cards[3] = new SearchClient(a,1);
		
		for(int i = 0;i < 4; i++){
			index.add(cards[i],text[i]);
		}
		
		this.add(index);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
}
