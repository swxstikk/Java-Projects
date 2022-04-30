package ss;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
 
  public class RegisterUser extends JFrame implements ActionListener
  { 
    JLabel title, lblName, lblEmail, lblPassword, lblCnfPassword, lblCountry, lblState, lblPhone, lblGender, lblAddress,
	lblDepartment, lblCity, lblPin;
    JTextField textName, textEmail,textState, textPhone, textDepartment, textCity, textPin;
    JButton btnSubmit, btnReset;
	JComboBox comboCountry;
	JRadioButton Male, Female, Other;
	JTextArea textAddress;
	JScrollPane scrollPane;
	ButtonGroup bg;
    JPasswordField textPassword, textCnfPassword;
	ArrayList<String> countryList = new ArrayList<String>();
	// Database Connectivity
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String comboList=null;
		

 
    RegisterUser(String inputEmail)
	//RegisterUser()
    {
        setLayout(null);
		comboCountry 	= new JComboBox();
		// Step 1: Opening database connect
		Config db = new Config();
		connect = db.getConnection();
		
		try {

			// Step 2.B: Creating JDBC Statement 
			statement = connect.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM country_master");

			// processing returned data and printing into console
			while(resultSet.next()){
				comboList= resultSet.getString("countrynm"); 
				countryList.add(comboList);
				
			}
			for (int i = 0; i < countryList.size(); i++) {
				comboCountry.addItem(countryList.get(i)); //Populate to Combobox
			}
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		
		// Label of Input Fields
        lblName 		= new JLabel("Full Name:");
        lblEmail 		= new JLabel("Email-ID:");
        lblPassword 	= new JLabel("Create Password:");
        lblCnfPassword 	= new JLabel("Confirm Password:");
		lblGender		= new JLabel("Gender");
        lblAddress		= new JLabel("Address");	
        lblCountry 		= new JLabel("Country:");
        lblState 		= new JLabel("State:");
		lblCity			= new JLabel("City");
        lblPhone 		= new JLabel("Phone No:");
		lblDepartment	= new JLabel("Department");
		lblPin			= new JLabel("Pin");
		//Radio Buttons
		Male			= new JRadioButton("Male");
		Male.setActionCommand("male");
		Female			= new JRadioButton("Female");
		Female.setActionCommand("female");
		Other			= new JRadioButton("Other");
		Other.setActionCommand("other");
		bg				= new ButtonGroup();
		bg.add(Male);bg.add(Female);bg.add(Other);
		// Text Area
		textAddress 	= new JTextArea();
		textAddress.setLineWrap(true);
		scrollPane 		= new JScrollPane(textAddress); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Text Field of Labels
        textName 		= new JTextField();
        textEmail 		= new JTextField();
		textEmail.setText(inputEmail);
		textEmail.setEditable(false); 
        textPassword	= new JPasswordField();
        textCnfPassword = new JPasswordField();
        textState 		= new JTextField();
        textCity 		= new JTextField();
        textPhone 		= new JTextField();
		textDepartment	= new JTextField();
		textPin			= new JTextField();
		
		//Buttons
         btnSubmit 		= new JButton("Submit",new ImageIcon("images/save2.gif"));
		btnReset	 	= new JButton("Clear",new ImageIcon("images/delete.png"));
       // setting position of Labels, Buttons aand TextField
        lblName.setBounds(80, 20, 200, 30);
        lblEmail.setBounds(80, 60, 200, 30);
        lblPassword.setBounds(80, 140, 200, 30);
        lblCnfPassword.setBounds(80, 180, 200, 30);
		lblGender.setBounds(80, 220, 200, 30);
		lblAddress.setBounds(80, 260, 200, 30);
        lblCountry.setBounds(80, 360, 200, 30);
        lblState.setBounds(80, 400, 200, 30);
        lblCity.setBounds(80, 440, 200, 30);
        lblPhone.setBounds(80, 480, 200, 30);
		lblDepartment.setBounds(80, 520, 200, 30);
		lblPin.setBounds(80, 560, 200, 30);
		
        textName.setBounds(300, 20, 200, 30);
        textEmail.setBounds(300, 60, 200, 30);
        textPassword.setBounds(300, 140, 200, 30);
        textCnfPassword.setBounds(300, 180, 200, 30);
		Male.setBounds(300, 220, 70, 30);
		Female.setBounds(400, 220, 70, 30);
		Other.setBounds(500, 220, 70, 30);
		scrollPane.setBounds(300, 260, 200, 80);
        comboCountry.setBounds(300, 360, 200, 30);
        textState.setBounds(300, 400, 200, 30);
        textCity.setBounds(300, 440, 200, 30);
        textPhone.setBounds(300, 480, 200, 30);
        textDepartment.setBounds(300, 520, 200, 30);
        textPin.setBounds(300, 560, 200, 30);
        btnSubmit.setBounds(150, 600, 100, 30);
        btnReset.setBounds(270, 600, 100, 30);
		// Adding all components to container
        //add(title);
        add(lblName);
        add(textName);
        add(lblEmail);
        add(textEmail); 
        add(lblPassword);
        add(textPassword);
        add(lblCnfPassword);
        add(textCnfPassword);
		add(lblGender);
		add(Male);
        add(Female);
        add(Other);
		add(lblAddress);
		add(scrollPane);
        add(lblCountry);
        add(comboCountry);
        add(lblState);
        add(textState);
        add(lblCity);
        add(textCity);
        add(lblPhone);
        add(textPhone);
        add(lblDepartment);
        add(textDepartment);
        add(lblPin);
        add(textPin);
        add(btnSubmit);
        add(btnReset);
		
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
		
        setSize(700, 700);
        setVisible(true);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration Form");
		
		//Action to Buttons
        btnSubmit.addActionListener(this);
        btnReset.addActionListener(this);
		
    }
	
    public void actionPerformed(ActionEvent e) 
    {
         if(e.getSource() == btnSubmit)
         {
            int x = 0;
			String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16;	
			s1=null;
			s2=null;
			s3=null;
			s4=null;
			s8=null;
			s9=null;
			s10=null;
			s12=null;
			s15=null;
			s16=null;
			s3 	= new String(textPassword.getPassword());
			s4	= new String(textCnfPassword.getPassword());
			s5 	= bg.getSelection().getActionCommand();
			s6 	= textAddress.getText();
			s7 	= (String)comboCountry.getSelectedItem();
			s11 	= textDepartment.getText();
			s13  = lblPhone.getText();
			s14  = lblPin.getText();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
			Date date = new Date();
			String newDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
			System.out.println(newDate);
			
			
			if(Validate.isEmpty(textName.getText())){
				JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textName.setText("");
				textName.requestFocus();
			}else{
				s1 	= textName.getText();
			}
			if(Validate.isEmpty(textEmail.getText())){
				JOptionPane.showMessageDialog(this,"Blank Email field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textEmail.setText("");
				textEmail.requestFocusInWindow();
			}else{
				s2 	= textEmail.getText();
			}
			if(Validate.isEmpty(s3)){
				JOptionPane.showMessageDialog(this,"Blank Password field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textPassword.setText("");
				textPassword.requestFocusInWindow();
			}
			if(Validate.isEmpty(s4)){
				JOptionPane.showMessageDialog(this,"Blank Confirm Password field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textCnfPassword.setText("");
				textCnfPassword.requestFocusInWindow();
			}else{
				if(s3.equals(s4))
				{
					//JOptionPane.showMessageDialog(this, "Password and Confirm Password Matched", "Information",JOptionPane.ERROR_MESSAGE);
					s15 = s3;
				}else{
					JOptionPane.showMessageDialog(this, "Password Does Not Match", "Error",JOptionPane.ERROR_MESSAGE);
					textCnfPassword.setText("");
					textCnfPassword.requestFocusInWindow();
				} 
				
			}
			if(Validate.isEmpty(textState.getText())){
				JOptionPane.showMessageDialog(this,"Blank field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				textState.setText("");
				textState.requestFocusInWindow();
			}else{
				s8 	= textState.getText();
			}
			if(Validate.isEmpty(textCity.getText())){
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
					//JOptionPane.showMessageDialog(this,"Contact is Oke " + textPhone.getText(),"Login",JOptionPane.INFORMATION_MESSAGE);
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
					//JOptionPane.showMessageDialog(this,"Pin is Integer " + textPin.getText(),"Login",JOptionPane.INFORMATION_MESSAGE);
					s12 	= textPin.getText();
				}else{
					JOptionPane.showMessageDialog(this,"Enter Correct Pin No","Error",JOptionPane.ERROR_MESSAGE);
					textPin.setText("");
					textPin.requestFocusInWindow();
				}
			}
			if(!s1.isEmpty() && !s2.isEmpty() && !s15.isEmpty()  && !s8.isEmpty() && !s9.isEmpty() && !s10.isEmpty() && !s12.isEmpty()){
				
				
               try
               {
				    // Step 2.B: Creating JDBC Statement 
					statement = connect.createStatement();
					statement.executeUpdate("INSERT into faculty_master(username,password,fullname,gender,email,address,department,contact_no,country,state,city,pin,created_at) VALUES('"+s16+"','"+s15+"','"+s1+"','"+s5+"','"+s2+"','"+s6+"','"+s11+"','"+s10+"','"+s7+"','"+s8+"','"+s9+"','"+s12+"','"+newDate+"')");
                   			
                    x++;
                    if (x > 0) 
                    {
                        JOptionPane.showMessageDialog(this, "Data Saved Successfully");
						new Login();
						this.dispose();
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
            textName.setText("");
            textEmail.setText("");
            textPassword.setText("");
            textCnfPassword.setText("");
            textState.setText("");
            textPhone.setText("");
			textDepartment.setText("");
            textCity.setText("");
        }
    } 
    public static void main(String args[])
    {	
		String sEmail = new String();
        new RegisterUser(sEmail);
		//new RegisterUser();
    }
}