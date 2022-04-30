package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class AddGrade extends JFrame implements ActionListener {
	JLabel  className, subjectName, studyLevel,lblgradeName,lblstartRange,lblendRange,lblGrade;
	JTextField gradeName, startRange, endRange, Grade;
	JComboBox cbClass = new JComboBox();
	JComboBox cbSubject = new JComboBox();
	JComboBox cbLevel = new JComboBox();
	JButton btnSubmit, btnClear, btnExit;
	Statement stmt=null;
	Connection connect=null;
	ResultSet result,resultStud, resultSubject=null;
	String email = new String();
	ArrayList<String> classList = new ArrayList<String>(); 
	ArrayList<String> subjectList = new ArrayList<String>(); 
	ArrayList<String> levelList = new ArrayList<String>(); 
	
	public AddGrade(String title1, String userEmail1, int xValue1, int yValue1){
		
		super("Add Grade");
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
            lblgradeName	 		= new JLabel("Grade Id:", Label.RIGHT);
            lblstartRange	 		= new JLabel("Start:", Label.RIGHT);
            lblendRange	 			= new JLabel("End:", Label.RIGHT);
            lblGrade		 		= new JLabel("Grade:", Label.RIGHT);
            
            pnlWest.add(new Label());
            pnlWest.add(className);
            pnlWest.add(subjectName);
            pnlWest.add(studyLevel);
			pnlWest.add(new Label());
            pnlWest.add(lblgradeName);
            pnlWest.add(lblstartRange);
            pnlWest.add(lblendRange);
            pnlWest.add(lblGrade);
            pnlWest.add(new Label());
            pnlWest.add(new Label());
			
		
		//  Center
        Panel pnlCenter = new Panel(new GridLayout(11,1,0,5));
        add(pnlCenter, BorderLayout.CENTER);
		cbClass.addItem("Select Class");
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
		// Text Field of Labels 
		gradeName 		= new JTextField();
		startRange 		= new JTextField();
		endRange 		= new JTextField();
		Grade 			= new JTextField();
		
		pnlCenter.add(new Label());
		pnlCenter.add(cbClass);
		pnlCenter.add(cbSubject);
		pnlCenter.add(cbLevel);
		pnlCenter.add(new Label());
		pnlCenter.add(gradeName);
		pnlCenter.add(startRange);
		pnlCenter.add(endRange);
		pnlCenter.add(Grade);
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
		setBounds(200,100,xValue1,yValue1);
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
		String s1,s2,s3,s4,s5,s6,s7;
		int x=0;
		s2=null;
		s5=null;
		s6=null;
		s7=null;
		s1	= (String)cbClass.getSelectedItem();
		s3	= (String)cbSubject.getSelectedItem();
		s4	= (String)cbLevel.getSelectedItem(); 		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		Date date = new Date();
		String createDate = new String(dateFormat.format(date)); //2016/11/16 12:08:43
		System.out.println(createDate);	
		
		if(Validate.isEmpty(gradeName.getText())){
			JOptionPane.showMessageDialog(this,"Blank Name field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			gradeName.setText("");
			gradeName.requestFocus();
		}else{
			s2  = gradeName.getText();
		}
		if(Validate.isEmpty(startRange.getText())){
			JOptionPane.showMessageDialog(this,"Blank Start Range field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			startRange.setText("");
			startRange.requestFocusInWindow();
		}else{
			if(Validate.isNumeric(startRange.getText())){
				s5  = startRange.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				startRange.setText("");
				startRange.requestFocusInWindow();
			}
		}
		if(Validate.isEmpty(endRange.getText())){
			JOptionPane.showMessageDialog(this,"Blank End Range field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			endRange.setText("");
			endRange.requestFocusInWindow();
		}else{
			if(Validate.isNumeric(startRange.getText())){
				s6  = endRange.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				endRange.setText("");
				endRange.requestFocusInWindow();
			}
		}
		if(Validate.isEmpty(Grade.getText())){
			JOptionPane.showMessageDialog(this,"Blank Grade field is not allowed","Error",JOptionPane.ERROR_MESSAGE);
			Grade.setText("");
			Grade.requestFocusInWindow();
		}else{
			if(Validate.isNumeric(startRange.getText())){
				s7  = Grade.getText();
			}else{
				JOptionPane.showMessageDialog(this,"Enter Only Integer","Error",JOptionPane.ERROR_MESSAGE);
				Grade.setText("");
				Grade.requestFocusInWindow();
			}
		}
		if(!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty()  && !s4.isEmpty() && !s5.isEmpty() && !s6.isEmpty() && !s7.isEmpty()){
		
			try {
				String chkEntry="Select * from grade_details where CLASS= '"+s1+"' and SUBJECT='"+s3+"' and LEVEL='"+s4+"' and GRADE_ID='"+s2+"'";
				System.out.println(chkEntry);
				ResultSet rsltChkEntry = stmt.executeQuery(chkEntry);
				if(rsltChkEntry.next()){
					JOptionPane.showMessageDialog(this,"Data Already found. Kindly check before entering again","Error",JOptionPane.INFORMATION_MESSAGE);
				}else{
					try
					{
						// Step 2.B: Creating JDBC Statement 
						stmt = connect.createStatement();
						stmt.executeUpdate("INSERT into grade_details(CLASS,SUBJECT,LEVEL,GRADE_ID,START_RANGE,END_RANGE,GRADE,created_at) VALUES('"+s1+"','"+s3+"','"+s4+"','"+s2+"','"+s5+"','"+s6+"','"+s7+"','"+createDate+"')");
								
						x++;
						if (x > 0) 
						{
							JOptionPane.showMessageDialog(this, "Data Saved Successfully");
							new Dashboard(email);
							this.dispose();
						}
					}
					catch(SQLException ex) 
					{
						ex.printStackTrace();
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
        gradeName.setText("");
        startRange.setText("");
        endRange.setText("");
        Grade.setText("");
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
		new AddGrade(title,userEmail, xValue, yValue);

	}
}
