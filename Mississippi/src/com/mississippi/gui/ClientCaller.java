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
import com.mississippi.gui.staff.AddStaffGUI;
import com.mississippi.gui.staff.ChangePassGUI;
import com.mississippi.gui.staff.ChangeRoleGUI;
import com.mississippi.gui.staff.EditStaffGUI;
import com.mississippi.gui.staff.UpdatePayGUI;

public class ClientCaller extends JFrame{

	static JPanel contents = new JPanel();
	JPanel[] cards = new JPanel[6];
	String[] labels = new String[6];
	JButton[] buttons = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
	static protected CardLayout cl;
	
	
	public static void main(String args[]){
		new ClientCaller();
	}
	ClientCaller(){
		super("gui");
		this.setSize(new Dimension(500,500));
		contents.setLayout(new CardLayout());
		cl = (CardLayout)(contents.getLayout());
		
		ActionListener a = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cl.show(contents, labels[0]);
			}

		};
		
		cards[0] = new JPanel();
		labels[0] = "Home";
		labels[1] = "Add Client";
		labels[2] = "Edit Client";
		labels[3] = "Change Employee Password";
		labels[4] = "Change Employee Role";
		labels[5] = "Change Employee Pay";
		
		cards[0].setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		for(int i = 0;i < 5; i++){
			final int b = i+1;
			buttons[i].setText(labels[i+1]);
			buttons[i].setSize(300, 80);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    cl.show(contents, labels[b]);
				}

			});
			cards[0].add(buttons[i],c);
			c.gridy++;
		}
		
		
		cards[1] = new AddClient();
		cards[2] = new EditStaffGUI(a);
		cards[3] = new ChangePassGUI(a);
		cards[4] = new ChangeRoleGUI(a);
		cards[5] = new UpdatePayGUI(a);
		
		for(int i = 0;i < 6; i++){
			contents.add(cards[i],labels[i]);
		}
		
		this.add(contents);
		setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
}
