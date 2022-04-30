package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
import java.lang.Math;

public class SearchMark extends JFrame implements ActionListener {
	JLabel  className, subjectName, studyLevel, studentName;
	JComboBox cbClass = new JComboBox();
	JComboBox cbStudent = new JComboBox();
	JComboBox cbSubject = new JComboBox();
	JComboBox cbLevel = new JComboBox();
	JButton btnSubmit, btnClear, btnExit;
	Statement stmt=null;
	Connection connect=null;
	ResultSet result,resultStud, resultSubject=null;
	String email = new String();
	ArrayList<String> classList = new ArrayList<String>(); 
	ArrayList<String> studentList = new ArrayList<String>(); 
	ArrayList<String> subjectList = new ArrayList<String>(); 
	ArrayList<String> levelList = new ArrayList<String>(); 
	
	public SearchMark(String title1, String userEmail1, int xValue1, int yValue1){
		
		super(title1);
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
            subjectName 			= new JLabel("Subject:", Label.RIGHT);
            studyLevel	 			= new JLabel("Level:", Label.RIGHT);
			studentName 			= new JLabel("Student:", Label.RIGHT);
			
			pnlWest.add(new Label());
			pnlWest.add(studyLevel);
			pnlWest.add(new Label());
            pnlWest.add(className);
            pnlWest.add(new Label());
            pnlWest.add(studentName);
            pnlWest.add(new Label());
            pnlWest.add(subjectName);
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(new Label());
			
		//  Center
        Panel pnlCenter = new Panel(new GridLayout(11,1,0,5));
        add(pnlCenter, BorderLayout.CENTER);
		
			cbClass.addItem("Select Class");
			cbStudent.addItem("Select Student");
			cbLevel.addItem("Select Level of Study");
			cbLevel.addItem("HL");
			cbLevel.addItem("SL");
			
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
						
						String selectedClass = (String)cbClass.getSelectedItem();
						String selectedLevel = (String)cbLevel.getSelectedItem();
						
						//dropdown subject
						String Query1="SELECT subj_name FROM subject_mapping where class_name='"+selectedClass+"' and subj_level='"+selectedLevel+"' order by subj_name asc";
						String listSubject=null;
						subjectList.clear();
						cbSubject.removeAllItems();
						try {
							stmt= connect.createStatement();
							System.out.println(Query1);
							resultSubject = stmt.executeQuery(Query1);
							// processing returned data and printing into console
							while(resultSubject.next()){
								listSubject= resultSubject.getString("subj_name"); 
								subjectList.add(listSubject); // Day List Array
							}
							for (int i = 0; i < subjectList.size(); i++) {							
								cbSubject.addItem(subjectList.get(i)); //Populate to Combobox
							}
							
						} catch(SQLException e) {
							e.printStackTrace();
						} catch(Exception e) {
							e.printStackTrace();
						}
						
					  // Item was just selected
						String selectedSubject = (String)cbSubject.getSelectedItem();
						String Query="SELECT user_name FROM subject_mapping where class_name='"+selectedClass+"' and subj_level='"+selectedLevel+"' and subj_name='"+selectedSubject+"' order by user_name asc";
					  //dropdown student
						String listStudent=null;
						studentList.clear();
						cbStudent.removeAllItems();
						try {
							stmt= connect.createStatement();
							System.out.println(Query);
							resultStud = stmt.executeQuery(Query);
							// processing returned data and printing into console
							while(resultStud.next()){
								listStudent= resultStud.getString("user_name"); 
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
			pnlCenter.add(cbLevel);
            pnlCenter.add(new Label());
			pnlCenter.add(cbClass);
            pnlCenter.add(new Label());
			pnlCenter.add(cbStudent);
            pnlCenter.add(new Label());
			pnlCenter.add(cbSubject);
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
            pnlCenter.add(new Label());
			
        //  South
        Panel pnlSouth = new Panel(new GridLayout(3,1));
        add(pnlSouth, BorderLayout.SOUTH);
            Panel pnlButton = new Panel(new GridLayout(1,3,5,0));
            pnlSouth.add(pnlButton);
                btnSubmit 		= new JButton("Search",new ImageIcon("images/get1.gif"));
                btnClear	 	= new JButton("Clear",new ImageIcon("images/delete.png"));
                btnExit 		= new JButton("Exit",new ImageIcon("images/exit2.png"));
                pnlButton.add(btnSubmit);
                pnlButton.add(btnClear);
                pnlButton.add(btnExit);

            this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {dispose();}
			});
		setBounds(200,100,xValue1,yValue1);
		//setBounds(300,250,500,350);
		setVisible(true);
		
		btnSubmit.addActionListener(this);
		btnClear.addActionListener(this);
		btnExit.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
        if(command.equals("Search")) 		{this.doSearch();
		} else if(command.equals("Clear"))  {this.doClear();
        } else if(command.equals("Exit"))   {this.doExit();
        }
	}
	private void doSearch() {
		String s1,s2,s3,s4;
		int x=0;
		s1	= (String)cbLevel.getSelectedItem(); 
		s2	= (String)cbClass.getSelectedItem();
		s3	= (String)cbStudent.getSelectedItem(); 
		s4	= (String)cbSubject.getSelectedItem();
		new EditMark(s1,s2,s3,s4);
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
		new SearchMark(title,userEmail, xValue, yValue);

	}
}