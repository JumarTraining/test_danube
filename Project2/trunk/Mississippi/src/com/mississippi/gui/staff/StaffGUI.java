package com.mississippi.gui.staff;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

import com.mississippi.databaseaccess.DB;

public class StaffGUI extends JPanel{
	JButton create = new JButton("Create");
	JButton cancel = new JButton("cancel");
	JButton search = new JButton("...");
	JTextField staffId = new JTextField("",20);
	JTextField fname = new JTextField("",20);
	JTextField lname = new JTextField("",20);
	JTextField email = new JTextField("",20);
	JTextField phone = new JTextField("",20);
	JTextField location = new JTextField("",20);
	JTextField password = new JTextField("",20);
	JComboBox paygrade = new JComboBox();//dropdown from grades table
	JComboBox type = new JComboBox();//drop down from types
	
	JLabel staffIdlb = new JLabel("Staff ID: ");
	JLabel fnamelb = new JLabel("First Name: ");
	JLabel lnamelb = new JLabel("Last Name: ");
	JLabel emaillb = new JLabel("Email: ");
	JLabel phonelb = new JLabel("Phone No#: ");
	JLabel locationlb = new JLabel("Location: ");
	JLabel passwordlb = new JLabel("New Password: ");
	JLabel paygradelb = new JLabel("Paygrade: ");
	JLabel typelb = new JLabel("Employee Type: ");
	
	
	DB database;
	
	
	JLabel Title = new JLabel();
	
	GridBagConstraints c = new GridBagConstraints();
	StaffGUI(){
	try{
	database = new DB();
	database.setLogin("root", "goomoonryong");
	database.setDatabase("jdbc:mysql://localhost:3306/mississippi");
	database.createConnection();
	}
	catch(Exception e){
	
	}
		setLayout(new GridBagLayout());
		c.insets = new Insets(5,5,5,5);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  userSearch(staffId.getText());
			}

		});
	}


	protected void setTitle(String title){
		Title.setText(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(5,5,30,5);
		add(Title,c);
		c.insets = new Insets(5,5,5,5);
		c.gridwidth =1;
	}
	protected ResultSet selectAllQuery(String Table){
		return database.selectAll(Table);
	}
	
	protected void populateGrades(ResultSet rs){
		try {
			while (rs.next()) {
			    int grade = rs.getInt("GradeID");
			    paygrade.addItem(grade);
			    
			}
		} catch (SQLException e) {
			paygrade.addItem("Grades Unavaliable");
		}
		
	}
	protected void populateTypes(ResultSet rs){
		try {
			while (rs.next()) {
			    String types = rs.getString("Type");
			    System.out.println(types);
			    type.addItem(types);
			    
			}
		} catch (SQLException e) {
			type.addItem("Types Unavaliable");
		}
	}
	protected void userSearch(String ID){
		
	}
	
}


