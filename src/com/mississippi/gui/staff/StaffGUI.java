package com.mississippi.gui.staff;


import java.awt.*;
import java.util.*;
import javax.swing.*;

import com.mississippi.databaseaccess.DB;

public class StaffGUI extends JPanel{
	JButton create = new JButton("Create");
	JButton cancel = new JButton("cancel");
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
	ArrayList<String> grades = new ArrayList<String>();
	ArrayList<String> Types = new ArrayList<String>();
	
	
	
	JLabel Title = new JLabel();
	
	GridBagConstraints c = new GridBagConstraints();
	StaffGUI(){
	try{
	DB a = new DB();
	grades.add("value1");
	grades.add("value2");
	a.Update("a", grades, grades, grades, grades);
	}
	catch(Exception e){
	
	}
		setLayout(new GridBagLayout());
		c.insets = new Insets(5,5,5,5);
		}

	ArrayList<String> getGrades(){
		return null;
		
	}
	ArrayList<String> getTypes(){
		return null;
		
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
	
}


