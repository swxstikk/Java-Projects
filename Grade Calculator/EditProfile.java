package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
 
  public class EditProfile extends JFrame implements ActionListener
  { 
    JLabel  lblName, lblEmail, lblUsername, lblAddress, lblCountry, lblState, lblPhone, lblDepartment, lblCity, lblPin;
    JTextField textName, textEmail, textUname,  textCountry, textState,  textPhone, textDepartment, textCity, textPin;
    JButton btnUpdate, btnCancel;
	JComboBox comboCountry;
	JTextArea textAddress;
	JScrollPane scrollPane;
	String sName, sEmail, sUname, sAddr, sCountry, sState, sPhone, sDept, sCity, sPin, sId;
	ArrayList<String> countryList = new ArrayList<String>();
	// Database Connectivity
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		String comboList=null;
	
	
    EditProfile(String title1, String userEmail1, int xValue1, int yValue1)
	//EditProfile()
    {
		setLayout(null);
		comboCountry 	= new JComboBox();
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
				sId 		= new String(resultSet.getString("user_id"));
				sName 		= new String(resultSet.getString("fullname"));
				sEmail 		= new String(resultSet.getString("email"));
				sUname 		= new String(resultSet.getString("username"));
				sPhone 		= new String(resultSet.getString("contact_no"));
				sDept 		= new String(resultSet.getString("department"));
				sAddr 		= new String(resultSet.getString("address"));
				sCountry 	= new String(resultSet.getString("country"));
				sState 		= new String(resultSet.getString("state"));
				sCity 		= new String(resultSet.getString("city"));
				sPin 		= new String(resultSet.getString("pin"));
			}
			resultSet1 = statement.executeQuery("SELECT * FROM country_master");
			
			while(resultSet1.next()){
				comboList= resultSet1.getString("countrynm"); 
				countryList.add(comboList);
				
			}
			for (int i = 0; i < countryList.size(); i++) {
				comboCountry.addItem(countryList.get(i)); //Populate to Combobox
				comboCountry.setSelectedItem(sCountry);
			}
			
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		
		// Label of Input Fields
        lblName 		= new JLabel("Full Name:");
        lblEmail 		= new JLabel("Email-ID:");
        lblUsername		= new JLabel("Username:");		
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
		scrollPane 		= new JScrollPane(textAddress); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Buttons
        btnUpdate 		= new JButton("Update",new ImageIcon("images/save1.gif"));
        btnCancel 		= new JButton("Cancel",new ImageIcon("images/exit2.png"));
				
		// Text Field of Labels
        textName 		= new JTextField(sName);
		textEmail 		= new JTextField(sEmail);
		textUname 		= new JTextField(sUname);
		textState 		= new JTextField(sState);
		textCity 		= new JTextField(sCity);
		textPhone 		= new JTextField(sPhone);
		textDepartment	= new JTextField(sDept);
		textPin			= new JTextField(sPin);
			
       // setting position of Labels, Buttons aand TextField
        
		lblName.setBounds(80, 20, 200, 30);
        lblEmail.setBounds(80, 60, 200, 30);
        lblUsername.setBounds(80, 100, 200, 30);
		lblPhone.setBounds(80, 140, 200, 30);
        lblDepartment.setBounds(80, 180, 200, 30);
		lblAddress.setBounds(80, 220, 200, 30);
        lblCountry.setBounds(80, 320, 200, 30);
        lblState.setBounds(80, 360, 200, 30);
        lblCity.setBounds(80, 400, 200, 30);
        lblPin.setBounds(80, 440, 200, 30);
		
		textName.setBounds(250, 20, 200, 30);
        textEmail.setBounds(250, 60, 200, 30);
        textUname.setBounds(250, 100, 200, 30);
        textPhone.setBounds(250, 140, 200, 30);
        textDepartment.setBounds(250, 180, 200, 30);
		scrollPane.setBounds(250, 220, 200, 80);
		comboCountry.setBounds(250, 320, 200, 30);
        textState.setBounds(250, 360, 200, 30);
        textCity.setBounds(250, 400, 200, 30);
        textPin.setBounds(250, 440, 200, 30);
		btnUpdate.setBounds(200, 480, 100, 30);
        btnCancel.setBounds(320, 480, 100, 30);
		
		// add components to panel
		add(lblName);
        add(textName);
		add(lblEmail);
        add(textEmail); 
		add(lblUsername);
        add(textUname);
        add(lblPhone);
        add(textPhone);
        add(lblDepartment);
        add(textDepartment);
        add(lblAddress);
		add(scrollPane);
		add(lblCountry);
		add(comboCountry);
		add(lblState);
        add(textState);
        add(lblCity);
        add(textCity);
        add(lblPin);
        add(textPin);
        add(btnUpdate);
        add(btnCancel);
		
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
        setSize(xValue1, yValue1);
        //setSize(700, 650);
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
			String s1,s2,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s16;	
			s1=null;
			s2=null;
			s8=null;
			s9=null;
			s10=null;
			s12=null;
			s16=null;
			s6 		= textAddress.getText();
			s7 		= (String)comboCountry.getSelectedItem();
			s11 	= textDepartment.getText();
			s13  	= lblPhone.getText();
			s14  	= lblPin.getText();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
			Date date = new Date();
			String modDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
			System.out.println(modDate);
			
			
			if(Validate.isEmpty(textName.getText().trim())){
				JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textName.setText("");
				textName.requestFocus();
			}else{
				s1 	= textName.getText();
			}
			if(Validate.isEmpty(textEmail.getText().trim())){
				JOptionPane.showMessageDialog(this,"Blank Email field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textEmail.setText("");
				textEmail.requestFocusInWindow();
			}else{
				if(Validate.checkEmail(textEmail.getText().trim())){
					if(sEmail.equals(textEmail.getText().trim())){
						s2 	= textEmail.getText();
					}else if(Validate.checkEmailReg(textEmail.getText().trim())){
						JOptionPane.showMessageDialog(this,"Email Id already regisetred","Error",JOptionPane.ERROR_MESSAGE);
						textEmail.setText("");
						textEmail.requestFocusInWindow();
					}
				}else{
					JOptionPane.showMessageDialog(this,"Enter a proper email id","Error",JOptionPane.ERROR_MESSAGE);
					textEmail.setText("");
					textEmail.requestFocusInWindow();
				}
			}
			if(Validate.isEmpty(textUname.getText().trim())){
				JOptionPane.showMessageDialog(this,"Blank Email field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textUname.setText("");
				textUname.requestFocusInWindow();
			}else{
				s16 	= textUname.getText();
			}			
			if(Validate.isEmpty(textState.getText().trim())){
				JOptionPane.showMessageDialog(this,"Blank field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textState.setText("");
				textState.requestFocusInWindow();
			}else{
				s8 	= textState.getText();
			}
			if(Validate.isEmpty(textCity.getText().trim())){
				JOptionPane.showMessageDialog(this,"Blank City field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textCity.setText("");
				textCity.requestFocusInWindow();
			}else{
				s9	= textCity.getText();
			}
			if(Validate.isEmpty(textPhone.getText())){
				JOptionPane.showMessageDialog(this,"Blank Phone field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textPhone.setText("");
				textPhone.requestFocusInWindow();
			}else{
				if(Validate.isNumeric(textPhone.getText()) && Validate.lenContact(textPhone.getText(),lblPhone.getText())){
					s10 	= textPhone.getText();
				}else{
					JOptionPane.showMessageDialog(this,"Enter Correct Contact No","Error",JOptionPane.ERROR_MESSAGE);
					textPhone.setText("");
					textPhone.requestFocusInWindow();
				}
			}
			if(Validate.isEmpty(textPin.getText())){
				JOptionPane.showMessageDialog(this,"Blank Pin field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textPin.setText("");
				textPin.requestFocusInWindow();
			}else{
				if(Validate.isNumeric(textPin.getText()) && Validate.lenContact(textPin.getText(),lblPin.getText())){
					s12 	= textPin.getText();
				}else{
					JOptionPane.showMessageDialog(this,"Enter Correct Pin No","Error",JOptionPane.ERROR_MESSAGE);
					textPin.setText("");
					textPin.requestFocusInWindow();
				}
			}
			if(!s1.isEmpty() && !s2.isEmpty()&& s2!=null && !s8.isEmpty() && !s9.isEmpty() && !s10.isEmpty() && !s12.isEmpty()){
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				}catch(ClassNotFoundException cnfex){
					System.out.println("Problem in loading or registering MS Access JDBC driver");
					cnfex.printStackTrace();
				}
               try
               {
				    statement = connect.createStatement();
					statement.executeUpdate("update faculty_master set username='"+s16+"',fullname='"+s1+"', email='"+s2+"', address='"+s6+"', department='"+s11+"', contact_no='"+s10+"', country='"+s7+"', state='"+s8+"', city='"+s9+"', pin='"+s12+"', modified_at='"+modDate+"' where user_id='"+sId+"' and email='"+sEmail+"'");
                   			
                    x++;
                    if (x > 0) 
                    {
                        JOptionPane.showMessageDialog(this, "Data Saved Successfully");
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
        //new EditProfile();
		new EditProfile(title,userEmail, xValue, yValue);
    }
}
