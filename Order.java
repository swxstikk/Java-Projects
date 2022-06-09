/*------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 3
-------------------------------------------------------*/

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Order implements MyFileIO {

    private int orderNumber;
    private String code;
    private String orderDate;
    private ArrayList<OrderDetail> orderDetails;
    private double total;

    public Order(int orderNumber, String code, String orderDate) {
		this.orderNumber = orderNumber;
		this.code = code;
		this.orderDate = orderDate;
    }
	
	public Order() {
		this.orderDetails = new ArrayList<OrderDetail>();
		this.total = 0.0;
		this.orderDate = "";
		this.code = "1";
		this.orderNumber = 1;
	}
	
	 public ArrayList<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
	
	public void setOrderDetail(ArrayList<OrderDetail> arrayList) {
		this.orderDetails = arrayList;
	}
	
	public void addOrderDetail(OrderDetail od) {
		this.orderDetails.add(od);
	}

    @Override
    public void readData(Scanner s) {
		orderNumber = s.nextInt();
		code = s.next();
		orderDate = s.next();
		total = s.nextDouble();
		for(int i=0; i<orderNumber; i++) {
			OrderDetail od = new OrderDetail();
			od.readData(s);
			orderDetails.add(od);
		}
    }

    @Override
    public void writeData(Formatter f) {
		f.format("%s\n", toString());
    }
	
	@Override
	public String toString() {
		String s = "";
        s+= orderNumber;
        s+= ", " + code;
        s+= ", " +orderDate + "\n";
        for(OrderDetail od: orderDetails) {
			s+=od.toString();
		}
        return s;
	}
	
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
