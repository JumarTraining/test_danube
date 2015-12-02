package com.mississippi.gui.staff;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class EditStaffGUI extends StaffGUI{
	public EditStaffGUI(ActionListener a){
		super();
		setTitle("Staff Update");
		
		//need to obtain staff info and assign to fields first
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 0;
		this.add(Title,c);
		
		c.gridwidth = 1;
		//adding labels
		c.gridy = 2;
		c.gridx=0;
		this.add(staffIdlb,c);
		c.gridy++;
		this.add(fnamelb,c);
		c.gridy++;
		this.add(lnamelb,c);
		c.gridy++;
		this.add(emaillb,c);
		c.gridy++;
		this.add(phonelb,c);
		c.gridy++;
		this.add(locationlb,c);
		c.gridy++;
		this.add(paygradelb,c);
		c.gridy++;
		this.add(typelb,c);
		c.gridy++;
		//adding textfields and combobox's
		c.gridy=2;
		c.gridx = 2;
		add(search,c);
		c.gridx=1;
		this.add(staffId,c);
		c.gridy++;
		this.add(fname,c);
		c.gridy++;
		this.add(lname,c);
		c.gridy++;
		this.add(email,c);
		c.gridy++;
		this.add(phone,c);
		c.gridy++;
		this.add(location,c);
		c.gridy++;
		this.add(paygrade,c);
		c.gridy++;
		this.add(type,c);
		c.gridy++;
		//adding buttons
		c.gridx=0;
		cancel.addActionListener(a);
		this.add(cancel,c);
		c.gridx=1;
		create.setText("Update");
		this.add(create,c);
				
		
	}
}