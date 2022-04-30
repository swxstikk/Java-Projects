package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
 
  public class ChangePassword extends JFrame implements ActionListener
  { 
    JLabel lblcurrPassword, lblnewPassword, lblnewPassword1;
    JPasswordField textcurrPassword, textnewPassword, textnewPassword1;
	String scurrPass, snewPassword, snewPassword1, sid,sEmail;
	JButton btnUpdate, btnCancel;
	// Database Connectivity
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
	
	
    ChangePassword(String title1, String userEmail1, int xValue1, int yValue1)
	//ChangePassword()
    {
		setLayout(null);
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
				scurrPass 		= new String(resultSet.getString("password"));
				sid		 		= new String(resultSet.getString("user_id"));
				sEmail	 		= new String(resultSet.getString("email"));
			}
			
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		
		// Label of Input Fields
        lblcurrPassword 		= new JLabel("Current Password:");
        lblnewPassword			= new JLabel("New Password:");
        lblnewPassword1 		= new JLabel("Re- Enter New Password:");
		
		// Text Field of Labels
		textcurrPassword		= new JPasswordField();
		textnewPassword			= new JPasswordField();
		textnewPassword1		= new JPasswordField();
		
		//Buttons
        btnUpdate 		= new JButton("Update");
        btnCancel 		= new JButton("Cancel");        
		
       // setting position of Labels, Buttons aand TextField
        
		lblcurrPassword.setBounds(40, 60, 200, 30);
        lblnewPassword.setBounds(40, 100, 200, 30);
        lblnewPassword1.setBounds(40, 140, 200, 30);
        
		textcurrPassword.setBounds(220, 60, 200, 30);
		textnewPassword.setBounds(220, 100, 200, 30);
		textnewPassword1.setBounds(220, 140, 200, 30);
        btnUpdate.setBounds(100, 190, 100, 30);
        btnCancel.setBounds(220, 190, 100, 30);
		
		// add components to panel
		add(lblcurrPassword);
        add(textcurrPassword);
		add(lblnewPassword);
		add(textnewPassword);
        add(lblnewPassword1);
        add(textnewPassword1);
        add(btnUpdate);
        add(btnCancel);
		
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
        setSize(xValue1, yValue1);
        setSize(500, 350);
        setVisible(true);
		setTitle(title1);
		
		
		//Action to Buttons
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);
       
    }
	
    public void actionPerformed(ActionEvent e) 
    {
		String command = e.getActionCommand();
         if(e.getSource() == btnUpdate)
         {
			int x = 0;
			String s1 	= new String(textcurrPassword.getPassword());
			String s2 	= new String(textnewPassword.getPassword());
			String s3	= new String(textnewPassword1.getPassword());
			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
			Date date = new Date();
			String modDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
			
			if(Validate.isEmpty(s1)){
				JOptionPane.showMessageDialog(this,"Blank Password field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textcurrPassword.setText("");
				textcurrPassword.requestFocusInWindow();
			}else{
				if(s1.equals(scurrPass))
				{
					if(Validate.isEmpty(s2)){
						JOptionPane.showMessageDialog(this,"Blank Confirm Password field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
						textnewPassword.setText("");
						textnewPassword.requestFocusInWindow();
					}
					if(Validate.isEmpty(s3)){
						JOptionPane.showMessageDialog(this,"Blank Confirm Password field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
						textnewPassword1.setText("");
						textnewPassword1.requestFocusInWindow();
					}else{
						if(s2.equals(s3))
						{
							snewPassword = s3;
							
							if(!snewPassword.isEmpty() && snewPassword!=null ){
							   try
							   {
									// Step 2.B: Creating JDBC Statement 
									statement = connect.createStatement();
									statement.executeUpdate("update faculty_master set password='"+snewPassword+"', modified_at='"+modDate+"' where email='"+sEmail+"' and user_id='"+sid+"'");
											
									x++;
									if (x > 0) 
									{
										JOptionPane.showMessageDialog(this, "Data Updated Successfully");
										setVisible(false);
									}
								}
								catch(SQLException ex) 
								{
									ex.printStackTrace();
								}finally {

									// Step 3: Closing database connect
									try {
										if(null != connect) {

											// cleanup resources, once after processing
											//resultSet.close();
											statement.close();

											// and then finally close connect
											connect.close();
										}
									}
									catch (SQLException sqlex) {
										sqlex.printStackTrace();
									}
								}
							}
						}else{
							JOptionPane.showMessageDialog(this, "Password Does Not Match", "Error",JOptionPane.ERROR_MESSAGE);
							textnewPassword1.setText("");
							textnewPassword1.requestFocusInWindow();
						} 
					}
				}else{
					JOptionPane.showMessageDialog(this, "Old Password Does Not Match", "Error",JOptionPane.ERROR_MESSAGE);
					textcurrPassword.setText("");
					textcurrPassword.requestFocusInWindow();
				} 
			}
			
			
         }else if(e.getSource() == btnCancel){
							setVisible(false);
		 }
    } 
    public static void main(String args[])
    {	
		String userEmail = new String();
		String title	 = new String();
		int xValue=0, yValue=0;
        //new ChangePassword();
		new ChangePassword(title,userEmail, xValue, yValue);
    }
}
