package com.mississippi.gui.staff;


import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AddStaffGUI extends StaffGUI{
	public AddStaffGUI(ActionListener a){
		super();
		

		
		
		populateTypes(selectAllQuery("Types"));
		populateGrades(selectAllQuery("Grades"));
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
		create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> Columns = new ArrayList<String>();
				Columns.add("FirstName");
				Columns.add("Surname");
				Columns.add("Email");
				Columns.add("Phone");
				Columns.add("Location");
				Columns.add("FK_Paygrade");
				Columns.add("Type");
				Columns.add("PassHash");
				ArrayList<String> Values = new ArrayList<String>();
				Values.add(fname.getText());
				Values.add(lname.getText());
				Values.add(email.getText());
				Values.add(phone.getText());
				Values.add(location.getText());
				Values.add(paygrade.getSelectedItem().toString());
				Values.add((String)type.getSelectedItem());
				Values.add(setPass("password"));
				database.Insert("Staff", Columns, Values);
			}
		});
		this.add(create,c);
	}
	public String setPass(String password){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			return String.format("%s",new java.math.BigInteger(1, digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return password;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return password;
		}

		
		
		
	}
	
}