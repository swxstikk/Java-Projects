package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class SearchStudent extends JFrame implements ActionListener {
	JLabel studentName, className;
	JComboBox cbClass = new JComboBox();
	JComboBox cbStudent = new JComboBox();
	JButton btnSubmit, btnClear, btnExit;
	Statement stmt=null;
	Connection connect=null;
	ResultSet result,resultStud=null;
	String email = new String();
	ArrayList<String> classList = new ArrayList<String>(); 
	ArrayList<String> studentList = new ArrayList<String>(); 
	
	public SearchStudent(String title1, String userEmail1, int xValue1, int yValue1){
		
		super("Edit Student");
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
			className				= new JLabel("Class:", Label.RIGHT);
            studentName 			= new JLabel("Student:", Label.RIGHT);
            
            
            pnlWest.add(new Label());
            pnlWest.add(new Label());
			pnlWest.add(className);
            pnlWest.add(studentName);
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
		
		//  Center
        Panel pnlCenter = new Panel(new GridLayout(11,1,0,5));
        add(pnlCenter, BorderLayout.CENTER);
		cbClass.addItem("Select Class");
		cbStudent.addItem("Select Student");
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
		cbClass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				//Do Something
				JComboBox cbClass = (JComboBox) event.getSource();
				Object item = event.getItem();
				
				if(event.getStateChange() == ItemEvent.SELECTED) {
				  // Item was just selected
					String selectedClass = (String)cbClass.getSelectedItem();
					String Query="SELECT * FROM user_master where class_assigned='"+selectedClass+"'";
				  //dropdown subject
					String listStudent=null;
					studentList.clear();
					cbStudent.removeAllItems();
					try {
						stmt= connect.createStatement();
						System.out.println(Query);
						resultStud = stmt.executeQuery(Query);
						// processing returned data and printing into console
						while(resultStud.next()){
							listStudent= resultStud.getString("rollno"); 
							studentList.add(listStudent); // Day List Array
						}
						for (int i = 0; i < studentList.size(); i++) {							
							cbStudent.addItem(studentList.get(i)); //Populate to Combobox
						}
						
					} catch(SQLException e) {
						e.printStackTrace();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}else if(event.getStateChange() == ItemEvent.DESELECTED) {
				  // Item is no longer selected
				  //subjList.clear();
				  //cbSubj.removeAllItems();
				}
				
			}
		});
		
		pnlCenter.add(new Label());
		pnlCenter.add(new Label());
		pnlCenter.add(cbClass);
		pnlCenter.add(cbStudent);
		pnlCenter.add(new Label());
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
                btnSubmit 		= new JButton("Edit");
                btnClear	 	= new JButton("Clear");
                btnExit 		= new JButton("Exit");
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
        if(command.equals("Edit")) 			{this.doSave();
		} else if(command.equals("Clear"))  {this.doClear();
        } else if(command.equals("Exit"))   {this.doExit();
        }
	}
	private void doSave() {
		String s1,s2;
		s1	= (String)cbClass.getSelectedItem();
		s2	= (String)cbStudent.getSelectedItem();
        new EditStudent(s1,s2,700,650);
	    this.dispose();
    }
	private void doClear() {
       
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
	//Main Method
	public static void main(String args[]){
		String userEmail = new String();
		String title	 = new String();
		int xValue=0, yValue=0;
		//new AddSubject();
		new SearchStudent(title,userEmail, xValue, yValue);

	}
}
