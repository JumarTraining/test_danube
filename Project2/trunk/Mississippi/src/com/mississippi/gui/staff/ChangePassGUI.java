package com.mississippi.gui.staff;

import java.awt.event.ActionListener;

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
		c.gridx = 1;
		c.gridy = 1;
		add(staffId,c);
		c.gridy++;
		add(fname,c);
		c.gridy++;
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
}