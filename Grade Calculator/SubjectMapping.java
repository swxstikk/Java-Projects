package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class SubjectMapping extends JFrame implements ActionListener {
	JLabel studentName, className, subjectName, studyLevel;
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
	
	public SubjectMapping(String title1, String userEmail1, int xValue1, int yValue1){
		
		super("Subject Mapping");
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
            subjectName 			= new JLabel("Subject:", Label.RIGHT);
            studyLevel	 			= new JLabel("Level:", Label.RIGHT);
            
            pnlWest.add(new Label());
            pnlWest.add(new Label());
            pnlWest.add(className);
            pnlWest.add(new Label());
			pnlWest.add(studentName);
            pnlWest.add(new Label());
            pnlWest.add(subjectName);
            pnlWest.add(new Label());
            pnlWest.add(studyLevel);
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
					//dropdown subject
					String Query1="SELECT * FROM subject_master where class_name='"+selectedClass+"'";
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
				}else if(event.getStateChange() == ItemEvent.DESELECTED) {
				  // Item is no longer selected
				  //subjList.clear();
				  //cbSubj.removeAllItems();
				}
				
			}
		});
		cbLevel.addItem("Select Level of Study");
		cbLevel.addItem("HL");
		cbLevel.addItem("SL");
		
		pnlCenter.add(new Label());
		pnlCenter.add(new Label());
		pnlCenter.add(cbClass);
		pnlCenter.add(new Label());
		pnlCenter.add(cbStudent);
		pnlCenter.add(new Label());
		pnlCenter.add(cbSubject);
		pnlCenter.add(new Label());
		pnlCenter.add(cbLevel);
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
	private void doSave() {
		String s1,s2,s3,s4;
		int x=0;
		s1	= (String)cbClass.getSelectedItem();
		s2	= (String)cbStudent.getSelectedItem();
		s3	= (String)cbSubject.getSelectedItem();
		s4	= (String)cbLevel.getSelectedItem();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		Date date = new Date();
		String createDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
		System.out.println(createDate);	
		
		try {
			String chkEntry="Select * from subject_mapping where subj_name= '"+s3+"' and class_name='"+s1+"' and user_name='"+s2+"' and subj_level='"+s4+"'";
			System.out.println(chkEntry);
			ResultSet rsltChkEntry = stmt.executeQuery(chkEntry);
			if(rsltChkEntry.next()){
				JOptionPane.showMessageDialog(this,"Data Already found. Kindly check before entering again","Error",JOptionPane.INFORMATION_MESSAGE);
			}else{
				String chkEntry1="Select * from subject_mapping where subj_name= '"+s3+"' and class_name='"+s1+"' and user_name='"+s2+"' and subj_level!='"+s4+"'";
				System.out.println(chkEntry1);
				ResultSet rsltChkEntry1 = stmt.executeQuery(chkEntry1);
				if(rsltChkEntry1.next()){
					int res = JOptionPane.showConfirmDialog(null, "Same Subject Mapped against this Student With different Level.Do you want to change level of study?", "", JOptionPane.YES_NO_OPTION);
					switch (res) {
						case JOptionPane.YES_OPTION:
							try {
								Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
							}catch(ClassNotFoundException cnfex){
								System.out.println("Problem in loading or registering MS Access JDBC driver");
								cnfex.printStackTrace();
							}
						   try
						   {
								stmt = connect.createStatement();
								stmt.executeUpdate("update subject_mapping set subj_level='"+s4+"' where subj_name= '"+s3+"' and class_name='"+s1+"' and user_name='"+s2+"'");
										
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
							}
						break;
						case JOptionPane.NO_OPTION:
						setVisible(false);
						break;
					}
				}else{
					String insertSql = "INSERT INTO subject_mapping(subj_name,class_name,user_name,subj_level,created_at) VALUES('"+s3+"','"+s1+"','"+s2+"','"+s4+"','"+createDate+"')";
					stmt.executeUpdate(insertSql);
					JOptionPane.showMessageDialog(this,"Data Inserted Successfully","Information",JOptionPane.INFORMATION_MESSAGE);
				}
				
            }
        } catch(SQLException e) {
            e.printStackTrace();;
        } catch(Exception e) {
            e.printStackTrace();;
        }
       
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
		new SubjectMapping(title,userEmail, xValue, yValue);

	}
}
