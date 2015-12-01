package com.mississippi.gui.advert;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class AdvertisementViewGui extends AdvertisementGUI {

	public AdvertisementViewGui(ActionListener a) {
		// TODO Auto-generated constructor stub
		super();
		Heading.setText("View Advert");
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
		this.add(create,c);
	}

}
