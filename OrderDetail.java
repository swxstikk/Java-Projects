/*------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 3
-------------------------------------------------------*/
import java.util.Scanner;
import java.util.Formatter;
import java.util.InputMismatchException;

public class OrderDetail implements MyFileIO {

    private String pname;
    private double price;
    private int orderQuantity;
    private double subTotal;

    public OrderDetail() {

    }

    public OrderDetail(String pname, double price, int orderQuantity) {
        this.pname=pname;
        this.price=price;
        this.orderQuantity=orderQuantity;
		this.updateSubtotal();
    }
	
	public OrderDetail(String pname, double price, int orderQuantity, double subtotal) {
		this.pname=pname;
        this.price=price;
        this.orderQuantity=orderQuantity;
		this.subTotal = subTotal;
	}
	
	public double updateSubtotal() {
		subTotal = price * orderQuantity;
		return subTotal;
		
	}
	
	public String getCode() {
		return pname;
	}
	
	public void increaseOrderQuantity() {
		orderQuantity++;
	}
	
	public void decreaseOrderQuantity() {
		if(orderQuantity > 0) {
		orderQuantity--;
		}
	}
	
	public int getOrderQuantity() {
		return orderQuantity;
	}

    public void readData(Scanner s) {
		 try {
            pname = s.next();
            price = s.nextDouble();
			orderQuantity = s.nextInt();
			subTotal = s.nextDouble();
        } catch(InputMismatchException e)  {
            System.out.printf("%sWrong input type "+e);
            s.next(); //
        }
	}
    public void writeData(Formatter f) {}
    public String toString() {
		String s = "";
        s+= pname+",";
		s+= price+",";
        s+= orderQuantity;
       // s+= subTotal+"\n";
        return s;
}
	
	public String displayInformation() {
        String s = "Product name: " + pname + "\nPrice: " + price + "\nOrderQuantity: " + orderQuantity + "\nSub total: " + subTotal;
        return s;
    }
	
}
