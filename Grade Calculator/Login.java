package ss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

  
public class Login extends JFrame implements ActionListener {
    JTextField userText;
	JPasswordField passText;
	
	public Login()
	{
		super("GRADING MANAGEMENT SYSTEM");
        this.setIconImage((new ImageIcon("images/main_logo.jpg")).getImage());
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        JLabel lblBackground = new JLabel();
        lblBackground.setLayout(new BorderLayout());
		//lblBackground.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), " Login "));
        container.add(lblBackground, BorderLayout.CENTER);
		
            JPanel fieldPanel = new JPanel(new GridLayout(6,1));
            fieldPanel.setOpaque(false);
            lblBackground.add(fieldPanel, BorderLayout.CENTER);
			
                userText = new JTextField();
                passText = new JPasswordField(15);
				
               
                userText.setFont(new Font("Courier",Font.BOLD,16));
                passText.setFont(new Font("Courier",Font.BOLD,16));
                
                userText.setOpaque(false);
                passText.setOpaque(false);
                
                fieldPanel.add(new JLabel());
                fieldPanel.add(new JLabel());
                fieldPanel.add(userText);
                fieldPanel.add(passText);
                fieldPanel.add(new JLabel());
                fieldPanel.add(new JLabel());

            JPanel labelPanel = new JPanel(new GridLayout(6,1));
			labelPanel.setOpaque(false);
            lblBackground.add(labelPanel, BorderLayout.WEST);
                JLabel userEmail = new JLabel("UserEmail: ", JLabel.RIGHT);
                JLabel userPassword = new JLabel("Password: ", JLabel.RIGHT);               

                userEmail.setDisplayedMnemonic('U');
                userPassword.setDisplayedMnemonic('P');                

                userEmail.setLabelFor(userText);
                userPassword.setLabelFor(passText);
                
                labelPanel.add(new JLabel());
                labelPanel.add(new JLabel());
                labelPanel.add(userEmail);
                labelPanel.add(userPassword);
                labelPanel.add(new JLabel());
                labelPanel.add(new JLabel());

        JPanel pnlSouth = new JPanel(new GridLayout(2,1));
        container.add(pnlSouth, BorderLayout.SOUTH);
            JPanel btnPanel1 = new JPanel(new GridLayout(1,3));
            pnlSouth.add(btnPanel1);
                JButton btnSave = new JButton("Login");
                JButton btnClear = new JButton("Clear");
                JButton btnExit = new JButton("Exit");
                btnSave.setRolloverIcon(new ImageIcon("save2.png"));
                btnExit.setRolloverIcon(new ImageIcon("exit2.png"));
                btnPanel1.add(btnSave);
                btnPanel1.add(btnClear);
                btnPanel1.add(btnExit);

            JPanel btnPanel2 = new JPanel(new GridLayout(1,1));
            pnlSouth.add(btnPanel2);
                JButton newReg = new JButton("Register", new ImageIcon());
                btnPanel2.add(newReg);
               
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {System.exit(0);}
        });
		btnSave.addActionListener(this);
		btnClear.addActionListener(this);
        btnExit.addActionListener(this);
		newReg.addActionListener(this);
                
        this.setBounds(120,150,400,350);
        this.setVisible(true);
	}

    public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		String value1 = userText.getText().trim();
		String value2 = new String(passText.getPassword());
		if(command.equals("Login")){
			if(Validate.authenticate(value1,value2)) {
				new Dashboard(value1);
                dispose();
			}else{
				System.out.println("Please enter a valid email-id and password");
				JOptionPane.showMessageDialog(this,"Incorrect login details entered!", "Error",JOptionPane.ERROR_MESSAGE);
				userText.setText("");
				passText.setText("");
			}
		}else if(command.equals("Clear")){
			userText.setText("");
			passText.setText("");
		}else if(command.equals("Exit")){
			  dispose();
		}else if(command.equals("Register")){
			 new NextPage();
			 this.dispose();
		}
		
    }

	public static void main(String arg[]) {
		new Login();
	}
 }
 
