package car_dealership;

public class Vehicle {

    private String name;
    private double price;

    public Vehicle() {
        this("",0);
    }

    public Vehicle(String nameInput, double priceInput) {
        this.name=nameInput;
        this.price=priceInput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
