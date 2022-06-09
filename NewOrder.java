import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewOrder extends JFrame implements ActionListener, ListSelectionListener {
    private JButton order, unorder, ok, cancel;
    private JTextArea productInformation, orderInformation, messageDisplay;
    private JLabel productName, orderDetails, message;
    private DefaultListModel<String> productInfo;
    private JList<String> productDetails, orderedItems;
	private Container container;
	private BorderLayout layout;
    private OrderSystem os;
	private Product product;
	private Customer customer;
	private Order orders;
	private OrderDetail orderDetail;
	private int currentIndex;

    public NewOrder(OrderSystem os) {
        // Creating the GUI for new orders.
        super("Order products");
		currentIndex = -1;
		product = null;
        this.os = os;
		addContainer();
		addProductComponents();
        addButtons();
        addMessage();
		displayProductDetails();
        setVisible(true);
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	private void addContainer() {
		container = getContentPane();
		layout = new BorderLayout();
		container.setLayout(layout);
		container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
	
	private void addProductComponents() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		// Add the label
		productName = new JLabel("Product name");
        panel.add(productName);
        orderDetails = new JLabel("Order");
        panel.add(orderDetails);
		
		// Add the product list
		productInfo = new DefaultListModel<>();
        for(Product p: os.getProducts())
        productInfo.addElement(p.getName());
        productDetails = new JList<>(productInfo);
        productDetails.setVisibleRowCount(5);
        productDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productDetails.addListSelectionListener(this);
        panel.add(new JScrollPane(productDetails));
		
		// Add product details
		productInformation = new JTextArea("",4,8);
        productInformation.setLineWrap(true);
        productInformation.setWrapStyleWord(true);
        panel.add(productInformation);
		
		// Add order details
		orderInformation = new JTextArea("",4,8);
        orderInformation.setLineWrap(true);
        orderInformation.setWrapStyleWord(true);
        panel.add(orderInformation);
		
		// Adding the panels to the main panel
		container.add(panel, BorderLayout.NORTH);
		
	}

    private void addButtons() {
		JPanel panel = new JPanel();
        order = new JButton("Order>>");
		order.addActionListener(this);
        panel.add(order);
        unorder = new JButton("Unorder<<");
		unorder.addActionListener(this);
        panel.add(unorder);
        ok = new JButton("OK");
		ok.addActionListener(this);
        panel.add(ok);
        cancel = new JButton("Cancel");
		cancel.addActionListener(this);
        panel.add(cancel);
		
		// Adding the panel to the main panel
		container.add(panel);
    }

    private void addMessage() {
		JPanel panel = new JPanel();
		message = new JLabel("Messages");
        panel.add(message);
		messageDisplay = new JTextArea("",8,8);
		messageDisplay.setLineWrap(true);
		messageDisplay.setWrapStyleWord(true);
		panel.add(messageDisplay);
		
		// Adding the panel to the main panel
		container.add(panel, BorderLayout.SOUTH);
    }

    private void displayProductDetails() {
        if(product!= null) {
            String s = product.displayInformation();
            productInformation.setText(s);
        }
        else {
            messageDisplay.setText("");
        }
    }
	
	public Order getOrder() {
		return orders;
	}
	
	// Implemented interface methods
	@Override 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == order) {
			if(currentIndex < 0) {
				messageDisplay.setText("Select a product");
			} else if(product.getStockQuantity() <= 0){
				messageDisplay.setText("The product is not in stock");
			} else {
				if(orderDetail!=null && product.getStockQuantity() > 0) {
					orderDetail.increaseOrderQuantity();
					product.decreaseStock();
					String s = product.displayInformation();
					productInformation.setText(s);
					orderDetail.updateSubtotal();
					String x = orderDetail.displayInformation();
					orderInformation.setText(x);
					messageDisplay.setText("The product is ordered");
				} else if(orderDetail == null){
					product.decreaseStock();
					String x = product.displayInformation();
					productInformation.setText(x);
					orderDetail = new OrderDetail(product.getName(), product.getPrice(), 1);
					String s = orderDetail.displayInformation();
					orderInformation.setText(s);
					messageDisplay.setText("The product is ordered");
				}
			}		
		} else if(e.getSource() == unorder) {
			if(orderDetail!=null && orderDetail.getOrderQuantity() > 0 ) {
					orderDetail.decreaseOrderQuantity();
					product.increaseStock();
					String s = product.displayInformation();
					productInformation.setText(s);
					orderDetail.updateSubtotal();
				} else {
					messageDisplay.setText("Cant unorder a null product");
				}
				String s = orderDetail.displayInformation();
				if(orderDetail.getOrderQuantity() == 0) {
					orderInformation.setText("");
				} else {
					orderInformation.setText(s);
				}
				messageDisplay.setText("The product is unordered");
		} else if(e.getSource() == ok) {
			if(orderDetail==null) {
				messageDisplay.setText("Order does not exist");
			} else if(orders == null){
				orders = new Order();
				customer = new Customer();
				Date date = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = dateFormat.format(date);
				orders.setOrderDate(strDate);
				orders.setCode(customer.getCode());
				orders.addOrderDetail(orderDetail);
			} else {
				Date date = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = dateFormat.format(date);
				orders.setOrderDate(strDate);
				orders.setCode(customer.getCode());
				orders.addOrderDetail(orderDetail);
			}
			
			messageDisplay.setText("The order has been saved");
			dispose();
		} else if(e.getSource() == cancel){
			// Exits without saving order
			dispose();
			}
		}	
		
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int i = productDetails.getSelectedIndex();
		messageDisplay.setText("");
		if(i >= 0) {	
			//Display the product and order details
			currentIndex = i;
			product = os.getProducts().get(currentIndex);
			displayProductDetails();
		}
	}
}





