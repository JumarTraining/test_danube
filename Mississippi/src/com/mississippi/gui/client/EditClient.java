package com.mississippi.gui.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EditClient extends ClientGui{
	public EditClient(ActionListener a, int id){
		super(a);
		/*ResultSet Rs=search("Client","ClientID",""+id);
		
		while(Rs.next()){
			nameBox.setText(Rs.getString(Rs.findColumn("Name")));
			adressBox.setText(Rs.getString(Rs.findColumn("Adress")));
			phoneBox.setText(Rs.getString(Rs.findColumn("Phone")));
			emailBox.setText(Rs.getString(Rs.findColumn("Email")));
			contactBox.setText(Rs.getString(Rs.findColumn("FK_StaffID")));
		}*/
		
		enter.removeAll();
		enter.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String value="('"+nameBox.getText()+z+adressBox.getText()+z+phoneBox.getText()+z+emailBox.getText()+z+contactBox.getText()+"');";
				String s = "INSERT INTO Cient (Name,Adress,Phone,Email,FK_StaffID) VALUES"+value;
				
			}
				
		});
	}
}
