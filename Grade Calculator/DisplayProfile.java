package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
 
  public class DisplayProfile extends JFrame implements ActionListener
  { 
    JLabel title, lblName, lblEmail, lblUsername, lblPassword, lblGender, lblAddress, lblCountry, lblState, lblPhone,  
	lblDepartment, lblCity, lblPin;
    JTextField textName, textEmail, textUname, textPassword, textGender, textCountry, textState,  textPhone, textDepartment, textCity, textPin;
    JButton btnEdit, btnBack;
	JTextArea textAddress;
	JScrollPane scrollPane;
	String sName, sEmail, sUname, sPass, sGender, sAddr, sCountry, sState, sPhone, sDept, sCity, sPin,regEmail;
	// Database Connectivity
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
	
    DisplayProfile(String title1,String userEmail1, int xValue1, int yValue1)
	//DisplayProfile()
    {
		setLayout(null);
		regEmail=userEmail1;
		// Step 1: Loading or registering Oracle JDBC driver class
		// Step 1: Loading or registering Oracle JDBC driver class
		Config db = new Config();
		connect = db.getConnection();
		// Step 2: Opening database connect
		try {

			
			// Step 2.B: Creating JDBC Statement 
			statement = connect.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("select * from faculty_master where email='"+userEmail1+"'");
			//resultSet = statement.executeQuery("select * from faculty_master where email='gyanendra89@gmail.com'");
			// processing returned data and printing into console
			if(resultSet.next()){
				sName 		= new String(resultSet.getString("fullname"));
				sEmail 		= new String(resultSet.getString("email"));
				sUname 		= new String(resultSet.getString("username"));
				sPass 		= new String(resultSet.getString("password"));
				sGender 	= new String(resultSet.getString("gender"));
				sPhone 		= new String(resultSet.getString("contact_no"));
				sDept 		= new String(resultSet.getString("department"));
				sAddr 		= new String(resultSet.getString("address"));
				sCountry 	= new String(resultSet.getString("country"));
				sState 		= new String(resultSet.getString("state"));
				sCity 		= new String(resultSet.getString("city"));
				sPin 		= new String(resultSet.getString("pin"));
			}
			
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}finally {

			// Step 3: Closing database connect
			try {
				if(null != connect) {

					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connect
					connect.close();
				}
			}
			catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		
		// Label of Input Fields
        lblName 		= new JLabel("Full Name:");
        lblEmail 		= new JLabel("Email-ID:");
        lblUsername		= new JLabel("Username:");		
        lblPassword 	= new JLabel("Password:");
        lblGender		= new JLabel("Gender");
        lblPhone 		= new JLabel("Phone No:");
		lblDepartment	= new JLabel("Department");
		lblAddress		= new JLabel("Address");	
        lblCountry 		= new JLabel("Country:");
        lblState 		= new JLabel("State:");
		lblCity			= new JLabel("City");
        lblPin			= new JLabel("Pin");
		// Textarea
		textAddress 	= new JTextArea(sAddr);
		textAddress.setLineWrap(true);
		textAddress.setEditable(false);
		scrollPane 		= new JScrollPane(textAddress); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Buttons
        btnEdit 		= new JButton("Edit",new ImageIcon("images/edit1.gif"));
        btnBack 		= new JButton("Back",new ImageIcon("images/back1.gif"));
				
		// Text Field of Labels
        textName 		= new JTextField(sName);
		textName.setEditable(false); 
        textEmail 		= new JTextField(sEmail);
		textEmail.setEditable(false); 
		textUname 		= new JTextField(sUname);
		textUname.setEditable(false); 
        textPassword	= new JTextField(sPass);
		textPassword.setEditable(false); 
        textGender		= new JTextField(sGender);
		textGender.setEditable(false); 
        textCountry		= new JTextField(sCountry);
		textCountry.setEditable(false); 
        textState 		= new JTextField(sState);
		textState.setEditable(false); 
        textCity 		= new JTextField(sCity);
		textCity.setEditable(false); 
        textPhone 		= new JTextField(sPhone);
		textPhone.setEditable(false); 
		textDepartment	= new JTextField(sDept);
		textDepartment.setEditable(false); 
		textPin			= new JTextField(sPin);
		textPin.setEditable(false); 
        
		
       // setting position of Labels, Buttons aand TextField
        
		lblName.setBounds(80, 20, 200, 30);
        lblEmail.setBounds(80, 60, 200, 30);
        lblUsername.setBounds(80, 100, 200, 30);
        lblPassword.setBounds(80, 140, 200, 30);
        lblGender.setBounds(80, 180, 200, 30);
        lblPhone.setBounds(80, 220, 200, 30);
        lblDepartment.setBounds(80, 260, 200, 30);
		lblAddress.setBounds(80, 300, 200, 30);
        lblCountry.setBounds(80, 400, 200, 30);
        lblState.setBounds(80, 440, 200, 30);
        lblCity.setBounds(80, 480, 200, 30);
        lblPin.setBounds(80, 520, 200, 30);
		
		textName.setBounds(300, 20, 200, 30);
        textEmail.setBounds(300, 60, 200, 30);
        textUname.setBounds(300, 100, 200, 30);
        textPassword.setBounds(300, 140, 200, 30);
        textGender.setBounds(300, 180, 200, 30);
        textPhone.setBounds(300, 220, 200, 30);
        textDepartment.setBounds(300, 260, 200, 30);
		scrollPane.setBounds(300, 300, 200, 50);
        textCountry.setBounds(300, 400, 200, 30);
        textState.setBounds(300, 440, 200, 30);
        textCity.setBounds(300, 480, 200, 30);
        textPin.setBounds(300, 520, 200, 30);
		btnEdit.setBounds(150, 580, 100, 30);
        btnBack.setBounds(250, 580, 100, 30);
		
		// add components to panel
		add(lblName);
        add(textName);
		add(lblEmail);
        add(textEmail); 
		add(lblUsername);
        add(textUname);
        add(lblPassword);
        add(textPassword);
		add(lblGender);
		add(textGender);
        add(lblPhone);
        add(textPhone);
        add(lblDepartment);
        add(textDepartment);
        add(lblAddress);
		add(scrollPane);
		add(lblCountry);
        add(textCountry);
        add(lblState);
        add(textState);
        add(lblCity);
        add(textCity);
        add(lblPin);
        add(textPin);
        add(btnEdit);
        add(btnBack);
		
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
        setSize(xValue1, yValue1);
        //setSize(700, 650);
        setVisible(true);
		setTitle(title1);
		
		
		//Action to Buttons
		btnEdit.addActionListener(this);
		btnBack.addActionListener(this);
       
    }
	
    public void actionPerformed(ActionEvent e) 
    {
		String command = e.getActionCommand();
         if(e.getSource() == btnEdit)
         {
							setVisible(false);
							new EditProfile("Edit Profile",regEmail, 800,700);
			
         }else if(e.getSource() == btnBack){
							setVisible(false);
		 }
    } 
    public static void main(String args[])
    {	
		String userEmail = new String();
		String title	 = new String();
		int xValue=0, yValue=0;
        //new DisplayProfile();
		new DisplayProfile(title,userEmail, xValue, yValue);
    }
}