package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class AddClass extends JFrame implements ActionListener {
	JLabel className, classDescription, section, subSection;
	JTextField txtName, txtDescription, txtSubSection;
	JComboBox cbSection;
	JButton btnSubmit, btnClear, btnExit;
	Statement stmt=null;
	Connection connect=null;
	String email = new String();
	String sectionlist[]={"A","B","C","D","E"};  
	
	public AddClass(String title1, String userEmail1, int xValue1, int yValue1){
	//public AddClass(){
		super("Add Class Detail");
		setLayout(new BorderLayout());
		email = userEmail1;
		
		 //  North
        Label lblCaption = new Label(" ", Label.CENTER);
        add(lblCaption, BorderLayout.NORTH);

        //  West
        Panel pnlWest = new Panel(new GridLayout(11,1,0,5));
        add(pnlWest, BorderLayout.WEST);
            className 			= new JLabel("Class Name:", Label.RIGHT);
            classDescription   	= new JLabel("Class Description:", Label.RIGHT);
            section				= new JLabel("Section:", Label.RIGHT);
            subSection		    = new JLabel("Sub-Section:", Label.RIGHT);

            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(section);
            pnlWest.add(className);
            pnlWest.add(classDescription);
            pnlWest.add(subSection);
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
		
		//  Center
        Panel pnlCenter = new Panel(new GridLayout(11,1,0,5));
        add(pnlCenter, BorderLayout.CENTER);
            txtName    			= new JTextField();
            txtDescription      = new JTextField();
            cbSection			= new JComboBox(sectionlist);
            txtSubSection       = new JTextField("-");

            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
            pnlCenter.add(cbSection);
            pnlCenter.add(txtName);
            pnlCenter.add(txtDescription);
            pnlCenter.add(txtSubSection);
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
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
		setBounds(300,250,xValue1,yValue1);
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
		String s3 = (String)cbSection.getSelectedItem();
		String s4 = txtSubSection.getText();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		Date date = new Date();
		String createDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
		System.out.println(createDate);		
		
       
		
        try {
			Config db = new Config();
			connect = db.getConnection();
			stmt= connect.createStatement();
			String chkEntry="Select * from class_master where class_name='"+s1+"' and email='"+email+"'";
			System.out.println(chkEntry);
			ResultSet rsltChkEntry = stmt.executeQuery(chkEntry);
			if(rsltChkEntry.next()){
				JOptionPane.showMessageDialog(this,"Data already found. Kindly check before entering again","Error",JOptionPane.INFORMATION_MESSAGE);
			}else{
				
				String insertSql = "INSERT INTO class_master(class_name,class_desc,section,subsection,email,created_at) VALUES('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+email+"','"+createDate+"')";
				//System.out.println(insertSql);
				stmt.executeUpdate(insertSql);
				JOptionPane.showMessageDialog(this,"Data Inserted Successfully","Information",JOptionPane.INFORMATION_MESSAGE);
            //this.doClear();
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
        txtSubSection.setText("");
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
		//new AddClass();
		new AddClass(title,userEmail, xValue, yValue);

	}
}

