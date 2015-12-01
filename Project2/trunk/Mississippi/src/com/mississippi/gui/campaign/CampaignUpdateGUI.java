package com.mississippi.gui.campaign;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JComboBox;

	
public class CampaignUpdateGUI extends JFrame implements ActionListener {

		JLabel titleLbl = new JLabel("CAMPAIGN UPDATE");	
		JLabel nameLbl = new JLabel("Campaign Name:");
		JLabel durationLbl = new JLabel("Type:");
		JLabel budgetLbl = new JLabel("Budget:");
		JLabel costLbl = new JLabel("Cost:");
		JLabel statusLbl = new JLabel("Status");
		
		
		JTextField nameText = new JTextField();
		JTextField durationText = new JTextField();
		JTextField costText = new JTextField();
		JTextField budgetText = new JTextField();
		JTextField statusText = new JTextField();
		
		/*JComboBox type = new JComboBox();
		JComboBox age = new JComboBox();
		*/
	
		JButton updateBtn = new JButton("Update");
		JButton cancelBtn = new JButton("Cancel");
		
		JMenuBar bar = new JMenuBar();
		
	
	public CampaignUpdateGUI(){
		setLayout(null);
		
		titleLbl.setBounds(185, 5, 290, 100);
		add(titleLbl);
		Font font = new Font("Century Gothic", Font.BOLD,24);
		titleLbl.setFont(font);

		
		nameLbl.setBounds(20, 90, 200, 25);
		add(nameLbl);
		nameText.setBounds(170, 90, 150, 25);
		add(nameText);
		Font fontName = new Font("Century Gothic", Font.BOLD,14);
		nameLbl.setFont(fontName);
		
		
		durationLbl.setBounds(20, 120, 200, 25);
		add(durationLbl);
		durationText.setBounds(170, 120, 150, 25);
		add(durationText);
		Font durationName = new Font("Century Gothic", Font.BOLD,14);
		durationLbl.setFont(fontName);
		
		/*
		ageGroupLbl.setBounds(20, 170, 100, 25);
		add(ageGroupLbl);
		age.setBounds(140, 170, 100, 25);
		age.addItem("0-3 years"); age.addItem("4-9 years"); age.addItem("10+");
		add(age);*/
		
		budgetLbl.setBounds(20, 150, 150, 25);
		add(budgetLbl);
		budgetText.setBounds(170, 150, 150, 25);
		add(budgetText);
		Font budgetName = new Font("Century Gothic", Font.BOLD,14);
		budgetLbl.setFont(fontName);
		
		costLbl.setBounds(20, 180, 150, 25);
		add(costLbl);
		costText.setBounds(170, 180, 150, 25);
		add(costText);
		Font costName = new Font("Century Gothic", Font.BOLD,14);
		costLbl.setFont(fontName);
		
		statusLbl.setBounds(20, 210, 150, 25);
		add(statusLbl);
		statusText.setBounds(170, 210, 150, 25);
		add(statusText);
		Font statusName = new Font("Century Gothic", Font.BOLD,14);
		statusLbl.setFont(fontName);
		
		
		updateBtn.setBounds(185, 250, 120, 50);
		add(updateBtn);
		updateBtn.addActionListener(this);
		
		cancelBtn.setBounds(200, 310, 90, 25);
		add(cancelBtn);
		cancelBtn.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}

	/*public void actionPerformed(ActionEvent e) {
		//if reserve button pressed then perform this
		if(e.getSource().equals(registerBtn)){
		
		String itemNameStr = nameText.getText();
		String itemNameStr = durationText.getText();
		String itemNameStr = budgetText.getText();
		String itemDescription = cost.getText()
		String itemPrice = statusText.getText();
		
		
	class ListenToUpdate implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
        {
			String query = ("SELECT Campaigns FROM staff where CampaignID");
			
        }
	}
}
	}*/

