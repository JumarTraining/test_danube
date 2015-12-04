package com.mississippi.nexus;

import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import com.mississippi.databaseaccess.DB;
import com.mississippi.login.Login;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

	public class Nexus extends JFrame implements ActionListener
	{
		static Vector data, columnNames;			
		CalendarEntries calEntries;
		JPanel desktop;
		JMenuBar menuBar;
		JMenu mFile, mCalendarEntry, mShow, mHelp;  
		
		JMenuItem mRetrieveData, mSaveData, mQuit,
					mAdd, mRemove,
					mShowDaily, mShowWeekly,mShowTodaysReminder,
					mAbout, mHelpContents;
		
		private JButton toolSaveData, toolRetrieveData, 
					toolAdd, toolRemove, toolShowDaily, 
					toolShowWeekly, toolShowTodaysReminder;
						
		JToolBar toolBar;
				
		Font font;
			
		Color backcolor, bordercolor, emptycolor;

	    static Date reminderDate;
		int year,mon,day,hour,minute;
		int taskYear, taskMon, taskDay;
		String reminderNameStr,appointmentName;
		/**
		 * Constructs the personal calendar main frame
	     */	
		
		public static void main(String[] args)
		{	//create GUI and DB connection
			DB a  = new DB();
			a.setLogin();
			new Login();
		}
		
	    public Nexus()
		{
			super("Main Window Mississippi");	
//			myCalendar = new PersonalCalendar("SAHA"); haha	
//			ce = new CalendarEntry();
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent we)
				{
					System.exit(0);
				}
			});
			
			Dimension dim  = Toolkit.getDefaultToolkit().getScreenSize();
			setSize( dim.width , dim.height - 30) ; 
			setLocation(0,0);
			
			font = new Font("Helvetica", Font.PLAIN, 14);		
			
			buildToolBars();
			addAllButtonListeners();		
			
			desktop = new JPanel();
			desktop.setLayout(new BorderLayout());	
			desktop.setBackground(Color.gray);
			getContentPane().add(desktop);
			desktop.add(toolBar, BorderLayout.PAGE_START);
			setJMenuBar(createMenuBar());
			setVisible(true);
		}
		/**
		 * Creates the menu bar
		 * @return JMenuBar as the menu bar
		 */
		private JMenuBar createMenuBar()
		{
			menuBar = new JMenuBar();
			
			mFile = new JMenu("File");
			mFile.setFont(font);
			mFile.setMnemonic(KeyEvent.VK_F);
			
			mFile.add(mSaveData = new JMenuItem("Save Data") );
			mSaveData.setMnemonic(KeyEvent.VK_S);
			mSaveData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK) );
			mSaveData.addActionListener(this);
			mSaveData.setFont(font);
			
			mFile.add(mRetrieveData = new JMenuItem("Retrieve Data") );
			mRetrieveData.setMnemonic(KeyEvent.VK_R);
			mRetrieveData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK) );
			mRetrieveData.addActionListener(this);
			mRetrieveData.setFont(font);		
			
			mFile.addSeparator();
		
			mFile.add(mQuit = new JMenuItem("Quit") );
			mQuit.setMnemonic(KeyEvent.VK_Q);
			mQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK) );
			mQuit.addActionListener(this);
			mQuit.setFont(font);
			
			mCalendarEntry = new JMenu("VideoData");
			mCalendarEntry.setFont(font);
			mCalendarEntry.setMnemonic(KeyEvent.VK_V);
			
			mCalendarEntry.add(mAdd = new JMenuItem("Add") );
			mAdd.setMnemonic(KeyEvent.VK_A);
			mAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK) );
			mAdd.setFont(font);
			mAdd.addActionListener(this);
			
			mCalendarEntry.add(mRemove = new JMenuItem("Remove") );
			mRemove.setMnemonic(KeyEvent.VK_V);
			mRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK) );
			mRemove.setFont(font);
			mRemove.addActionListener(this);
				
			mShow = new JMenu("Show");
			mShow.setFont(font);
			mShow.setMnemonic(KeyEvent.VK_S);
			
			mShow.add(mShowDaily = new JMenuItem("Show Object") );
			mShowDaily.setMnemonic(KeyEvent.VK_D);
			mShowDaily.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK) );
			mShowDaily.setFont(font);
			mShowDaily.addActionListener(this);
			
			mShow.add(mShowWeekly = new JMenuItem("Show Event") );
			mShowWeekly.setMnemonic(KeyEvent.VK_W);
			mShowWeekly.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK) );
			mShowWeekly.setFont(font);
			mShowWeekly.addActionListener(this);
				
			mShow.add(mShowTodaysReminder = new JMenuItem("Customise Search") );
			mShowTodaysReminder.setMnemonic(KeyEvent.VK_T);
			mShowTodaysReminder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK) );
			mShowTodaysReminder.setFont(font);
			mShowTodaysReminder.addActionListener(this);	
			
			mHelp = new JMenu("Help");
			mHelp.setFont(font);
			mHelp.setMnemonic(KeyEvent.VK_H);
			mHelp.addMouseListener(new MouseAdapter()
			{
				public void mouseEntered(MouseEvent me)
				{				
					mHelp.setBackground(backcolor);
					mHelp.setForeground(Color.white);
				}
				public void mouseExited(MouseEvent m)
				{				
					mHelp.setBackground(emptycolor);
					mHelp.setForeground(Color.black);
				}
			});
			
			mHelp.add(mHelpContents = new JMenuItem("Help Contents") );
			mHelpContents.setMnemonic(KeyEvent.VK_N);
			mHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK) );
			mHelpContents.setFont(font);
			mHelpContents.addActionListener(this);
			
			mHelp.addSeparator();
			
			mHelp.add(mAbout = new JMenuItem("About") );
			mAbout.setMnemonic(KeyEvent.VK_O);
			mAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK) );
			mAbout.setFont(font);
			mAbout.addActionListener(this);
			
			menuBar.add(mFile);
			menuBar.add(mCalendarEntry);
			menuBar.add(mShow);		
			menuBar.add(mHelp);
			
			return menuBar;
		}
		/**
		 * Creates the toolbar panel.
		 */
		private void buildToolBars()
		{
			toolSaveData = new JButton(new ImageIcon("images//save.gif") );
			toolSaveData.setToolTipText("Save Data");
			toolRetrieveData = new JButton(new ImageIcon("images//retrieve.gif") );
			toolRetrieveData.setToolTipText("Retrieve Data");
			toolAdd = new JButton(new ImageIcon("images//add.gif") );
			toolAdd.setToolTipText("Add");
			toolRemove = new JButton(new ImageIcon("images//removetool.gif") );
			toolRemove.setToolTipText("Remove");
			toolShowDaily = new JButton(new ImageIcon("images//daily.gif") );
			toolShowDaily.setToolTipText("Show Daily Calendar");
			toolShowWeekly = new JButton(new ImageIcon("images//weekly.gif") );
			toolShowWeekly.setToolTipText("Show Weekly Calendar");
			toolShowTodaysReminder = new JButton(new ImageIcon("images//currentreminder.gif") );
			toolShowTodaysReminder.setToolTipText("Show Today's Reminder List");
			toolBar = new JToolBar("Tool Bar");
			toolBar.setBackground(Color.LIGHT_GRAY);
			toolBar.add(toolSaveData);
			toolBar.add(toolRetrieveData);
			toolBar.addSeparator();
			toolBar.add(toolAdd);
			toolBar.add(toolRemove);
			toolBar.addSeparator();
			toolBar.add(toolShowDaily);
			toolBar.add(toolShowWeekly);
			toolBar.add(toolShowTodaysReminder);

		}
		/**
		 * Overrides the actioPerformed method of the ActionListener interface.
		 */
		public void actionPerformed(ActionEvent ae)
		{
			Object source = ae.getSource();
			
			if(source == mSaveData || source == toolSaveData)
			{       
	                        
//	            saveToDisk(ce , "DataFolder//PersonalCalendar.dat");
	    //        saveReminderToDisk(myCalendar, "DataFolder//Reminder.dat");

			}
			if (source == mRetrieveData || source == toolRetrieveData)
			{	
				int n = JOptionPane.showConfirmDialog(this,
	                "This operation will overwrite all data of the current calendar entries in the memory!",
	                "Retrieve data from file", JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.WARNING_MESSAGE );

				if (n==0) 
				{
//					ce = retrieveFromDisk("DataFolder//PersonalCalendar.dat"); 
		//			myCalendar = retrieveReminderFromDisk("DataFolder//Reminder.dat"); 
				}
	            else JOptionPane.showMessageDialog(null,"You have cancelled the retrieval operation."); 
	        }
			if (source == mQuit)
			{
				int yesno = JOptionPane.showConfirmDialog(null,"Do you really want to close the window?","Closing...",
														JOptionPane.YES_NO_OPTION);
				int yes = JOptionPane.YES_OPTION;
				if(yesno == yes)
				{
					
					System.exit(0);	
	            
	        	}
	        }
			if (source == mAdd || source == toolAdd)
			{
				
				calEntries = new CalendarEntries(this, "Add Calendar Entry Form", true);
				
				calEntries.setVisible(true);
			}
			if(source == mRemove || source == toolRemove)
			{
				String userDateStr = JOptionPane.showInputDialog(null,"Enter the date (dd-mm-yyyy) for which you \nwant to remove a calendar entry");
				String re = "Removing Calendar Entry";
				commonMethodForRemoveAndShowDaily(userDateStr, re);
			}
		 	if (source == mShowDaily || source == toolShowDaily)
			{
				Date t = new Date();
				String userDateStr = JOptionPane.showInputDialog(null,"Enter the date (dd-mm-yyyy) for which you \nwant to see the daily calendar");
				String re = "Showing Calendar Entry for "+userDateStr;
				commonMethodForRemoveAndShowDaily(userDateStr, re);
				
			}
			if (source == mShowWeekly || source == toolShowWeekly)
			{
				String userDateStr = JOptionPane.showInputDialog(null,"Enter start of the week:dd-mm-yyyy:");
				Vector columnNames = new Vector();
				columnNames.addElement("Calendar Entry Type");
				columnNames.addElement("Name");
				columnNames.addElement("Start Time/Time to Occur");
				columnNames.addElement("Date/Deadline");
				columnNames.addElement("Duration(Min.)");
				columnNames.addElement("Location");
				columnNames.addElement("Status");
				columnNames.addElement("Description");
				ShowingCalendar calShowW = new ShowingCalendar(this, "Showing Weekly Calendar", true,columnNames,columnNames.size());
				if(userDateStr != null)
				{
					calShowW.showWeeklyCalendar(userDateStr);
					calShowW.setVisible(true);
					
	            }
	            else
	            {
	            	
	            	JOptionPane.showMessageDialog(null,"You have cancelled this operation");
	            	
	            }
	            
			}
			if(source == mShowTodaysReminder || source == toolShowTodaysReminder)
			{
				Vector columnNames = new Vector();
				columnNames.addElement("Calendar Entry Type");
				columnNames.addElement("Calendar Entry Name");
				columnNames.addElement("Reminder");
				columnNames.addElement("Date");
				columnNames.addElement("Time");
				ShowingCalendar calShow = new ShowingCalendar(this, "Showing Today's Reminder", true,columnNames,columnNames.size());
//				calShow.showTodaysReminder();
				calShow.setVisible(true);
			}
			if(source == mHelpContents)
			{
				JOptionPane.showMessageDialog( null,
	                    "Personal Calendar Help\n\n" +
	                    "Use menu and tool bar to navigate through the system\n" +
	                    "To select use mouse clicks or keyboard shortcuts",
	                    "Help Contents", JOptionPane.PLAIN_MESSAGE );
			}
			if(source == mAbout)
			{
				JOptionPane.showMessageDialog( null,
	                    "Personal Calendar\nDeveloped by: JAYANTA KUMAR SAHA\nStudent No:05045255",
	                    "About", JOptionPane.PLAIN_MESSAGE );
			}
		}
		/**
		 * Adds all the tool bar buttons listeners
		 */
		private void addAllButtonListeners()
		{
			toolSaveData.addActionListener(this);
			toolSaveData.addActionListener(this);
			toolAdd.addActionListener(this);
			toolRemove.addActionListener(this);
			toolShowDaily.addActionListener(this);
			toolShowWeekly.addActionListener(this);
			toolShowTodaysReminder.addActionListener(this);

		}
		/**
		 * Common method for removing and showing daily calendar.
		 * @param userDateStr user enters the date for which he wants to see
		 * the daily calendar or wants to remove a calendar entry.
		 * @param re the date information of that calendar entry.
		 */
		private void commonMethodForRemoveAndShowDaily(String userDateStr, String re)
		{
			int year=0,month=0,day=0;
			Date userDate=null;
			Vector columnNames = new Vector();
			columnNames.addElement("Calendar Entry Type");
			columnNames.addElement("Name");
			columnNames.addElement("Start Time/Time to Occur");
			columnNames.addElement("Date/Deadline");
			columnNames.addElement("Duration(Min.)");
			columnNames.addElement("Location");
			columnNames.addElement("Status");
			columnNames.addElement("Description");
			ShowingCalendar calShow = new ShowingCalendar(this, re, true,columnNames,columnNames.size());
			if(userDateStr != null)
			{
				year = Integer.parseInt(userDateStr.substring(6,10));
				month = Integer.parseInt(userDateStr.substring(3,5));
				day = Integer.parseInt(userDateStr.substring(0,2));
				userDate = new Date(year-1900, month-1, day);
	        }
	        else
	        {
	            calShow.dispose();
	            JOptionPane.showMessageDialog(null,"You have cancelled this operation");
	        }
	       	try
	       	{
//				calShow.showDailyCalendar(userDate);
				if(re.equals("Removing Calendar Entry"))
				{
					calShow.btnRemoveApp.setEnabled(true);
					calShow.btnRemoveTa.setEnabled(true);
					calShow.btnRemoveRe.setEnabled(true);
				}
				calShow.setVisible(true);
			}
			catch(Exception e){JOptionPane.showMessageDialog(null,"An exception occured "+e);}
		}
		/**
		 * Method to write the CalendarEntry object into the disk.
		 * @param cal the CalendarEntry object.
		 * @param filename the file name of the hard disk location.
		 */
	/*	private void saveToDisk(CalendarEntry cal, String filename)
	    {
	    	try
	        {
	        	ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(filename));
	            ooStream.writeObject(cal);
	            ooStream.flush();
	            ooStream.close();
	            JOptionPane.showMessageDialog(this,
	                "Current data have been written to file DataFolder//PersonalCalendar.dat");
	        }
	        catch (IOException e)
	        {
	            JOptionPane.showMessageDialog(this,
	                "Error processing file! " + e.toString() +
	                "\nPlease check disk drive, then re-try.",
	                "Input error",  JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }*/
	    /**
		 * Method to write the PersonalCalendar object into the disk.
		 * PersonalCalendar object holds all the reminders of the calendar
		 * @param pc the PersonalCalendar object.
		 * @param filename the file name of the hard disk location.
		 */
	/*    private void saveReminderToDisk(PersonalCalendar pc, String filename)
	    {
	   		try
	       	{
	       		ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(filename));
	            ooStream.writeObject(pc);
	            ooStream.flush();
	            ooStream.close(); 
	        }
	        catch (IOException e)
	        {
	            JOptionPane.showMessageDialog(this,
	                "Error processing file! " + e.toString() +
	                "\nPlease check disk drive, then re-try.",
	                "Input error",  JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }*/
	    /**
		 * Method to retrieve the CalendarEntry object into the memory.
		 * @param filename the file name of the hard disk location.
		 * @return the CalendarEntry object.
		 */
	/*    private CalendarEntry retrieveFromDisk(String filename)
	    {
	    	try
	        {
	        	ObjectInputStream ioStream = new ObjectInputStream(new FileInputStream(filename));
	            CalendarEntry aCalendar = (CalendarEntry) ioStream.readObject();
	            ioStream.close();
	            JOptionPane.showMessageDialog(null,"Data from file have been loaded into the memory!");
	            return aCalendar;
	        }
	        catch(Exception e)
	        {
	            JOptionPane.showMessageDialog(this,
	                "Error processing file! " + e.toString() +
	                "\nPlease check disk drive, then re-try.",
	                "Input error",  JOptionPane.ERROR_MESSAGE);
	            return null;
	        }
	    }*/
	    /**
		 * Method to retrieve the PersonalCalendar object into the memory.
		 * @param filename the file name of the hard disk location.
		 * @return the PersonalCalendar object.
		 */
	/*    private PersonalCalendar retrieveReminderFromDisk(String filename)
	    {
	    	try
	        {
	            ObjectInputStream ioStream = new ObjectInputStream(new FileInputStream(filename));
	            PersonalCalendar aCalendar = (PersonalCalendar) ioStream.readObject();
	            ioStream.close();
	            return aCalendar;
	        }
	        catch(Exception e)
	        {
	            JOptionPane.showMessageDialog(this,
	                "Error processing file! " + e.toString() +
	                "\nPlease check disk drive, then re-try.",
	                "Input error",  JOptionPane.ERROR_MESSAGE);
	            return null;
	        }
	    }*/
	    /**
		 * Main method at where the application starts its execution
		 */
		
		/**
		 * An inner class which contains three inner classes,i.e.  
		 * AppointmentTabPanel, TaskTabPanel and ReminderTabPanel.
		 * This CalendarEntries class builds the tabbed panes for entering 
		 * appointment, task and reminder events. This class provides the 
		 * methods for adding new appointments, new tasks and new reminder events.
		 * It also provides the methods for setting the reminders for different calendar entries
		 */
		public class CalendarEntries extends JDialog  
		{
			boolean taskStatus;
			JPanel main_panel;
			//Appointment
	  		JLabel lblAppointmentName, lblAppointmentDate, lblStartTime, 
	  		lblDuration, lblLocation, lblDescription;
	 		JTextField txtAppointmentName, txtStartTime, txtDuration, txtLocation;
			JTextArea txtaDescription, txtaMessage;
			JScrollPane scroll;
			JButton btnAppointAdd, btnAppointExit;
//			PanelCalendar pnlAppointmentDate;
			// Task
	 		JLabel lblTaskName, lblTaskDescription, lblTaskDeadLine, lblTaskStatus;
			JTextField txtTaskName, txtTaskDescription, txtTaskDeadLine, txtTaskStatus;
			JTextArea txtaTaskDescription, txtaTaskMessage;
			JComboBox cbxTaskStatus;
			JButton btnTaskAdd,   btnTaskExit;
			JScrollPane taskScroll;
		//	PanelCalendar pnlTaskDate;
			// Reminder 
			JLabel lblReminderName, lblReminderDate, lblReminderTime, lblReminderDescription;
	 		JTextField txtReminderName, txtReminderTime;
			JTextArea txtaReminderDescription, txtaReminderMessage;
			JScrollPane reminderScroll;
			JButton btnReminderAdd,btnReminderExit;
//			PanelCalendar pnlReminderDate;
			JTabbedPane tabbedPane;
	    	String r;
	    	/**
	     	 * Constructs the CalendarEntries main panel
	     	 * @param parent the parent JFrame 
	     	 * @param title the title of the calendar entries frame
	     	 * @param modal the modal
	     	 */
	    	public CalendarEntries(JFrame parent, String title, boolean modal)
	    	{    	        	
	        	super(parent, title, modal);
	        	setSize(700,360);
	        	setLocation(150,200);
	        	main_panel = new JPanel();
	        	getContentPane().add(main_panel);
	        	main_panel.setLayout(null);
				//Appointment
				lblAppointmentName = new JLabel("Appointment Name:");
				lblAppointmentDate = new JLabel("Appointment Date:");
				lblStartTime = new JLabel("Start Time (hh mm):");
				lblDuration = new JLabel("Duration (in minutes):");
				lblLocation = new JLabel("Location:");		
				lblDescription = new JLabel("Description:");
				txtAppointmentName = new JTextField();
				txtStartTime = new JTextField();
				txtDuration = new JTextField();
				txtLocation = new JTextField();;
				txtaDescription = new JTextArea(3,10);
				txtaDescription.setLineWrap(true);
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				scroll = new JScrollPane(txtaDescription,v,h);
				txtaMessage = new JTextArea("This tabbed pane allows you to add appointments "+
		    										"for your upcoming activities.",3,3);
		    	Dimension dimm = new Dimension(75,45);
				btnAppointAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
				btnAppointAdd.setPreferredSize(dimm);
				btnAppointAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnAppointAdd.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnAppointAdd.setMnemonic('A');
	    	    btnAppointExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
				btnAppointExit.setPreferredSize(dimm);
				btnAppointExit.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnAppointExit.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnAppointExit.setMnemonic('E');
//	    		pnlAppointmentDate = new PanelCalendar();
	    		// Task
				lblTaskName = new JLabel("Task Name:", SwingConstants.RIGHT);				
				lblTaskDescription = new JLabel("Description:", SwingConstants.RIGHT);
				lblTaskDeadLine = new JLabel("Deadline (Date):", SwingConstants.RIGHT);
				lblTaskStatus = new JLabel("Status:", SwingConstants.RIGHT);		
				txtTaskName = new JTextField();
				txtTaskDescription = new JTextField();
				txtTaskDeadLine = new JTextField();
				txtTaskStatus = new JTextField();;
				txtaTaskDescription = new JTextArea(3,10);
				txtaTaskDescription.setLineWrap(true);
				int hr = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				int vr = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				taskScroll = new JScrollPane(txtaTaskDescription,vr,hr);
				String status[] = {"Completed","Not Completed"};		
				cbxTaskStatus = new JComboBox(status);	
				txtaTaskMessage = new JTextArea("This tabbed pane allows you to add the tasks "+
		    										"for your upcoming activities.",3,3);			
				btnTaskAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
				btnTaskAdd.setPreferredSize(dimm);
				btnTaskAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnTaskAdd.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnTaskAdd.setMnemonic('A');
	    		btnTaskExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
				btnTaskExit.setPreferredSize(dimm);
				btnTaskExit.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnTaskExit.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnTaskExit.setMnemonic('E');   	
//	    		pnlTaskDate = new PanelCalendar();
	    		// Reminder Events
				lblReminderName = new JLabel("Event Name:", SwingConstants.RIGHT);
				lblReminderDate = new JLabel("Event Date:", SwingConstants.RIGHT);
				lblReminderTime = new JLabel("Time to Occur:", SwingConstants.RIGHT);				
				lblReminderDescription = new JLabel("Description:", SwingConstants.RIGHT);
				txtReminderName = new JTextField();
				txtReminderTime = new JTextField();			
				txtaReminderDescription = new JTextArea(3,10);
				txtaReminderDescription.setLineWrap(true);
				int hor = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				int vor = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				reminderScroll = new JScrollPane(txtaReminderDescription,vor,hor);
				txtaReminderMessage = new JTextArea("This tabbed pane allows you to add a reminder event "+
		    										"for your upcoming activities.",3,3);
				btnReminderAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
				btnReminderAdd.setPreferredSize(dimm);
				btnReminderAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnReminderAdd.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnReminderAdd.setMnemonic('A');
	    	    btnReminderExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
				btnReminderExit.setPreferredSize(dimm);
				btnReminderExit.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnReminderExit.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnReminderExit.setMnemonic('E');
//	    		pnlReminderDate = new PanelCalendar();
	    		tabbedPane = new JTabbedPane();
	       		tabbedPane.setBounds(1,1,500,350);
	        	add(tabbedPane);
	        	buildTabbedPane();
	        	setFontProperties();
			}
			/**
			 * Method that builds the GUI tabbed pane for appointment, task and reminder event.
			 */
	    	private void buildTabbedPane() 
	    	{
	        	tabbedPane.addTab("Appointment",null, new AppointmentTabPanel(), "Appointments");
	        	tabbedPane.addTab("Task", null, new TaskTabPanel(), "Tasks");
				tabbedPane.addTab("Reminder Event", null, new ReminderTabPanel(), "Reminder Events");
			}
			/**
			 * Method that sets the propeties of the fonts
			 */
	    	private void setFontProperties()
	    	{
	    		Font fonts = new Font("Tahoma", Font.BOLD, 16);
	    		Font font = new Font("Tahoma", Font.PLAIN, 15);
	    		Font font_buttons = new Font("Tahoma", Font.BOLD, 12);
	    		lblAppointmentName.setFont(font);    	
				lblAppointmentDate.setFont(font);
				lblStartTime.setFont(font);
				lblDuration.setFont(font);
				lblLocation.setFont(font);
				lblDescription.setFont(font);
				btnAppointAdd.setFont(font_buttons);
				btnAppointExit.setFont(font_buttons);
				txtaMessage.setFont(font_buttons);
				lblTaskName.setFont(font);    	
				lblTaskStatus.setFont(font);
				lblTaskDeadLine.setFont(font);		
				lblTaskDescription.setFont(font);
				btnTaskAdd.setFont(font_buttons);
				btnTaskExit.setFont(font_buttons);
				cbxTaskStatus.setFont(font);
				txtaTaskMessage.setFont(font_buttons);
				lblReminderName.setFont(font);    	
				lblReminderDate.setFont(font);
				lblReminderTime.setFont(font);		
				lblReminderDescription.setFont(font);
				btnReminderAdd.setFont(font_buttons);
				btnReminderExit.setFont(font_buttons);
				txtaReminderMessage.setFont(font_buttons);
				cbxTaskStatus.setFont(font);
				tabbedPane.setFont(fonts);
			}
			/**
			 * An inner class of CalendarEntries that builds the appointment tabbed pane
			 */
	    	class AppointmentTabPanel extends JPanel
	    	{
	    		/**
	    		 * Constructs the appointment tabbed panel
	    		 */
	    		public AppointmentTabPanel()
	    		{
		    		setLayout(null);
		    		setBorder(BorderFactory.createEtchedBorder() );
		    		// Appointment Name
		    		lblAppointmentName.setBounds(30,10,150,20);
		    		add(lblAppointmentName);
		    		txtAppointmentName.setBounds(200,10,200,25);
		    		add(txtAppointmentName);
		    		// Appointment Date
		    		lblAppointmentDate.setBounds(30,40,150,20);
		    		add(lblAppointmentDate);
//		    		pnlAppointmentDate.setBounds(195,38,150,25);
//		    		add(pnlAppointmentDate);
		    		// Start Time
		    		lblStartTime.setBounds(30,68,150,20);
		    		add(lblStartTime);
		    		txtStartTime.setBounds(200,68,100,25);
		    		add(txtStartTime);
		    		// Duration
		    		lblDuration.setBounds(30,100,150,20);
		    		add(lblDuration);
		    		txtDuration.setBounds(200,100,100,25);
		    		add(txtDuration);
		    		// Location
		    		lblLocation.setBounds(30,130,150,20);
		    		add(lblLocation);
		    		txtLocation.setBounds(200,130,200,25);
		   			add(txtLocation);
		    		// Description
		    		lblDescription.setBounds(30,165,150,20);
		    		add(lblDescription);
		    		scroll.setBounds(200,165,250,60);
		    		add(scroll);
		    		// Buttons
		    		btnAppointAdd.setBounds(245,240,80,38);
		    		add(btnAppointAdd);
					btnAppointAdd.addActionListener(new ActionListener()
		    		{
		    			public void actionPerformed(ActionEvent ae)
		    			{
		    				r = JOptionPane.showInputDialog(null, "Do you want to set reminder? y/n ");
							if(r != null)
							{
								if(r.equals("y"))
								{
									try{
										addNewAppointment("y");
		    						
		    						}catch(Exception e){}
		    					}
		    					else if(r.equals("n"))
		    					{
									JOptionPane.showMessageDialog(null,"You don't want to set reminder for your appointment:");
									addNewAppointment("n");
								}
								else
									JOptionPane.showMessageDialog(null,"Choose a valid option:");
		    				
							}
							else
								JOptionPane.showMessageDialog(null,"Choose a valid option:");	
						}
		    		});
		 			btnAppointExit.setBounds(330,240,80,38);
		    		add(btnAppointExit);
		    		btnAppointExit.addActionListener(new ActionListener()
		    		{
		    			public void actionPerformed(ActionEvent ae)
		    			{
		    				setVisible(false);
		    				dispose();
		    			}
		    		});	
		    		txtaMessage.setEditable(false);
		    		txtaMessage.setLineWrap(true);
		    		txtaMessage.setBackground(getBackground());
		    		txtaMessage.setWrapStyleWord(true);
		    		JScrollPane appoint_inside = new JScrollPane(txtaMessage);
		    		appoint_inside.setBounds(480,20,150,80);
		    		add(appoint_inside);    
				}
			}
			/**
			 * An inner class of CalendarEntries that builds the task tabbed pane
			 */
	    	class TaskTabPanel extends JPanel
	    	{
	    		/**
	    		 * Constructs the task tabbed panel
	    		 */
	    		public TaskTabPanel()
	    		{
		    		setLayout(null);
		    		setBorder(BorderFactory.createEtchedBorder() );
		    		// Task Name
		    		lblTaskName.setBounds(30,20,150,20);
		    		add(lblTaskName);
		    		txtTaskName.setBounds(200,20,200,25);
		    		add(txtTaskName);
		    		// Task Deadline
		    		lblTaskDeadLine.setBounds(30,60,150,20);
		    		add(lblTaskDeadLine);
//		    		pnlTaskDate.setBounds(200,60,150,25);
//		    		add(pnlTaskDate);
		   			// Task Status
		    		lblTaskStatus.setBounds(30,100,150,20);
		    		add(lblTaskStatus);
		    		cbxTaskStatus.setBounds(200,100,150,25);
		    		add(cbxTaskStatus);
		    		// Description
		    		lblTaskDescription.setBounds(30,140,150,20);
		    		add(lblTaskDescription);
		    		taskScroll.setBounds(200,140,250,60);
		    		add(taskScroll);
		    		// Buttons
		    		btnTaskAdd.setBounds(245,240,80,38);
		    		add(btnTaskAdd);
		    		btnTaskAdd.addActionListener(new ActionListener()
		    		{
		    			public void actionPerformed(ActionEvent ae)
		    			{
		    				r = JOptionPane.showInputDialog(null, "Do you want to set a reminder? y/n ");
							if(r != null)
							{
								if(r.equals("y"))
								{
									try{
										addNewTask("y");
		    						}catch(Exception e){}
		    					}
		    					else if(r.equals("n"))
		    					{
									JOptionPane.showMessageDialog(null,"You don't want to set a reminder for your task:");
									addNewTask("n");
								}
								else
									JOptionPane.showMessageDialog(null,"Choose a valid option:");
		    				}
							else
								JOptionPane.showMessageDialog(null,"Choose a valid option:");
						}
		    		});
		    		btnTaskExit.setBounds(330,240,80,38);
		    		add(btnTaskExit);
		    		btnTaskExit.addActionListener(new ActionListener()
		    		{
		    			public void actionPerformed(ActionEvent ae)
		    			{
		    				setVisible(false);
		    				dispose();
		    			}
		    		});
		    		txtaTaskMessage.setEditable(false);
		    		txtaTaskMessage.setLineWrap(true);
		    		txtaTaskMessage.setBackground(getBackground());
		    		txtaTaskMessage.setWrapStyleWord(true);
		    		JScrollPane task_inside = new JScrollPane(txtaTaskMessage);
		    		task_inside.setBounds(480,20,150,80);
		    		add(task_inside);	    	    
				}
	    	}
	    	/**
			 *	An inner class of CalendarEntries that builds the reminder event tabbed pane
			 */
	    	class ReminderTabPanel extends JPanel
	    	{
	    		/**
	    		 * Constructs the reminder event tabbed panel
	    		 */
	    		public ReminderTabPanel()
	    		{
		    		setLayout(null);
		    		setBorder(BorderFactory.createEtchedBorder() );
		    		// Reminder Name
		    		lblReminderName.setBounds(30,20,150,20);
		    		add(lblReminderName);
		    		txtReminderName.setBounds(200,20,200,25);
		    		add(txtReminderName);
		    		// Reminder Date
		    		lblReminderDate.setBounds(30,60,150,20);
		    		add(lblReminderDate);
//		    		pnlReminderDate.setBounds(195,60,150,25);
		    //		add(pnlReminderDate);
		    		// Reminder Time to occur
		    		lblReminderTime.setBounds(30,100,150,20);
		    		add(lblReminderTime);
		    		txtReminderTime.setBounds(200,100,150,25);
		    		add(txtReminderTime);
		    		// Reminder Description
		    		lblReminderDescription.setBounds(30,140,150,20);
		    		add(lblReminderDescription);
		    		reminderScroll.setBounds(200,140,250,60);
		    		add(reminderScroll);
		    		// Buttons
		    		btnReminderAdd.setBounds(245,240,80,38);
		    		add(btnReminderAdd);
		    		btnReminderAdd.addActionListener(new ActionListener()
		    		{
		    			public void actionPerformed(ActionEvent ae)
		    			{
		    				r = JOptionPane.showInputDialog(null, "Do you want to set a reminder? y/n ");
							if(r != null)
							{
								if(r.equals("y"))
								{
									try{
										addNewReminder("y");
		    						}catch(Exception e){}
		    					}
		    					else if(r.equals("n"))
		    					{
									JOptionPane.showMessageDialog(null,"You don't want to set a reminder for your reminder event:");
									addNewReminder("n");
								}
								else
									JOptionPane.showMessageDialog(null,"Choose a valid option:");
		    				}
							else
								JOptionPane.showMessageDialog(null,"Choose a valid option:");
		    			}
		    		});
		    	   	btnReminderExit.setBounds(330,240,80,38);
		    		add(btnReminderExit);
		    		btnReminderExit.addActionListener(new ActionListener()
		    		{
		    			public void actionPerformed(ActionEvent ae)
		    			{
		    				setVisible(false);
		    				dispose();
		    			}
		    		});	    	
		    		txtaReminderMessage.setEditable(false);
		    		txtaReminderMessage.setLineWrap(true);
		    		txtaReminderMessage.setBackground(getBackground());
		    		txtaReminderMessage.setWrapStyleWord(true);
		    		JScrollPane reminder_inside = new JScrollPane(txtaReminderMessage);
		    		reminder_inside.setBounds(480,20,150,80);
		    		add(reminder_inside);	    									
				}
	    	}
	    	/**
	    	 * Adds new appointment with reminder option
	    	 * @param reminderOption the reminder option for the appointment
	    	 */
	    	private  void addNewAppointment(String reminderOption) 
	    	{
	    		appointmentName = txtAppointmentName.getText();
//				String appDateStr = pnlAppointmentDate.input.getText();
//				year = Integer.parseInt(appDateStr.substring(7,11));
		//		String month = appDateStr.substring(3,6);
				String [] monthCon = {"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				for(int i = 0;i<monthCon.length;i++)
				{
	/*				if(month.equals(monthCon[i]))
					{
						mon = i+1;
						break;
					}*/
				}
//				day = Integer.parseInt(appDateStr.substring(0,2));
				String appStartTime = txtStartTime.getText();
				hour = Integer.parseInt(appStartTime.substring(0,2));
				minute = Integer.parseInt(appStartTime.substring(3,5));
				String appointmentDurationStr = txtDuration.getText();
				int appDuration = Integer.parseInt(appointmentDurationStr);
				String appointmentLocationStr = txtLocation.getText();
				String appointmentDescriptionStr = txtaDescription.getText();
				if(reminderOption.equals("y"))
				{
					setReminder("Appointment",year, mon, day, hour, minute, appointmentName);
				}
//	      		Appointment app = new Appointment(1,appointmentName, 
//	      		new Date(year-1900, mon-1, day), appStartTime, appDuration,
	 //     			appointmentLocationStr,appointmentDescriptionStr);
//	        	ce.addCalendarEntryComponent(app);
	        	String s = "Appointment "+appointmentName+" is created. "+"\nDo you want create another appointment?";
	        	int i = JOptionPane.showConfirmDialog(null, s, "Appointment",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(i == JOptionPane.YES_OPTION)
				{
					txtAppointmentName.setText("");
					txtAppointmentName.requestFocus();
					txtStartTime.setText("");
					txtDuration.setText("");
					txtLocation.setText("");
					txtaDescription.setText("");
				}
			}
			/**
	    	 * Adds new task with reminder option
	    	 * @param reminderOption the reminder option for the task.
	    	 */
			private  void addNewTask(String reminderOption)
			{
				String taskNameStr = txtTaskName.getText();
//				String taskDateStr = pnlTaskDate.input.getText();
//				taskYear = Integer.parseInt(taskDateStr.substring(7,11));
		//		String month = taskDateStr.substring(3,6);
				String [] monthCon = {"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				for(int i = 0;i<monthCon.length;i++)
				{
	/*				if(month.equals(monthCon[i]))
					{
						taskMon = i+1;
						break;
					}*/
				}
//				taskDay = Integer.parseInt(taskDateStr.substring(0,2));
				String s =(String) cbxTaskStatus.getSelectedItem();
				if(s.equals("Completed"))
				{
					taskStatus = true;
				}
				else if(s.equals("Not Completed"))
				{
					taskStatus = false;
				}
				String taskDescriptionStr = txtaTaskDescription.getText();
				if(reminderOption.equals("y"))
				{
					setReminder("Task",taskYear, taskMon, taskDay, 23, 60, taskNameStr);
				}
//				Task ta = new Task(2,taskNameStr, new Date(taskYear-1900, taskMon-1, taskDay) ,taskStatus,taskDescriptionStr);
//				ce.addCalendarEntryComponent(ta);
				String s1 = "Task "+taskNameStr+" is created. "+"\nDo you want to create another task?";
	        	int i = JOptionPane.showConfirmDialog(null, s1, "Task",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(i == JOptionPane.YES_OPTION)
				{
					txtTaskName.setText("");
					txtTaskName.requestFocus();
					txtaTaskDescription.setText("");
				}
			}
			/**
	    	 * Adds new reminder event with reminder option
	    	 * @param reminderOption the reminder option for the reminder event.
	    	 */
			private void addNewReminder(String reminderOption) 
			{
				String reminderNameStr = txtReminderName.getText();
//				String reminderDateStr = pnlReminderDate.input.getText();
//				int year = Integer.parseInt(reminderDateStr.substring(7,11));
//				String month = reminderDateStr.substring(3,6);
				int mon = 0;
				String [] monthCon = {"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				for(int i = 0;i<monthCon.length;i++)
				{
	/*				if(month.equals(monthCon[i]))
					{
						mon = i+1;
						break;
					}*/
				}
//				int day = Integer.parseInt(reminderDateStr.substring(0,2));
				String reTimeStr = txtReminderTime.getText();
				int hour = Integer.parseInt(reTimeStr.substring(0,2));
				int minute = Integer.parseInt(reTimeStr.substring(3,5));
				String reminderDescriptionStr =txtaReminderDescription.getText();
				if(reminderOption.equals("y"))
				{
					setReminder("Reminder Event",year, mon, day, hour, minute, reminderNameStr);
				}			
//				Reminder re = new Reminder(3,reminderNameStr, new Date(year-1900, mon-1, day), reTimeStr, reminderDescriptionStr);
//				ce.addCalendarEntryComponent(re);
				String s1 = "Reminder Event "+reminderNameStr+" is created. "+"\nDo you want create another reminder event?";
	        	int i = JOptionPane.showConfirmDialog(null, s1, "Reminder Event",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(i == JOptionPane.YES_OPTION)
				{
					txtReminderName.setText("");
					txtReminderName.requestFocus();
					txtReminderTime.setText("");
				}
			}
			/**
			 * Sets reminder for appointment, task and reminder events.
			 * It sets four reminders for each calendar entry.
			 * @param s1 the calendar entry type
		 	 * @param year the reminder year
		     * @param mon the reminder month
		     * @param day the reminder day
		     * @param hour the reminder hour
		     * @param minute the reminder minute
		     * @param s2 the calendar entry name.
			 */
			private void setReminder(String s1,int year, int mon, int day, int hour, int minute,String s2)
			{
			   JOptionPane.showMessageDialog(null,"There will be four reminders for your "+s1+
					"\nReminder No 1: reminder at the start"+
					"\nReminder No 2: reminder before 15 minutes"+
					"\nReminder No 3: reminder before 1 hour"+
					"\nReminder No 4: reminder before 1 day");
				int min = minute;
				int hou = hour;
				if(min < 15)
				{
					hou = hou - 1;
					min = (min + 60) - min;
				
				}	
//				ReminderSetter sr1 = new ReminderSetter(year, mon, day, hour, minute,s1,s2, "Reminder No:1");
		//		myCalendar.addReminderSetter(sr1);
	/*			ReminderSetter sr2 = new ReminderSetter(year, mon, day, hou, min-15,s1,s2, "Reminder No:2");
				myCalendar.addReminderSetter(sr2);
				ReminderSetter sr3 = new ReminderSetter(year, mon, day, hour-1, minute,s1,s2, "Reminder No:3");
				myCalendar.addReminderSetter(sr3);
				ReminderSetter sr4 = new ReminderSetter(year, mon, day-1, hour, minute,s1,s2, "Reminder No:4");
				myCalendar.addReminderSetter(sr4);*/
			}
		}
		/**
		 * An inner class of GUIVideoRetrieval class.
		 * This class has the methods to show daily and weekly calendar
		 * and also this class builds the GUI for displaying them.
		 * This class also contains the method to remove calendar entry.
		 */
		public class ShowingCalendar extends JDialog 
		{
			JPanel main_panel,top_panel,center_panel, bottom_panel;
			JLabel  lblTitle;
			JTable table;
			JButton  btnExit, btnRemoveApp, btnRemoveTa, btnRemoveRe;
			Font font, fonts;
			JScrollPane scrolls, scroll;
			GregorianCalendar calendar;
			Date date;
			String strDate, strTime, sTime; 
			int day, month, year;
			/**
	     	 * Constructs the ShowingCalendar main panel
	     	 * @param parent the parent JFrame 
	     	 * @param title the title of the showing calendar frame
	     	 * @param modal the modal
	     	 * @param colNames as column names of the table
	     	 * @param columnSize the width of the table column.
	     	 */
			public ShowingCalendar(JFrame parent, String title, boolean modal, Vector colNames, int columnSize)
			{
				super(parent, title, modal);	
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);				
				setSize(850,450);
	        	setLocation(80,150);		
				main_panel = new JPanel();
				getContentPane().add(main_panel);
				main_panel.setLayout(new BorderLayout() );
				font = new Font("Comic Sans Ms", Font.PLAIN, 15);
				fonts = new Font("Tahoma", Font.PLAIN, 18);
				lblTitle = new JLabel("Personal Calendar");
				lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));	
				lblTitle.setForeground(new Color(0,0,120) );
				btnRemoveApp = new JButton("Remove Appointment", new ImageIcon("images\\removeapp.gif"));		
				btnRemoveApp.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnRemoveApp.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnRemoveTa= new JButton("Remove Task", new ImageIcon("images\\removetask.gif"));		
				btnRemoveTa.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnRemoveTa.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnRemoveRe = new JButton("Remove Reminder" , new ImageIcon("images\\removerem.gif"));		
				btnRemoveRe.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnRemoveRe.setHorizontalTextPosition(AbstractButton.CENTER);
				btnExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );		
				btnExit.setVerticalTextPosition(AbstractButton.BOTTOM);
	    		btnExit.setHorizontalTextPosition(AbstractButton.CENTER);
	    		btnExit.setMnemonic('E');
				columnNames = colNames;		
				data =  new Vector();			 
				table = new JTable(data, columnNames);
				table.putClientProperty("terminateEditOnFocusLost", Boolean.FALSE);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getTableHeader().setReorderingAllowed(false);				
				int hr = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				int vr = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				scrolls = new JScrollPane(table,vr,hr);		
				scrolls.setPreferredSize(new Dimension(1024, 420) );
				TableColumn columnWidth = null;
				for(int i=0; i <columnSize ; i++)
				{
					columnWidth = table.getColumnModel().getColumn(i);
					columnWidth.setPreferredWidth(125);
				}	
				buildTopPanel();
	 			buildCenterPanel();
				buildBottomPanel();		
				buildAllEvents();		
				main_panel.add(top_panel, BorderLayout.PAGE_START);
				main_panel.add(center_panel, BorderLayout.CENTER);
				main_panel.add(bottom_panel, BorderLayout.PAGE_END);					
			}
			/**
			 * A method that builds the top panel.
			 */	
			public void buildTopPanel()
			{
				top_panel = new JPanel();
				top_panel.setLayout(new BorderLayout() );
				JPanel insidepanel = new JPanel();
				insidepanel.setLayout(new FlowLayout(FlowLayout.LEFT) );
				JPanel insidee = new JPanel();
				insidee.setLayout(new FlowLayout() );
				insidee.add(lblTitle);		
				JPanel inside = new JPanel();
				inside.setLayout(new GridLayout(0,1) );		
				inside.add(insidee);	
				top_panel.add(inside, BorderLayout.CENTER);			
			}
			/**
			 * Method that builds the centre panel.
			 */
			public void buildCenterPanel()
			{		
				center_panel = new JPanel();
				center_panel.setLayout(new BorderLayout() );
				JPanel insidecentre = new JPanel();
				insidecentre.setLayout(new FlowLayout() );
				JPanel centerpanel = new JPanel();
				centerpanel.setLayout(new GridLayout(0,1) );
				center_panel.add(insidecentre, BorderLayout.PAGE_START);
				center_panel.add(scrolls, BorderLayout.CENTER);			
			}
			/**
			 * Method that builds the bottom panel.
			 */
			public void buildBottomPanel()
			{
				bottom_panel = new JPanel();
				bottom_panel.setLayout(new BorderLayout() );			
				JPanel ontop = new JPanel();
				ontop.setLayout(new FlowLayout(FlowLayout.RIGHT) );
				ontop.add(btnRemoveApp);
				btnRemoveApp.setEnabled(false);
				ontop.add(btnRemoveTa);
				btnRemoveTa.setEnabled(false);
				ontop.add(btnRemoveRe);
				btnRemoveRe.setEnabled(false);
				ontop.add(btnExit);		
				JPanel both = new JPanel();
				both.setLayout(new GridLayout(0,1) );
				both.add(ontop);				
				bottom_panel.add(both, BorderLayout.PAGE_START);		
			}
			/**
			 * Method that holds all the action for all the button 
			 * in showing calendar frame.
			 */	
			public void buildAllEvents()
			{
				btnExit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{	
						setVisible(false);
						dispose();
					}
				});
				btnRemoveApp.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{	
						removeAppointmentAndReminder();
					}
				});
				btnRemoveTa.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{	
//						removeTask();
					}
				});
				btnRemoveRe.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{	
						removeAppointmentAndReminder();
					}
				});
			}
			/**
			 * A method that shows all the calendar entries for a particular given date
			 * @param userDate the date entered by user.
			 */
			/*private void showDailyCalendar(Date userDate) 
			{
				int	numOfEntries = ce.listCalendarEntryComponent().length; 
				for (int i=0; i<numOfEntries; i++)
	        	{
	        		if (ce.listCalendarEntryComponent()[i] != null)
	         		{
	         			if(ce.listCalendarEntryComponent()[i].getKey() == 1)
	            		{
	            			Appointment a = (Appointment)ce.listCalendarEntryComponent()[i];
	            			Date appDate = a.getCalendarEntryDate();
							if(appDate.equals(userDate))
	            			{            			            			
								Vector element = new Vector();
								element.addElement("Appointment");
								element.addElement(a.getCalendarEntryName());
								element.addElement(a.getAppStartTime());
								DateFormat df = DateFormat.getDateInstance();
								element.addElement(df.format(userDate));
								element.addElement(""+a.getAppDuration());
								element.addElement(a.getAppLocation());
								element.addElement("N/A");
								element.addElement(a.getDescription());
								data.addElement(element);            		
	            			}
	            		}
	            		if(ce.listCalendarEntryComponent()[i].getKey() == 2)
	            		{
	            			Task t = (Task)ce.listCalendarEntryComponent()[i];
	            			Date taskDate = t.getCalendarEntryDate();
							boolean status = t.getStatus();
							String st = "";
							if(status == true)
								st = "task completed";
							else
								st = "task not completed";
							if(taskDate.equals(userDate))
	            			{
	            				Vector element = new Vector();
								element.addElement("Task");
								element.addElement(t.getCalendarEntryName());
								element.addElement("N/A");
								DateFormat df = DateFormat.getDateInstance();
								element.addElement(df.format(userDate));						
								element.addElement("N/A");
								element.addElement("N/A");
								element.addElement(st);
								element.addElement(t.getDescription());
								data.addElement(element);            		
	            			}	
	            		}
	            		if(ce.listCalendarEntryComponent()[i].getKey() == 3)
	            		{
	            			Reminder r = (Reminder)ce.listCalendarEntryComponent()[i];
	            			Date reminderDate = r.getCalendarEntryDate();
	            			if(reminderDate.equals(userDate))
	            			{
								Vector element = new Vector();
								element.addElement("Reminder");
								element.addElement(r.getCalendarEntryName());
								element.addElement(r.getTimeToOccur());
								DateFormat df = DateFormat.getDateInstance();
								element.addElement(df.format(userDate));
								element.addElement("N/A");
								element.addElement("N/A");
								element.addElement("N/A");
								element.addElement(r.getDescription());
								data.addElement(element);            		            		
	            			}
	            		}
	          		}
	          	}
			
			}*/
			/**
			 * A method for showing the weekly calendar.
			 * The week beginning date is entered by the user.
			 * @param beginningDateStr the start of the week entered by user.
			 */
			private void showWeeklyCalendar(String beginningDateStr)
			{
				int ye = Integer.parseInt(beginningDateStr.substring(6,10));
				int mon = Integer.parseInt(beginningDateStr.substring(3,5));
				int da = Integer.parseInt(beginningDateStr.substring(0,2));
				GregorianCalendar gc = new GregorianCalendar(ye, mon-1, da);
				int year = gc.get(GregorianCalendar.YEAR);
	      		int month = gc.get(GregorianCalendar.MONTH);
	      		int date = gc.get(GregorianCalendar.DATE);
	      		GregorianCalendar firstDay, oneDay,twoDay,threeDay,fourDay,fiveDay,sixDay;
	      		firstDay = new GregorianCalendar(year, month, date);
	      		oneDay = new GregorianCalendar(year, month, date);
	      		twoDay = new GregorianCalendar(year, month, date);
	      		threeDay = new GregorianCalendar(year, month, date);
	      		fourDay = new GregorianCalendar(year, month, date);
	      		fiveDay = new GregorianCalendar(year, month, date);
	      		sixDay = new GregorianCalendar(year, month, date);
	      		oneDay.add(GregorianCalendar.DATE, 1);
	      		twoDay.add(GregorianCalendar.DATE, 2);
	      		threeDay.add(GregorianCalendar.DATE, 3);
	      		fourDay.add(GregorianCalendar.DATE, 4);
	      		fiveDay.add(GregorianCalendar.DATE, 5);
	      		sixDay.add(GregorianCalendar.DATE, 6);
	      		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG); 
	      		Date [] week = {firstDay.getTime(),oneDay.getTime(),twoDay.getTime(),threeDay.getTime(),fourDay.getTime(),
	      			fiveDay.getTime(),sixDay.getTime()};
