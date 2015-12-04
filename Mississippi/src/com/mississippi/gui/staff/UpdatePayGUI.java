package com.mississippi.gui.staff;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class UpdatePayGUI extends StaffGUI{

	public UpdatePayGUI(ActionListener a){
		super();
		setTitle("Update Pay");
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
