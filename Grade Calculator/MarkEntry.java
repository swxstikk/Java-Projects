package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
import java.lang.Math;

public class MarkEntry extends JFrame implements ActionListener {
	JLabel  className, subjectName, studyLevel, studentName,Mark1,Mark2,Mark3,Mark4,Mark5,Mark6;
	JTextField textMark1, textMark2, textMark3, textMark4,textMark5,textMark6;
	JComboBox cbClass = new JComboBox();
	JComboBox cbStudent = new JComboBox();
	JComboBox cbSubject = new JComboBox();
	JComboBox cbLevel = new JComboBox();
	JComboBox cbBehave = new JComboBox();
	JButton btnSubmit, btnClear, btnExit;
	Statement stmt=null;
	Connection connect=null;
	ResultSet result,resultStud, resultSubject=null;
	String email = new String();
	ArrayList<String> classList = new ArrayList<String>(); 
	ArrayList<String> studentList = new ArrayList<String>(); 
	ArrayList<String> subjectList = new ArrayList<String>(); 
	ArrayList<String> levelList = new ArrayList<String>(); 
	ArrayList<String> behaveList = new ArrayList<String>(); 
	
	public MarkEntry(){
		super("Mark Entry");
		setLayout(new BorderLayout());
		String listClass=null;
		
		Config db = new Config();
		connect = db.getConnection();
		
		// North
		Panel pnlNorth = new Panel(new GridLayout(3,4,5,5));
		add(pnlNorth, BorderLayout.NORTH);
			className				= new JLabel("Class:", Label.RIGHT);
            studentName 			= new JLabel("Student:", Label.RIGHT);
            subjectName 			= new JLabel("Subject:", Label.RIGHT);
            studyLevel	 			= new JLabel("Level:", Label.RIGHT);
			
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
			
			
			pnlNorth.add(studyLevel);
			pnlNorth.add(className);
            pnlNorth.add(studentName);
            pnlNorth.add(subjectName);
            pnlNorth.add(cbLevel);
            pnlNorth.add(cbClass);
            pnlNorth.add(cbStudent);
            pnlNorth.add(cbSubject);
            pnlNorth.add(new Label());
			pnlNorth.add(new Label());
            pnlNorth.add(new Label());
            pnlNorth.add(new Label());
		
		//  Center
		Panel pnlCenter = new Panel(new GridLayout(4,1));
        add(pnlCenter, BorderLayout.CENTER);
            Panel pnlLabel = new Panel(new GridLayout(1,6,5,0));
            pnlCenter.add(pnlLabel);
				Mark1				= new JLabel("Assessment-1:", Label.RIGHT);
				Mark2 				= new JLabel("Assessment-2:", Label.RIGHT);
				Mark3 				= new JLabel("Paper-1:", Label.RIGHT);
				Mark4	 			= new JLabel("Paper-2:", Label.RIGHT);
				Mark5	 			= new JLabel("Paper-3:", Label.RIGHT);
				Mark6	 			= new JLabel("Behaviour:", Label.RIGHT);
				
				pnlLabel.add(Mark6); 
				pnlLabel.add(Mark1);
				pnlLabel.add(Mark2);
				pnlLabel.add(Mark3);
				pnlLabel.add(Mark4);
				pnlLabel.add(Mark5);
				
			Panel pnlText = new Panel(new GridLayout(1,6,5,0));
            pnlCenter.add(pnlText);
				//JTextField
				textMark1 			= new JTextField();
				textMark2 			= new JTextField();
				textMark3			= new JTextField();
				textMark4 			= new JTextField();
				textMark5 			= new JTextField();
				cbBehave.addItem("Mark For Behaviour");
				cbBehave.addItem("1");
				cbBehave.addItem("2");
				cbBehave.addItem("3");
				cbBehave.addItem("4");
				cbBehave.addItem("5");
			
				pnlText.add(cbBehave);
				pnlText.add(textMark1);
				pnlText.add(textMark2);
				pnlText.add(textMark3);
				pnlText.add(textMark4);
				pnlText.add(textMark5);
				
				 pnlCenter.add(new Label());
				 pnlCenter.add(new Label());
		//  South
        Panel pnlSouth = new Panel(new GridLayout(3,1));
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
            
		setBounds(200,100,1000,600);
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
		String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
		int x=0,s14=0,s15=0,s16=0,s11,s12,s13;
		s1	= (String)cbLevel.getSelectedItem(); 
		s2	= (String)cbClass.getSelectedItem();
		s3	= (String)cbStudent.getSelectedItem(); 
		s4	= (String)cbSubject.getSelectedItem();
		s5	= (String)cbBehave.getSelectedItem();
		s6=null;
		s7=null;
		s8=null;
		s9=null;
		s10=null;
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		Date date = new Date();
		String createDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
		System.out.println(createDate);	
		
		if(Validate.isEmpty(textMark1.getText())){
			JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			textMark1.setText("");
			textMark1.requestFocus();
		}else{
			if(Validate.isNumeric(textMark1.getText())){
				s6  = textMark1.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				textMark1.setText("");
				textMark1.requestFocusInWindow();
			}
		}
		if(Validate.isEmpty(textMark2.getText())){
			JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			textMark2.setText("");
			textMark2.requestFocus();
		}else{
			if(Validate.isNumeric(textMark2.getText())){
				s7  = textMark2.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				textMark2.setText("");
				textMark2.requestFocusInWindow();
			}
		}
		if(Validate.isEmpty(textMark3.getText())){
			JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			textMark3.setText("");
			textMark3.requestFocus();
		}else{
			if(Validate.isNumeric(textMark3.getText())){
				s8  = textMark3.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				textMark3.setText("");
				textMark3.requestFocusInWindow();
			}
		}
		if(Validate.isEmpty(textMark4.getText())){
			JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			textMark4.setText("");
			textMark4.requestFocus();
		}else{
			if(Validate.isNumeric(textMark4.getText())){
				s9  = textMark4.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				textMark4.setText("");
				textMark4.requestFocusInWindow();
			}
		}
		if(Validate.isEmpty(textMark5.getText())){
			JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			textMark5.setText("");
			textMark5.requestFocus();
		}else{
			if(Validate.isNumeric(textMark5.getText())){
				s10  = textMark5.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				textMark5.setText("");
				textMark5.requestFocusInWindow();
			}
		}
		
		if(!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty()  && !s4.isEmpty() && !s5.isEmpty() && !s6.isEmpty() && !s7.isEmpty() && !s8.isEmpty() && !s9.isEmpty() && !s10.isEmpty()){
			s15=Integer.parseInt(s6)+Integer.parseInt(s7);
			s16=Integer.parseInt(s8)+Integer.parseInt(s9)+Integer.parseInt(s10);
			s11 = Math.round((20*(s15))/100); // final_sess Mark
			s12 = Math.round((80*(s16)/3)/100); // final_theory Mark
			s13 = s11+s12; // final percentage
			
			try{
				String chkEntry = "select * from mark_entry where class_name='"+s2+"' and subject='"+s4+"' and level='"+s1+"' and rollno='"+s3+"'";
				System.out.println(chkEntry);
				ResultSet rsltChkEntry = stmt.executeQuery(chkEntry);
				if(rsltChkEntry.next()){
					JOptionPane.showMessageDialog(this,"Data Already found. Kindly check before entering again","Error",JOptionPane.INFORMATION_MESSAGE);
				}else{
					String getGrade = "SELECT * FROM grade_details WHERE CLASS='"+s2+"' and SUBJECT='"+s4+"' and LEVEL='"+s1+"' AND start_range<='"+s13+"' AND end_range>='"+s13+"';";
					System.out.println(getGrade);
					ResultSet rsltgetGrade = stmt.executeQuery(getGrade);
					if(rsltgetGrade.next()){
						s14 = Integer.parseInt(rsltgetGrade.getString("GRADE")); 
						try{
							// Step 2.B: Creating JDBC Statement 
							stmt = connect.createStatement();
							stmt.executeUpdate("INSERT into mark_entry(class_name,subject,level,rollno,mark1,mark2,mark3,mark4,mark5,mark6,final_sess,final_theory,final_perc,final_grade,created_at) VALUES ('"+s2+"','"+s4+"','"+s1+"','"+s3+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s5+"','"+s11+"','"+s12+"','"+s13+"','"+s14+"','"+createDate+"')");
									
							x++;
							if (x > 0) 
							{
								JOptionPane.showMessageDialog(this, "Student Final Grade is:- "+s14,"Success",JOptionPane.INFORMATION_MESSAGE);
								new Dashboard(email);
								this.dispose();
							}
						}
						catch(SQLException ex) 
						{
							ex.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(this,"Grade Not set for current combination","Error",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();;
			} catch(Exception e) {
				e.printStackTrace();;
			}
			
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
		new MarkEntry();

	}
}
