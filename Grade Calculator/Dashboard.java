package ss;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;

public class Dashboard extends JFrame implements ActionListener {
    String sessEmail = new String();

    public Dashboard(String dataEmail) {
	//public Dashboard() {
        super("Dashboard");
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
		sessEmail = dataEmail;
		//sessEmail = "dataEmail";

        JMenuBar mBar = new JMenuBar();
        this.setJMenuBar(mBar);
            JMenu file = new JMenu("Profile");
            file.setMnemonic('P');
            file.setIcon(new ImageIcon("images/user.gif"));
            mBar.add(file);	
				// Add Submenu to first menu Profile
                JMenuItem viewProfile = new JMenuItem("View");
                viewProfile.setMnemonic('V');
                viewProfile.setIcon(new ImageIcon("images/view.png"));
				viewProfile.addActionListener(this);
                file.add(viewProfile);
				
				JMenuItem editProfile = new JMenuItem("Edit");
                editProfile.setMnemonic('E');
                editProfile.setIcon(new ImageIcon("images/edit2.gif"));
				editProfile.addActionListener(this);
                file.add(editProfile);
                file.addSeparator();
				
				JMenuItem chngPassword = new JMenuItem("Change Password");
                chngPassword.setMnemonic('C');
                chngPassword.setIcon(new ImageIcon("images/key2.png"));
				chngPassword.addActionListener(this);
                file.add(chngPassword);
                file.addSeparator();
				
                JMenuItem fileExit = new JMenuItem("Exit", new ImageIcon("images/exit1.png"));
                fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
                fileExit.setMnemonic('x');
				fileExit.addActionListener(this);
                file.add(fileExit);

            JMenu taskMenu = new JMenu("Task Manager");
            taskMenu.setMnemonic('E');
            taskMenu.setIcon(new ImageIcon(""));
            mBar.add(taskMenu);
                JMenu taskClass = new JMenu("Class Management");
                taskClass.setMnemonic('M');
                taskClass.addActionListener(this);
                taskMenu.add(taskClass);
					JMenuItem addClass = new JMenuItem("Add Class", new ImageIcon("images/ADD.png"));
					//JMenuItem editClass = new JMenuItem("Edit Class");
					//JMenuItem viewClass = new JMenuItem("View Class");
					addClass.addActionListener(this);
					taskClass.add(addClass);
					//taskClass.add(editClass);
					//taskClass.add(viewClass);					
                
                JMenu taskSubject = new JMenu("Subject Management");
                taskSubject.setMnemonic('S');
                taskSubject.addActionListener(this);
                taskMenu.add(taskSubject);
                taskMenu.addSeparator();
					JMenuItem addSubj = new JMenuItem("Add Subject", new ImageIcon("images/ADD.png"));
					//JMenuItem editSubj = new JMenuItem("Edit Subject");
					//JMenuItem viewSubj = new JMenuItem("View Subject");
					addSubj.addActionListener(this);
					taskSubject.add(addSubj);
					//taskSubject.add(editSubj);
					//taskSubject.add(viewSubj);
                JMenu taskStudManage = new JMenu("Student Management");
                taskStudManage.setMnemonic('S');
                taskStudManage.addActionListener(this);
                taskMenu.add(taskStudManage);
					JMenuItem addRoutine = new JMenuItem("Add Student", new ImageIcon("images/ADD.png"));
					JMenuItem viewRoutine = new JMenuItem("Edit Student", new ImageIcon("images/edit2.gif"));
					addRoutine.addActionListener(this);
					viewRoutine.addActionListener(this);
					taskStudManage.add(addRoutine);
					taskStudManage.add(viewRoutine);					
				JMenu mappStudSubj = new JMenu("Student Subject Mapping");
                mappStudSubj.setMnemonic('M');
                mappStudSubj.addActionListener(this);
                taskMenu.add(mappStudSubj);
					JMenuItem addMapping = new JMenuItem("Subject Mapping");
					addMapping.addActionListener(this);
					mappStudSubj.add(addMapping);
				JMenu subjGrade = new JMenu("SUBJECT GRADING");
                subjGrade.setMnemonic('G');
                subjGrade.addActionListener(this);
                taskMenu.add(subjGrade);
					JMenuItem addGrade = new JMenuItem("Add Grade", new ImageIcon("images/ADD.png"));
					addGrade.addActionListener(this);
					subjGrade.add(addGrade);
			// Third Menu
			JMenu markMenu = new JMenu("Grade Student");
            markMenu.setMnemonic('G');
            markMenu.setIcon(new ImageIcon(""));
            mBar.add(markMenu);
				JMenuItem enterMark = new JMenuItem("Mark Entry", new ImageIcon("images/ADD.png"));
				JMenuItem viewMark = new JMenuItem("View Entry", new ImageIcon("images/view.png"));
				JMenuItem chkMark = new JMenuItem("Edit Mark", new ImageIcon("images/edit2.gif"));
                enterMark.addActionListener(this);
                chkMark.addActionListener(this);
                viewMark.addActionListener(this);
                markMenu.add(enterMark);
                markMenu.add(chkMark);
                markMenu.add(viewMark);
				
        this.setIconImage(new ImageIcon("images/main_logo.jpg").getImage());
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        }); 
		this.setBounds(150,100,800,500);
		this.setVisible(true);
		
	}

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
		if(command.equals("View")){
			//actionWindow("View Profile", 700, 600); 
			new DisplayProfile("View Profile",sessEmail ,800, 700);
		}else if(command.equals("Edit")){
			new EditProfile("Edit Profile",sessEmail, 800,700);
		}else if(command.equals("Change Password")){
			new ChangePassword("Change Password",sessEmail, 500,350);
		}else if(command.equals("Add Class")){
			new AddClass("Add Class",sessEmail, 500,350);
		}else if(command.equals("Add Subject")){
			new AddSubject("Add Subject",sessEmail, 500,350);
		}else if(command.equals("Add Student")){
			new AddStudent(sessEmail);
		}else if(command.equals("Edit Student")){
			new SearchStudent("Edit Student",sessEmail,500,350);
		}else if(command.equals("Subject Mapping")){
			new SubjectMapping("Subject Mapping",sessEmail,500,350);
		}else if(command.equals("Add Grade")){
			new AddGrade("Add Grade",sessEmail,700,600);
		}else if(command.equals("Mark Entry")){
			new MarkEntry();
		}else if(command.equals("Edit Mark")){
			new SearchMark("Search Student",sessEmail,500,450);
		}else if(command.equals("View Entry")){
			new ViewMark("View Mark List",sessEmail,500,450);
		}else if(command.equals("Exit")){
            this.dispose();
        }
    }

	
	public static void main(String arg[]) {
		String data = new String();
		//new Dashboard();
		new Dashboard(data);
	}
}


