package com.mississippi.gui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mississippi.gui.advert.AdvertisementCreateGUI;
import com.mississippi.gui.advert.AdvertisementUpdateGUI;
import com.mississippi.gui.advert.AdvertisementViewGui;


@SuppressWarnings("serial")
public class AdvertismentCaller extends JFrame {
	
	static JPanel contents = new JPanel();
	JPanel[] cards = new JPanel[6];
	String[] labels = new String[6];
	JButton[] buttons = {new JButton(),new JButton(),new JButton()};
	static protected CardLayout cl;
	
	
	public static void main(String args[]){
		new AdvertismentCaller();
	}
	AdvertismentCaller(){
		super("gui");
		this.setSize(new Dimension(500,500));
		contents.setLayout(new CardLayout());
		cl = (CardLayout)(contents.getLayout());
		
		ActionListener a = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cl.show(contents, labels[0]);
			}

		};
		
		cards[0] = new JPanel()
				;
		labels[0] = "Home";
		labels[1] = "Create Advert";
		labels[2] = "View Advert";
		labels[3] = "Maintain Advert";
		
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
		
		
		cards[1] = new AdvertisementCreateGUI(a);
		cards[2] = new AdvertisementViewGui(a);
		cards[3] = new AdvertisementUpdateGUI(a);
		
		for(int i = 0;i < 6; i++){
			contents.add(cards[i],labels[i]);
		}
		
		this.add(contents);
		setVisible(true);
		setResizable(false);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}

}
