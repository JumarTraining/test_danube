package com.mississippi.gui.staff;

import java.awt.event.ActionListener;

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
}
