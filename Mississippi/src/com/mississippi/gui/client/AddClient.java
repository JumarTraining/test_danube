package com.mississippi.gui.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class AddClient extends ClientGui{
	public AddClient(ActionListener a){
		super(a);
		
		//enter.removeAll();
		enter.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<String> columns = new ArrayList<String>(Arrays.asList("Name","Adress","Phone","Email","FK_StaffID"));
				System.out.println(contactBox.getText());
				ArrayList<String> values = new ArrayList<String>(Arrays.asList(nameBox.getText(),adressBox.getText(),phoneBox.getText(),emailBox.getText(),contactBox.getText()));
				db.Insert("Client", columns, values);
			}
				
		});
	
	}
}
