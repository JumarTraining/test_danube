package com.mississippi.gui.staff;


import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
//govan
@SuppressWarnings("serial")
public class AddStaffGUI extends StaffGUI{
	public AddStaffGUI(ActionListener a){
		super();
		Title.setText("Add New Employee");
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 0;
		this.add(Title,c);
		c.gridy = 1;
		this.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		c.gridwidth = 1;
		//adding labels
		c.gridx=0;
		c.gridy=2;
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
		c.gridx=1;
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
		this.add(create,c);
		
	}
	
}