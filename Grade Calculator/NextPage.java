package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
  
public class NextPage extends JFrame implements ActionListener
{
	JLabel lblCheckEmail;
	JTextField txtEmail;
	JButton btnProceed;
	// Database Connectivity
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	NextPage()
	{
		 this.setIconImage((new ImageIcon("images//main_logo.jpg")).getImage());
		
		//create object of JPanel
		JPanel contentPane = new JPanel();
        //set contentPane border
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//set ContentPane with new object
		setContentPane(contentPane);
		//set contentPane layout is null
		contentPane.setLayout(null);
		
		// create text field for user
		txtEmail = new JTextField();
		//set bounds for text fields
		txtEmail.setBounds(158, 51, 200, 25);
		//in contentPane add text field
		contentPane.add(txtEmail);
		//set column for text field
		txtEmail.setColumns(100);
		
		//lable the text field
		lblCheckEmail = new JLabel("User Email");
		//set bounds for label
		lblCheckEmail.setBounds(70, 54, 86, 14);
		//add into contentPane
		contentPane.add(lblCheckEmail);
		
		//Button to Check Email
		btnProceed = new JButton("Proceed");
		//set bounds for Button
		btnProceed.setBounds(120,100,90,30);
		//add into contentPane
		contentPane.add(btnProceed);
		//add action on Button
		btnProceed.addActionListener(this);
		
		
		//setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		 this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
		//set title
		setTitle("New User Registration");
		setBounds(100, 100, 450, 300);
		setSize(450, 220);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		String valueEmail = txtEmail.getText().trim();
		String fetchEmail = new String();
		String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w\\-]+\\.)+[a-zA-Z]{2,}$"; //  ^(.+)@(.+)$
		if(command.equals("Proceed")){
			if(valueEmail!=null && !valueEmail.isEmpty()){
				Boolean validEmail = valueEmail.matches(email_pattern); // Matches the input email value with pattern defined
				if(validEmail){
					Config db = new Config();
					connection = db.getConnection();
					// Step 2: Opening database connection
					try {
						// Step 2.B: Creating JDBC Statement 
						statement = connection.createStatement();

						// Step 2.C: Executing SQL & retrieve data into ResultSet
						resultSet = statement.executeQuery("SELECT * FROM faculty_master where email='"+valueEmail+"'");

						// processing returned data and printing into console
						if(resultSet.next()){
							fetchEmail= resultSet.getString("email"); 
						}	
					}
					catch(SQLException sqlex){
						sqlex.printStackTrace();
					}finally {

						// Step 3: Closing database connection
						try {
							if(null != connection) {

								// cleanup resources, once after processing
								resultSet.close();
								statement.close();

								// and then finally close connection
								connection.close();
							}
						}
						catch (SQLException sqlex) {
							sqlex.printStackTrace();
						}
					}
					if(valueEmail.equals(fetchEmail)){ // check for already registered or not
						JOptionPane.showMessageDialog(this,
								"This email id is already registered\n Choose different or use forgot password",
								"Error",JOptionPane.ERROR_MESSAGE);
						txtEmail.requestFocus();
					}else{
						setVisible(false);
						new RegisterUser(valueEmail);	
					}
					   		
				}else{
					JOptionPane.showMessageDialog(this,
								"Enter a valid email id" + valueEmail,
								"Error",JOptionPane.ERROR_MESSAGE);
					txtEmail.requestFocus();
				}
							
			}else{
				JOptionPane.showMessageDialog(this,
								"No Email id entered ",
								"Alert",JOptionPane.ERROR_MESSAGE);
				txtEmail.requestFocus();	
			}
		}
	}
	public static void main(String args[]){
		new NextPage();
	}
}