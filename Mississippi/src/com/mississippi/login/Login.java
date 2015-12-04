package com.mississippi.login;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.sql.*;

import javax.swing.*;

import com.mississippi.databaseaccess.DB;
import com.mississippi.gui.StaffCaller;
import com.mississippi.nexus.*;

import java.security.*;

@SuppressWarnings("serial")
public class Login extends JFrame
{	//initialize buttons and things lol
	JLabel HeaderLBL = new JLabel("Staff Login");
	JLabel IDLBL = new JLabel("Staff ID");
	JLabel PassLBL = new JLabel("Password");
	JTextField IDTXT = new JTextField();
	JPasswordField PassTXT = new JPasswordField();
	JButton SubmitBTN = new JButton("Enter");
	DB passcheck;
	String user;
	String pass;
	
	public Login()
	{	//add things to GUI
		setLayout(new GridBagLayout());
		setSize(400, 400);
		setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		HeaderLBL.setHorizontalAlignment(JLabel.CENTER);
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		add(HeaderLBL, c);
		
		IDLBL.setHorizontalAlignment(JLabel.RIGHT);
		c.gridy = 1;
		c.gridwidth = 1;
		add(IDLBL, c);
		
		PassLBL.setHorizontalAlignment(JLabel.RIGHT);
		c.gridy = 2;
		add(PassLBL, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.ipadx = 100;
		add(IDTXT, c);
		
		c.gridy = 2;
		add(PassTXT, c);
		
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		add(SubmitBTN, c);
		SubmitBTN.addActionListener(new ListenToLogin());
	}
	
	public String getHash(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{	//hash password
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		byte[] input = digest.digest(pass.getBytes("UTF-8"));
		return String.format("%s",new java.math.BigInteger(1, input));
	 }
	
	class ListenToLogin implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
        {
			user = IDTXT.getText();
			pass = PassTXT.getText();
			String passHash = null;
			try {
				passHash = getHash(pass);
			}
			catch (NoSuchAlgorithmException e2)
			{
				e2.printStackTrace();
			}
			catch (UnsupportedEncodingException e2)
			{
				e2.printStackTrace();
			}
			//check user id
			String idQuery = ("SELECT StaffID FROM staff;");
			passcheck = new DB();
			passcheck.createConnection();
			ResultSet rs = passcheck.selectCustom(idQuery);
			try
			{
				while(rs.next())
				if(IDTXT.getText().equals(rs.getString("StaffID")))
				{
					//check password
					String query = ("SELECT PassHash FROM staff where StaffID = '" + IDTXT.getText() + "';");
					passcheck = new DB();
					passcheck.createConnection();
					ResultSet rs2 = passcheck.selectCustom(query);
					while(rs2.next())
						if(passHash.equals(rs2.getString("PassHash")))
						{
							new StaffCaller();
							Login.this.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect password!", "Login Error", JOptionPane.ERROR_MESSAGE);
						}
					break;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "That user ID does not exist!", "Login Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}	
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
        }
	}
}