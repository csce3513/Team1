/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class DB_class extends JFrame implements ActionListener {
	private Container container;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
 
	private JButton Login, Cancel;
	private JLabel USer, Password;
	private JTextField txtUser;
	private JPasswordField txtPassword;
 
	public DB_class()
	{
 
		setTitle("Login Screen");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); //disable resizing and Max button
 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(300,150);
		setLocationRelativeTo(null);
 
		container = getContentPane();
		layout = new GridBagLayout();
		container.setLayout(layout);
 
		gbc = new GridBagConstraints();
 
		USer = new JLabel("Username:");
		gbc.insets = new Insets(2,2,2,2);
		container.add(USer, gbc);
 
		txtUser = new JTextField(15);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		container.add(txtUser, gbc);
 
		Password = new JLabel("Password:");
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		container.add(Password, gbc);
 
		txtPassword = new JPasswordField(15);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		container.add(txtPassword, gbc);
 
		Login = new JButton("Login");
		Login.addActionListener( this );
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		container.add(Login, gbc);
 
		Cancel = new JButton("Cancel");
		Cancel.addActionListener( this );
		gbc.gridx = 2;
		container.add(Cancel, gbc);
	} 
 
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Login")){
			JOptionPane.showMessageDialog(null, "Wrong Username or Password, try again", "Warning !!!", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			//default icon, custom title
			int respond = JOptionPane.showConfirmDialog(null, "Would you like exiting the program ?", "Exiting", JOptionPane.YES_NO_OPTION);
			//System.out.println(respond);
 
			if(respond == 0){
				dispose (); //closing the frame
			}
		}
	} //actionPerformed()
 
	public static void connect()
	{
		Connection conn = null;
 
		try
		{
			String userName = "battlechess";
			String password = "b@ttl3CHe$$";
			String url = "jdbc:mysql://jarvis.cast.uark.edu/Member";
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection (url, userName, password);
			System.out.println ("Database connection established");
		}
		catch (Exception e)
		{
			System.err.println ("Cannot connect to database server");
		}
 
		finally
		{
			if (conn != null)
			{
				try{
					conn.close ();
					System.out.println ("Database connection terminated");
				}
				catch (Exception e) 
				{
					 
				}
			}
		}
	} 	
 
public static void main(String args[]) {
		new DB_class().setVisible(true);
		connect();
	} 
 
} 

