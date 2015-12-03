package com.mississippi.gui.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChangePassGUI extends StaffGUI{
	public ChangePassGUI(ActionListener a){
		super();
		setTitle("Change User Password");
		c.gridx = 0;
		c.gridy = 1;
		add(staffIdlb,c);
		c.gridy++;
		add(fnamelb,c);
		c.gridy++;
		add(lnamelb,c);
		c.gridy++;
		add(passwordlb,c);
		c.gridy = 1;
		c.gridx = 2;
		add(search,c);
		c.gridx = 1;
		add(staffId,c);
		c.gridy++;
		fname.setEditable(false);
		add(fname,c);
		c.gridy++;
		lname.setEditable(false);
		add(lname,c);
		c.gridy++;
		add(password,c);
		c.gridy++;
		c.gridx=0;
		cancel.addActionListener(a);
		add(cancel,c);
		c.gridx=1;
		create.setText("Submit");//re-use unused button :)
		add(create,c);
		
		
		
	}
	@Override
	protected void userSearch(String ID){
		ArrayList<String> Columns = new ArrayList<String>();
		ArrayList<String> Values = new ArrayList<String>();
		if(staffId.getText().equals(""))
			return;
		Columns.add("StaffID");
		Values.add(staffId.getText());
		ResultSet rs = database.select("Staff", Columns, Values);
		try {
			rs.next();
			fname.setText(rs.getString(rs.findColumn("FirstName")));
			lname.setText(rs.getString(rs.findColumn("Surname")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
	}
}