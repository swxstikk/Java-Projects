package car_dealership;

public class Customer {

    private String name;
    private String address;
    private double cashOnHand;

    public Customer() {
        this("","",0);
    }

    public Customer(String nameInput, String addressInput, double cashInput) {
        // making a constructor to initialise the variables for this class
        this.name=nameInput;
        this.address=addressInput;
        this.cashOnHand=cashInput;
    }

    public void purchaseCar(Vehicle vehicle, Employee employee, boolean finance) {
        employee.handleCustomer(this, finance,vehicle );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCashOnHand() {
        return cashOnHand;
    }

    public void setCashOnHand(double cashOnHand) {
        this.cashOnHand = cashOnHand;
    }
}
