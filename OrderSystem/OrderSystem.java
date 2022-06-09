/*------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 3
-------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class OrderSystem extends JFrame implements ActionListener, ListSelectionListener {

    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
	private Container container;
	private BorderLayout layout;
    private JLabel customerCode, customerDetails, messages;
    private JTextArea messageInformation, customerDetail;
    private DefaultListModel<String> customerCodes;
    private JList<String> customerList;
    private JButton newOrder, saveOrder;
    private Customer customerObject;
    private NewOrder newOrderWindow;
	private int currentIndex;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public static void main(String[] args) {
        OrderSystem os = new OrderSystem();
        os.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        os.setSize(800,500);
        os.setVisible(true);

    }

    public OrderSystem() {
		
        super("Order Management System"); //Title for the GUI
		currentIndex = -1;
		customerObject = null;
		// Loading data from text files into containers
		
        customers = new ArrayList<Customer>();
        loadCustomers();
        products = new ArrayList<Product>();
        loadProducts();
        orders = new ArrayList<Order>();
		
		// Building the GUI
		addContainer();
        addCustomerComponents();
        addButtons();
        addMessages();
		displayCustomerDetails();

        /*
		
		---------- Test cases and solutions to be implemented for the assignment: -------------------------------------------------------------------------
		
        ** Basic layout of the GUI is constructed. (DONE)
        ** loading data from given txt files. (DONE)
		
		*Display formatting:
		
			** Load customer codes from .txt file into a list when program is run. (DONE)
			** Display customer details in a text area when a customer code is selected. (DONE)
			
        * Implement the ActionListener methods:
		
			** 'New Order' button directs to a new GUI window. (DONE)
			** NewOrder window is launched ONLY IF a customer code is selected. (DONE)
			** Display error message if customer code is not selected and 'New Order' button is clicked. (DONE)
			** When a product is ordered but stock quantity is < 0, display error message. (DONE)
			** When product is ordered and stock quantity > 0, order detail of product is displayed. (DONE)
			** Decrease stock quantity by order quantity of the ordered product when product ordered has stock quantity > 0. (DONE)
			** Increase stock quantity of product by unorder amount when 'Unorder' button is clicked. (DONE)
			** If order quantity is 0, remove the ordered product from the orders array list. (DONE)
			** When 'Ok' button is clicked, order details of products are saved in the orders array list and 'NewOrder' window is closed. (DONE)
			** When 'Save Order' button is clicked, order details are saved into a txt file. (DONE)
			
        * Test the Formatter method for creating new files:
		
			** A new txt file is generated from the writeData method. (DONE)
			** Contents of the new file should hold data from user interactions with the GUI. (DONE)
			
		-----------------------------------------------------------------------------------------------------------------------------------------------------
			
         */

    }

    public void loadCustomers() {
        String file = "customers.txt";
        Path path = Paths.get(file);
        int count = 0;
        try {
            if(Files.exists(path)) {
                if(!Files.isDirectory(path)) { //Not a directory, read data
                    Scanner s = new Scanner(path);
                    s.useDelimiter(",|\r\n|\t|\n");
                    customers.clear();
                    while(s.hasNext()) {
                        Customer cust = new Customer();
                        cust.readData(s);
                        customers.add(cust);
                        count ++;
                    }
                    s.close();
                }
                else
                    System.out.printf("File %s is a directory", path);
            }
            else
                System.out.printf("File %s does not exist", path);
        }
        catch (IOException err) {
            System.out.println("IO exception error");
        }
    }

    public void loadProducts() {
        String file = "products.txt";
        Path path = Paths.get(file);
        int count = 0;
        try {
            if(Files.exists(path)) {
                if(!Files.isDirectory(path)) { //Not a directory, read data
                    Scanner s = new Scanner(path);
                    s.useDelimiter(",|\r\n|\t|\n");
                    products.clear();
                    while(s.hasNext()) {
                        Product prod = new Product();
                        prod.readData(s);
                        products.add(prod);
                        count ++;
                    }
                    s.close();
                }
                else
                    System.out.printf("File %s does not exist", path);
            }
        }
        catch (IOException err) {
            System.out.println("IO exception error");
        }   
    }

    public void saveOrders() {
		String filename = "new_orders.txt";
            //Open an output file
            try {
			Formatter f = new Formatter(filename);
			for(Order o: orders) {
			o.writeData(f);
		}
            f.close();        
			} catch(FileNotFoundException e) {
		System.out.println(e);
		}
	}
	
	private void addContainer() {
		container = getContentPane();
		layout = new BorderLayout();
		container.setLayout(layout);
		container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}

    private void addCustomerComponents() {
		
		JPanel panel = new JPanel(new GridLayout(2,2));
        //Add the label
        customerCode = new JLabel("Customer code ");
        panel.add(customerCode);
        customerDetails = new JLabel("Customer details: ");
		panel.add(customerDetails);

        //Add the customer code list
        customerCodes = new DefaultListModel<>();
        for(Customer c: customers)
            customerCodes.addElement(c.getCode());
        customerList = new JList<>(customerCodes);
        customerList.setVisibleRowCount(5);
        customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerList.addListSelectionListener(this);
        panel.add(new JScrollPane(customerList));

        customerDetail = new JTextArea("",4,8);
        customerDetail.setLineWrap(true);
        customerDetail.setWrapStyleWord(true);
        panel.add(customerDetail);
		
		// Adding the panel to the main panel
		container.add(panel, BorderLayout.NORTH);
    }

    private void addButtons() {
		JPanel panel = new JPanel(new FlowLayout());
        newOrder = new JButton("New Order");
        newOrder.addActionListener(this); // This is to ensure when button is clicked, new window is opened.
		//add(newOrder);
        panel.add(newOrder);
        saveOrder = new JButton("Save Order");
		saveOrder.addActionListener(this); // This is to ensure when button is clicked, a new file containing orders is created.
        panel.add(saveOrder);
		
		// Adding the panel to the main panel
		container.add(panel, BorderLayout.CENTER);
    }

    private void addMessages() {
		JPanel panel = new JPanel(new FlowLayout());
        messages = new JLabel("Messages");
        panel.add(messages);
        messageInformation = new JTextArea("",4,8);
        messageInformation.setLineWrap(true);
        messageInformation.setWrapStyleWord(true);
        panel.add(messageInformation);
		
		// Adding the panel to the main panel
		container.add(panel, BorderLayout.SOUTH);
    }

    private void displayCustomerDetails() {
        if(customerObject != null) {
            String s = customerObject.displayInformation();
            customerDetail.setText(s);
			messageInformation.setText("");
        }
        else {
            messageInformation.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        messageInformation.setText("");
        if(e.getSource() == newOrder) { // Takes user to new window for adding an order
		if(currentIndex >= 0) { 
				currentIndex ++;
				customerObject = customers.get(currentIndex);
				displayCustomerDetails();
				messageInformation.setText("");
				newOrderWindow = new NewOrder(this);
			}
			else {
				messageInformation.setText("Select a customer");
			}
        } else {
			// Saves the order to a .txt file and disposes the window
			orders.add(newOrderWindow.getOrder());
			System.out.println("test");
			saveOrders();
			messageInformation.setText("Orders are saved");
		}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
		int i = customerList.getSelectedIndex();
		messageInformation.setText("");
		if(i >= 0) {	
			//Display the customer details
			currentIndex = i;
			customerObject = customers.get(currentIndex);
			displayCustomerDetails();
		}
    }
}
