package com.mississippi.gui.staff;


import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChangeRoleGUI extends StaffGUI{
	public ChangeRoleGUI(ActionListener a){
	super();
	JButton check = new JButton("...");
	setTitle("Change Employee Type");
	c.gridx = 0;
	c.gridy = 1;
	add(staffIdlb,c);
	c.gridy++;
	add(fnamelb,c);
	c.gridy++;
	add(lnamelb,c);
	c.gridy++;
	add(typelb,c);
	c.gridx = 1;
	c.gridy = 1;
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
	create.setText("Submit");//re-use unused button :)
	add(create,c);
}
}