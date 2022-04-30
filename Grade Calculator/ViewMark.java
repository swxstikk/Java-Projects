package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
import javax.swing.table.*;

 public class ViewMark extends JFrame implements ActionListener{
	JLabel  className, subjectName, studyLevel, studentName;
	JComboBox cbClass = new JComboBox();
	JComboBox cbStudent = new JComboBox();
	JComboBox cbSubject = new JComboBox();
	JComboBox cbLevel = new JComboBox();
	Statement stmt=null;
	Connection connect=null;
	ResultSet result,resultStud, resultSubject=null;
	String email = new String();
	ArrayList<String> classList = new ArrayList<String>(); 
	ArrayList<String> studentList = new ArrayList<String>(); 
	ArrayList<String> subjectList = new ArrayList<String>(); 
	ArrayList<String> levelList = new ArrayList<String>(); 
	JTable table;
	JFrame frame1;
	String[] columnNames = {"RollNo","FA1", "FA2", "Paper-1", "Paper-2", "Paper-3","Final Perc", "Grade"};
	String s1,s2,s3,s4;
	PreparedStatement pst;
	
	
	public ViewMark(String title1, String userEmail1, int xValue1, int yValue1){
		
		super(title1);
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
			
		//center
		cbSubject.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				JComboBox cbMark = (JComboBox) ie.getSource();
				Object item = ie.getItem();
				if(ie.getStateChange() == ItemEvent.SELECTED) {
					showTableData();
				}else if(ie.getStateChange() == ItemEvent.DESELECTED) {
				  // Item is no longer selected
				  
				}
			}
		});
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
		setBounds(200,100,1000,600);
		setVisible(true);
	}
	//  Overriding ItemListener
	public void showTableData() {
 
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
		//TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
		//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
		//table = new JTable(model);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        s1	= (String)cbLevel.getSelectedItem(); 
		s2	= (String)cbClass.getSelectedItem();
		s3	= (String)cbStudent.getSelectedItem(); 
		s4	= (String)cbSubject.getSelectedItem();
		
		int mark1,mark2,mark3,mark4,mark5,final_perc,roll;
		String grade="";
		try {
            pst = connect.prepareStatement("select * from mark_entry where class_name='"+s2+"' and subject='"+s4+"' and level='"+s1+"'");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while(rs.next()) {
                roll = Integer.parseInt(rs.getString("rollno"));
                mark1 = Integer.parseInt(rs.getString("mark1"));
                mark2 = Integer.parseInt(rs.getString("mark2"));
                mark3 = Integer.parseInt(rs.getString("mark3"));
                mark4 = Integer.parseInt(rs.getString("mark4"));
                mark5 = Integer.parseInt(rs.getString("mark5"));
                final_perc = Integer.parseInt(rs.getString("final_perc"));
                grade = rs.getString("final_grade");
                model.addRow(new Object[]{roll,mark1, mark2, mark3, mark4,mark5,final_perc,grade});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
		JButton exit = new JButton("Exit",new ImageIcon("images/exit2.png"));
		exit.addActionListener(this);
		frame1.add(exit,BorderLayout.SOUTH);
        frame1.setVisible(true);
        frame1.setBounds(350, 250, 550, 350);
    }
	public void actionPerformed(ActionEvent e) 
    {
		String command = e.getActionCommand();
		if(command.equals("Exit")) {
			frame1.setVisible(false);
        }
	}
	//Main Method
	public static void main(String args[]){
		String userEmail = new String();
		String title	 = new String();
		int xValue=0, yValue=0;
		new ViewMark(title,userEmail, xValue, yValue);

	}
 }
