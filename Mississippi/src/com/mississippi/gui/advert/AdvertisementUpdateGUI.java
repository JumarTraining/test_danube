package com.mississippi.gui.advert;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class AdvertisementUpdateGUI extends AdvertisementGUI {

	public AdvertisementUpdateGUI(ActionListener a) {
		// TODO Auto-generated constructor stub
		
		super();
		Heading.setText("Update Advert");
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
		this.add(idLbl,c);
		c.gridy++;
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
		this.add(advertId,c);
		c.gridy++;
		advertId.setFocusable(true);
		Search.setEnabled(false);
		advertId.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {}
			
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(advertId.getText().length()>0){
					Search.setEnabled(true);
				} else 
					Search.setEnabled(false);
			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
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
		this.add(Update,c);
		c.gridx=2;
		this.add(Search,c);
		
	}

}