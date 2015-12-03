package com.mississippi.gui.client;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchClient extends ClientGui{

	public SearchClient(ActionListener a, int id) {
		super(a);
		this.nameBox.setEditable(false);
		this.adressBox.setEditable(false);
		this.phoneBox.setEditable(false);
		this.emailBox.setEditable(false);
		this.contactBox.setEditable(false);
		
		
		final ArrayList<String> column = new ArrayList<String>(Arrays.asList("ClientID"));
		final ArrayList<String> value = new ArrayList<String>(Arrays.asList(""+id));
		
		ResultSet Rs= db.select("Client", column, value);
		
		try {
			while(Rs.next()){
				nameBox.setText(Rs.getString(Rs.findColumn("Name")));
				adressBox.setText(Rs.getString(Rs.findColumn("Adress")));
				phoneBox.setText(Rs.getString(Rs.findColumn("Phone")));
				emailBox.setText(Rs.getString(Rs.findColumn("Email")));
				contactBox.setText(Rs.getString(Rs.findColumn("FK_StaffID")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
