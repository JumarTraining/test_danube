package com.mississippi.gui.advert;
import java.awt.*;
import java.util.*;

import javax.swing.*;

public class AdvertisementGUI extends JPanel {
	
	JButton create = new JButton("Create");
	JButton Search = new JButton("Search");
	JButton Update = new JButton("Update");
	JButton cancel = new JButton("cancel");
	JTextField title = new JTextField("",20);
	JTextField startDate = new JTextField("",20);
	JTextField duration = new JTextField("",20);
	JTextField progress = new JTextField("",20);
	JTextField cost = new JTextField("",20);
	JLabel titleLbl = new JLabel("Title:" );
	JLabel startDateLbl = new JLabel("Start Date:");
	JLabel durationLbl = new JLabel("Duration:");
	JLabel progressLbl = new JLabel("Progress");
	JLabel costLbl = new JLabel("Cost");
	JLabel Heading = new JLabel();
	
	GridBagConstraints c;
	
	AdvertisementGUI(){
	setLayout(new GridBagLayout());
	c = new GridBagConstraints();
	c.insets = new Insets(5,5,5,5);
	
	/*c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 2;
	//c.insets = new Insets(5,5,30,5);
	add(Heading,c);
	//c.insets = new Insets(5,5,5,5);
	//c.gridwidth =1;	
*/	
	}
	
}
