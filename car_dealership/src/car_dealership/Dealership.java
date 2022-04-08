package car_dealership;

public class Dealership {
    public static void main(String[] args) {

        // adding vehicles
        Vehicle firstCar = new Vehicle();
        Vehicle secondCar = new Vehicle();
        // assigning values to vehicles
        firstCar.setName("Toyota Corolla 2022");
        firstCar.setPrice(4500);
        secondCar.setName("BMW X6 2022");
        secondCar.setPrice(15000);
        // adding employee
        Employee onlyEmployee = new Employee();
        // assigning values to employee
        onlyEmployee.setName("Max Payne");
        onlyEmployee.setPosition("Sales Head");
        // adding customers
        Customer firstCustomer = new Customer();
        Customer secondCustomer = new Customer();
        // assigning values to customers
        firstCustomer.setName("Swastik Satapathy");
        firstCustomer.setAddress("Somewhere in Wollongong, NSW, 2500");
        firstCustomer.setCashOnHand(5000);
        secondCustomer.setName("Tommy Turnbull");
        secondCustomer.setAddress("Somewhere in Los Angeles, CA, 90001");
        secondCustomer.setCashOnHand(15500);
        // executing the project's functionality for the objects
        firstCustomer.purchaseCar(firstCar, onlyEmployee, false);
        secondCustomer.purchaseCar(secondCar, onlyEmployee, false);
    }

}
