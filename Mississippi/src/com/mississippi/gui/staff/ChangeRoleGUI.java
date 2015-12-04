package com.mississippi.gui.staff;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChangeRoleGUI extends StaffGUI{
	public ChangeRoleGUI(ActionListener a){
	super();
	JButton check = new JButton("...");
	setTitle("Change Employee Type");
	populateTypes(selectAllQuery("Types"));
	c.gridx = 0;
	c.gridy = 1;
	add(staffIdlb,c);
	c.gridy++;
	add(fnamelb,c);
	c.gridy++;
	add(lnamelb,c);
	c.gridy++;
	add(typelb,c);
	c.gridy = 1;
	c.gridx = 2;
	add(search,c);
	c.gridx = 1;
	add(staffId,c);
	c.gridx ++;
	add(check,c);
	c.gridx--;
	c.gridy++;
	add(fname,c);
	c.gridy++;
	add(lname,c);
	c.gridy++;
	add(type,c);
	c.gridy++;
	c.gridx=0;
	cancel.addActionListener(a);
	add(cancel,c);
	c.gridx=1;
	create.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			submit();			
		}
	});
	create.addActionListener(a);
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
			type.setSelectedItem(rs.getString(rs.findColumn("type")));
			} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
	}
	protected void submit(){
		ArrayList<String> Columns = new ArrayList<String>();
		Columns.add("type");
		ArrayList<String> Values = new ArrayList<String>();
		Values.add(type.getSelectedItem().toString());
		ArrayList<String> ConditionColumns = new ArrayList<String>();
		ConditionColumns.add("StaffID");
		ArrayList<String> ConditionValues = new ArrayList<String>();
		ConditionValues.add(staffId.getText());
		database.Update("Staff", Columns, Values, ConditionColumns, ConditionValues);
	}
}