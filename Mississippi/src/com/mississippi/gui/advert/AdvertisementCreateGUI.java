package com.mississippi.gui.advert;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//ryan
@SuppressWarnings("serial")
public class AdvertisementCreateGUI extends AdvertisementGUI {
	public AdvertisementCreateGUI(ActionListener a){
		super();
		Heading.setText("Create New Advert");
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 0;
		this.add(Heading,c);
		c.gridy = 1;
		this.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		c.gridwidth = 1;
		//adding labels
		c.gridx=0;
		c.gridy=2;
		this.add(titleLbl,c);
		c.gridy++;
		this.add(startDateLbl,c);
		c.gridy++;
		this.add(durationLbl,c);
		c.gridy++;
		this.add(progressLbl,c);
		c.gridy++;
		this.add(costLbl,c);
		c.gridy++;
		//adding textfields and combobox's
		c.gridy=2;
		c.gridx=1;
		this.add(title,c);
		c.gridy++;
		this.add(startDate,c);
		c.gridy++;
		this.add(duration,c);
		c.gridy++;
		this.add(progress,c);
		c.gridy++;
		this.add(cost,c);
		c.gridy++;
		
		//adding buttons
		c.gridx=0;
		cancel.addActionListener(a);
		this.add(cancel,c);
		c.gridx=1;
		create.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				buildCreateSql();
				
			}

		});
		
		create.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent arg0) {}
			
			public void keyReleased(KeyEvent arg0) {
				//createEnable();
			}
			
			public void keyTyped(KeyEvent arg0){}
		});
		this.add(create,c);
	}
	
	void buildCreateSql(){
		//create statement for database insert 
		
		
	}

}