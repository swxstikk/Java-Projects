import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Product implements MyFileIO {

    private String pname;
    private String supplier;
    private String category;
    private int stockQuantity;
    private double price;

    public Product() {

    }

    public String getSupplier() {
        return supplier;
    }

    public String getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return pname;
    }
	
	public int setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
        return stockQuantity;
	}
    
    public void increaseStock() {
        stockQuantity++;
    }
    
    public void decreaseStock() {
        stockQuantity--;
    }

    @Override
    public void readData(Scanner s) {
        try {
            pname = s.next();
            supplier = s.next();
            category = s.next();
            stockQuantity = s.nextInt();
            price = s.nextDouble();
        } catch(InputMismatchException e)  {
            System.out.printf("%sWrong input type "+e);
            s.next(); //
        }
    }

    public String toString() {
        String s = "";
        s+= "pname: " + pname;
        s+= "Supplier: " + supplier;
        s+= "Category: " + category;
        s+= "Stock quantity: " + stockQuantity;
        s+= "Price: " + price;
        return s;
    }

    @Override
    public void writeData(Formatter f) {
        f.format("%s\n", this);
    }
	
	 public String displayInformation() {
        String s = "Product name: " + pname + "\nSupplier: " + supplier + "\nCategory: " + category + "\nStock Quantity: " + stockQuantity + "\nPrice: " + price;
        return s;
    }
}
