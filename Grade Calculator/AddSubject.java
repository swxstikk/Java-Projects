package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class AddSubject extends JFrame implements ActionListener {
	JLabel subjectName, subjectDescription, className;
	JTextField txtName, txtDescription;
	JComboBox cbClass = new JComboBox();
	JButton btnSubmit, btnClear, btnExit;
	Statement stmt=null;
	Connection connect=null;
	ResultSet result=null;
	String email = new String();
	ArrayList<String> classList = new ArrayList<String>(); 
	
	public AddSubject(String title1, String userEmail1, int xValue1, int yValue1){
	//public AddSubject(){
		super("Add Subject Detail");
		setLayout(new BorderLayout());
		email = userEmail1;
		String listClass=null;
		Config db = new Config();
		connect = db.getConnection();
		
		 //  North
        Label lblCaption = new Label(" ", Label.CENTER);
        add(lblCaption, BorderLayout.NORTH);

        //  West
        Panel pnlWest = new Panel(new GridLayout(11,1,0,5));
        add(pnlWest, BorderLayout.WEST);
			className				= new JLabel("Class Name:", Label.RIGHT);
            subjectName 			= new JLabel("Subject Name:", Label.RIGHT);
            subjectDescription   	= new JLabel("Subject Description:", Label.RIGHT);
            
            
            pnlWest.add(new Label());
            pnlWest.add(new Label());
			pnlWest.add(className);
            pnlWest.add(subjectName);
            pnlWest.add(subjectDescription);
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
		
		//  Center
        Panel pnlCenter = new Panel(new GridLayout(11,1,0,5));
        add(pnlCenter, BorderLayout.CENTER);
			try {
			
				stmt= connect.createStatement();
				result = stmt.executeQuery("SELECT * FROM class_master");

				// processing returned data and printing into console
				while(result.next()){
					listClass= result.getString("class_name"); 
					classList.add(listClass);
				
			}
			for (int i = 0; i < classList.size(); i++) {
				cbClass.addItem(classList.get(i)); //Populate to Combobox
			}
			
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
            txtName    			= new JTextField();
            txtDescription      = new JTextField();
           
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
            pnlCenter.add(cbClass);
            pnlCenter.add(txtName);
            pnlCenter.add(txtDescription);
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
		
		
        //  East
        Label lblEast = new Label("     ");
        add(lblEast, BorderLayout.EAST);

        //  South
        Panel pnlSouth = new Panel(new GridLayout(2,1));
        add(pnlSouth, BorderLayout.SOUTH);
            Panel pnlButton = new Panel(new GridLayout(1,3,5,0));
            pnlSouth.add(pnlButton);
                btnSubmit 		= new JButton("Save",new ImageIcon("images/save2.gif"));
                btnClear	 	= new JButton("Clear",new ImageIcon("images/delete.png"));
                btnExit 		= new JButton("Exit",new ImageIcon("images/exit2.png"));
                pnlButton.add(btnSubmit);
                pnlButton.add(btnClear);
                pnlButton.add(btnExit);

            this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {dispose();}
			});
		setBounds(300,250,xValue1,yValue1);
		//setBounds(300,250,500,350);
		setVisible(true);
		//Action to Buttons
		btnSubmit.addActionListener(this);
		btnClear.addActionListener(this);
		btnExit.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
        if(command.equals("Save")) 			{this.doSave();
		} else if(command.equals("Clear"))  {this.doClear();
        } else if(command.equals("Exit"))   {this.doExit();
        }
	}
	
	 public void doSave() {
		String s1= txtName.getText();
		String s2 = txtDescription.getText();
		String s3 = (String)cbClass.getSelectedItem();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		Date date = new Date();
		String createDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
		System.out.println(createDate);		
		
       
		
        try {
			String chkEntry="Select * from subject_master where subj_name like '"+s1+"%' and class_name='"+s3+"'";
			System.out.println(chkEntry);
			ResultSet rsltChkEntry = stmt.executeQuery(chkEntry);
			if(rsltChkEntry.next()){
				JOptionPane.showMessageDialog(this,"Another Data found. Kindly check before entering again","Error",JOptionPane.INFORMATION_MESSAGE);
			}else{
				String insertSql = "INSERT INTO subject_master(subj_name,subj_desc,class_name,email,created_at) VALUES('"+s1+"','"+s2+"','"+s3+"','"+email+"','"+createDate+"')";
				stmt.executeUpdate(insertSql);
				JOptionPane.showMessageDialog(this,"Data Inserted Successfully","Information",JOptionPane.INFORMATION_MESSAGE);
				int res = JOptionPane.showConfirmDialog(null, "Do you want to add another subject?", "", JOptionPane.YES_NO_OPTION);
				switch (res) {
					case JOptionPane.YES_OPTION:
					this.doExit();
					new AddSubject("Add Subject",email, 500,350);
					break;
					case JOptionPane.NO_OPTION:
					setVisible(false);
					break;
				}
            }
        } catch(SQLException e) {
            e.printStackTrace();;
        } catch(Exception e) {
            e.printStackTrace();;
        }
		
    }
	
	private void doClear() {
        txtName.setText("");
        txtDescription.setText("");
        txtName.requestFocus();
    }
	private void doExit() {
        try {
        	stmt.close(); connect.close();
        } catch(SQLException e) {
        } catch(Exception e) {
        }
        //System.exit(0);
		this.setVisible(false);
    }
	public static void main(String args[]){
		String userEmail = new String();
		String title	 = new String();
		int xValue=0, yValue=0;
		//new AddSubject();
		new AddSubject(title,userEmail, xValue, yValue);

	}
}