//	      		int numOfEntries = ce.listCalendarEntryComponent().length; 
				/*for(int j = 0; j<week.length; j++)
				{
					for (int i=0; i<numOfEntries; i++)
	        		{
	            		if (ce.listCalendarEntryComponent()[i] != null)
	            		{
	                		if(ce.listCalendarEntryComponent()[i].getKey() == 1)
	            			{
	            				Appointment a = (Appointment)ce.listCalendarEntryComponent()[i];
	            				Date appDate = a.getCalendarEntryDate();
								if(appDate.equals(week[j]))
	            				{
	            					Vector element = new Vector();
									element.addElement("Appointment");
									element.addElement(a.getCalendarEntryName());
									element.addElement(a.getAppStartTime());
									DateFormat df1 = DateFormat.getDateInstance();
									element.addElement(df1.format(appDate));
									element.addElement(""+a.getAppDuration());
									element.addElement(a.getAppLocation());
									element.addElement("N/A");
									element.addElement(a.getDescription());
									data.addElement(element);            		
	            				}
	            			}
	            			if(ce.listCalendarEntryComponent()[i].getKey() == 2)
	            			{
	            				Task t = (Task)ce.listCalendarEntryComponent()[i];
	            				Date taskDate = t.getCalendarEntryDate();
								boolean status = t.getStatus();
								String st = "";
								if(status == true)
									st = "task completed";
								else
									st = "task not completed";
								if(taskDate.equals(week[j]))
	            				{
	            					Vector element = new Vector();
									element.addElement("Task");
									element.addElement(t.getCalendarEntryName());
									element.addElement("N/A");
									DateFormat df2 = DateFormat.getDateInstance();
									element.addElement(df2.format(taskDate));						
									element.addElement("N/A");
									element.addElement("N/A");
									element.addElement(st);
									element.addElement(t.getDescription());
									data.addElement(element);            		
	            				}
	            			}
	            			if(ce.listCalendarEntryComponent()[i].getKey() == 3)
	            			{
	            				Reminder r = (Reminder)ce.listCalendarEntryComponent()[i];
	            				Date reminderDate = r.getCalendarEntryDate();
	            				if(reminderDate.equals(week[j]))
	            				{
	            					Vector element = new Vector();
									element.addElement("Reminder");
									element.addElement(r.getCalendarEntryName());
									element.addElement(r.getTimeToOccur());
									DateFormat df3 = DateFormat.getDateInstance();
									element.addElement(df3.format(reminderDate));
									element.addElement("N/A");
									element.addElement("N/A");
									element.addElement("N/A");
									element.addElement(r.getDescription());
									data.addElement(element);            		            		            			
								}
	            			}
	            		}
	        		}//inner for loop ends
	        	}*///outer for loop ends
			}
			/**
			 * A method that removes appointment and reminder event.
			 */
			public void removeAppointmentAndReminder()
			{
				String removeItemTimeStr=JOptionPane.showInputDialog(null,"Enter the time (Start time or Time to occur)\n:hh mm(24hr clock)");	
	        	int hour = Integer.parseInt(removeItemTimeStr.substring(0,2));
				int minute = Integer.parseInt(removeItemTimeStr.substring(3,5));
//				int numOfEntries = ce.listCalendarEntryComponent().length; 
				//for (int i=0; i<numOfEntries; i++)
	        	{
	           		/*if (ce.listCalendarEntryComponent()[i] != null)
	           		{
	           			if(ce.listCalendarEntryComponent()[i].getKey() == 1 )
	           			{
	           				Appointment a = (Appointment)ce.listCalendarEntryComponent()[i];
	           				String appStart = a.getAppStartTime();
							if(removeItemTimeStr.equals(appStart))
	           				{
	           					ce.removeCalendarEntryComponent(a);
	           					JOptionPane.showMessageDialog(null, "This appointment is deleted\nNow check with Show menu item");
	           				}
	           			}
	           			if(ce.listCalendarEntryComponent()[i].getKey() == 3)
	           			{
	           				Reminder r = (Reminder)ce.listCalendarEntryComponent()[i];
	           				String reminderTime = r.getTimeToOccur();
	           				if(removeItemTimeStr.equals(reminderTime))
	           				{
	           					ce.removeCalendarEntryComponent(r);
	           					JOptionPane.showMessageDialog(null, "This Reminder Event is Deleted\nNow check with Show menu item");
	           				}
	           			}
					}*/
				}//for loop ends
			}
			/**
			 * A method that removes task
			 */
		/*	private void removeTask()
			{
				String removeTaskNameStr=JOptionPane.showInputDialog(null,"Enter the task name which you want to delete");	
	        	int numOfEntries = ce.listCalendarEntryComponent().length; 
				for (int i=0; i<numOfEntries; i++)
	        	{
	           		if (ce.listCalendarEntryComponent()[i] != null)
	           		{
	           			if(ce.listCalendarEntryComponent()[i].getKey() == 2 )
	           			{
	           				Task t = (Task)ce.listCalendarEntryComponent()[i];
	           				String taskName = t.getCalendarEntryName();
							if(removeTaskNameStr.equals(taskName))
	           				{
	           					ce.removeCalendarEntryComponent(t);
	           					JOptionPane.showMessageDialog(null, "This task is deleted\nNow check with Show menu item");
	           				}
	           			}
	           		}
				}
			}*/
			/**
			 * Method that shows all the reminders of the current date.
			 */
		/*	private void showTodaysReminder()
			{
				int numOfEntries = myCalendar.listReminderSetter().length;
				for (int i=0; i<numOfEntries; i++)
	        	{
	        		if(myCalendar.listReminderSetter()[i] != null)
	        		{
	        			ReminderSetter rs = (ReminderSetter)myCalendar.listReminderSetter()[i]; 
	            		Date today = new Date();
	            		Date da = rs.getReminderDate();
	            		if(today.getYear()==da.getYear()&&today.getMonth()==da.getMonth()&&today.getDate()==da.getDate())
	            		{
	            			Vector element = new Vector();
							element.addElement(rs.getReCalendarEntryType());
							element.addElement(rs.getReCalendarEntryName());
							element.addElement(rs.getReminderNo());
							DateFormat df3 = DateFormat.getDateInstance();
							element.addElement(df3.format(da));
							element.addElement(" "+rs.getReminderHour()+" "+rs.getReminderMinute());
							data.addElement(element);
						}
	            
	           		}
	           	}
			}*/

		}//class ends ShowingCalendar
	}//class ends GUIVideoRetrieval