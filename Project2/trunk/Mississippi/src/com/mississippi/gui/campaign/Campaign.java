package com.mississippi.gui.campaign;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Campaign extends JFrame implements ActionListener {
	JLabel welcomeLbl = new JLabel("Campaigns");
	
	Font font = new Font("Century Gothic", Font.BOLD,26);
	
	JButton insertBtn = new JButton("Insert new campaign");
	JButton updateBtn = new JButton("Update current campaigns");
	JButton viewBtn = new JButton("View current campaigns");
	JButton deleteBtn = new JButton("Delete campaigns");
			

public Campaign(){
	setLayout(null);
	
	welcomeLbl.setBounds(130, -10, 200, 150);
	add(welcomeLbl);
	welcomeLbl.setFont(font);	
	
	insertBtn.setBounds(100, 100, 200, 40);
	add(insertBtn);
	insertBtn.addActionListener(this);
	
	updateBtn.setBounds(100, 160, 200, 40);
	add(updateBtn);
	updateBtn.addActionListener(this);

	viewBtn.setBounds(100, 220, 200, 40);
	add(viewBtn);
	viewBtn.addActionListener(this);
	
	deleteBtn.setBounds(100, 280, 200, 40);
	add(deleteBtn);
	deleteBtn.addActionListener(this);
}



@Override
			//get source of actionlistener
			//create a new jframe = "campaignaddGUI"
	public void actionPerformed(ActionEvent evt) {
	    if (evt.getActionCommand() == ;
	    } else if (evt.getActionCommand() == ;
	    }
	  }
}
}