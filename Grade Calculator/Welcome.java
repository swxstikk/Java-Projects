package ss;

import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.sql.*;
/////////////////////////////////////////////////////////////////////


public class Welcome extends JFrame implements ActionListener {
   
    Container content;
    JLabel lblBackground;
    CardLayout cardLayout;

	public Welcome() {
        super("GRADING MANAGEMENT SYSTEM");
        this.setIconImage((new ImageIcon("images/main_logo.jpg")).getImage());
        content = this.getContentPane();
        content.setLayout(new BorderLayout());
                  
        //Background
        lblBackground = new JLabel(new ImageIcon("images/desert.JPG"));
        lblBackground.setLayout(new BorderLayout());
        content.add(lblBackground, BorderLayout.CENTER);
            
            JPanel pnlNorth= new JPanel(new GridLayout(6,1));
            pnlNorth.setOpaque(false);
            lblBackground.add(pnlNorth, BorderLayout.NORTH);

				//NORTH
                JLabel lblwelcome=new JLabel("WELCOME",JLabel.CENTER);    
                lblwelcome.setFont(new Font("Elephant",Font.BOLD, 38));  
                lblwelcome.setForeground(Color.RED);                 

                //South
                JLabel lblTo=new JLabel(   "TO",JLabel.CENTER); 
                lblTo.setFont(new Font("Elephant",Font.BOLD, 38));  
                lblTo.setForeground(Color.RED);
                
                pnlNorth.add(lblwelcome);
                pnlNorth.add(new JLabel());
                pnlNorth.add(new JLabel());
                pnlNorth.add(new JLabel());
                pnlNorth.add(lblTo);
                pnlNorth.add(new JLabel());
               
            //Center
			JLabel lblCenter=new JLabel("DIGITAL GRADING SYSTEM",JLabel.CENTER);
			lblBackground.add(lblCenter,BorderLayout.CENTER);
            lblCenter.setFont(new Font("Elephant",Font.BOLD, 38));  
            lblCenter.setForeground(Color.RED);
             
            //South
            JPanel pnlSouth=new JPanel(new GridLayout(1,5));
            pnlSouth.setOpaque(false);
	        lblBackground.add(pnlSouth,BorderLayout.SOUTH);
                JButton btnEnter = new JButton("Enter", new ImageIcon("images/forward2.GIF"));
                btnEnter.setOpaque(false);
                btnEnter.addActionListener(this);
                btnEnter.setFont(new Font("Comic Sans",Font.BOLD,18));
                btnEnter.setRolloverIcon(new ImageIcon("images/SendChat.GIF"));
                pnlSouth.add(new JLabel());
                pnlSouth.add(new JLabel());
                pnlSouth.add(new JLabel());
                pnlSouth.add(new JLabel());
                pnlSouth.add(btnEnter);
        

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
        this.setBounds(100,90,800,600);
        this.setVisible(true);
	}

     public void actionPerformed(ActionEvent ae) {
        content.remove(lblBackground);
        content.add(new JLabel("WelcomePanel "), BorderLayout.CENTER);
        String command = ae.getActionCommand();
        if(command.equalsIgnoreCase("enter")) {
			new Login(); 
            this.dispose();
        }
	} 

	public static void main(String arg[]){
		new Welcome();
	}
}