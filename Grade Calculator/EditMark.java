package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
import java.lang.Math;

public class EditMark extends JFrame implements ActionListener {
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
	String SMark1,SMark2,SMark3,SMark4,SMark5,SMark6;
	
	public EditMark(String level,String class_nm, String student, String subject){
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
			
			cbClass.addItem(class_nm);
			cbStudent.addItem(student);
			cbLevel.addItem(level);
			cbSubject.addItem(subject);
		// Step 2: Opening database connect
		
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
				try {
					// Step 2.B: Creating JDBC Statement 
					stmt = connect.createStatement();

					// Step 2.C: Executing SQL & retrieve data into ResultSet
					result = stmt.executeQuery("select * from mark_entry where class_name='"+class_nm+"' and subject='"+subject+"' and level='"+level+"' and rollno='"+student+"'");
					if(result.next()){
						SMark1 		= new String(result.getString("mark1"));
						SMark2 		= new String(result.getString("mark2"));
						SMark3 		= new String(result.getString("mark3"));
						SMark4		= new String(result.getString("mark4"));
						SMark5 		= new String(result.getString("mark5"));
						SMark6 		= new String(result.getString("mark6"));
						
					}
				}catch(SQLException sqlex){
					sqlex.printStackTrace();
				}
				//JTextField
				textMark1 			= new JTextField(SMark1);
				textMark2 			= new JTextField(SMark2);
				textMark3			= new JTextField(SMark3);
				textMark4 			= new JTextField(SMark4);
				textMark5 			= new JTextField(SMark5);
				
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
                btnSubmit 		= new JButton("Update",new ImageIcon("images/save2.gif"));
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
        if(command.equals("Update")) 			{this.doUpdate();
		} else if(command.equals("Clear"))  {this.doClear();
        } else if(command.equals("Exit"))   {this.doExit();
        } 
	}
	private void doUpdate() {
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
				
					String getGrade = "SELECT * FROM grade_details WHERE CLASS='"+s2+"' and SUBJECT='"+s4+"' and LEVEL='"+s1+"' AND start_range<='"+s13+"' AND end_range>='"+s13+"';";
					System.out.println(getGrade);
					ResultSet rsltgetGrade = stmt.executeQuery(getGrade);
					if(rsltgetGrade.next()){
						s14 = Integer.parseInt(rsltgetGrade.getString("GRADE")); 
						try{
							// Step 2.B: Creating JDBC Statement 
							stmt = connect.createStatement();
							stmt.executeUpdate("update mark_entry set mark1='"+s6+"',mark2='"+s7+"',mark3='"+s8+"',mark4='"+s9+"',mark5='"+s10+"',mark6='"+s5+"',final_sess='"+s11+"',final_theory='"+s12+"',final_perc='"+s13+"',final_grade='"+s14+"',updated_at='"+createDate+"'");
									
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
		String level = new String();
		String subject	 = new String();
		String class_nm	 = new String();
		String student	 = new String();
		new EditMark(level,class_nm, student, subject);

	}
}
