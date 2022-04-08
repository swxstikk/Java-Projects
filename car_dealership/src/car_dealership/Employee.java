package car_dealership;

public class Employee {

    private String name;
    private String position;

    public Employee() {
        this("","");
    }

    public Employee(String nameInput, String position) {
        this.name=nameInput;
        this.position=position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void processTransaction(Customer c, Vehicle v) {
        System.out.println("Congratulations " + c.getName() + " on the purchase of your new " + v.getName());
    }

    public void runCreditHistory(Customer c, double loanAmount) {
        Vehicle v = new Vehicle();
        if(c.getCashOnHand() < v.getPrice()) {
            c.setCashOnHand(c.getCashOnHand() + loanAmount);
        } else {
            System.out.println("You have sufficient funds to purchase this vehicle.");
            processTransaction(c, v);
        }
    }

    public void handleCustomer(Customer c, boolean finance, Vehicle v) {
        double loanAmount = v.getPrice() - c.getCashOnHand();
        if(finance==true) {
            runCreditHistory(c,loanAmount);
        } else if (v.getPrice() <= c.getCashOnHand()) {
            processTransaction(c, v);
        } else {
            System.out.println("Please provide sufficient funds to purchase this vehicle.");
        }
    }
}
