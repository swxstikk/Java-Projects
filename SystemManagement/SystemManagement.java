/*
------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 2
-------------------------------------------------------
*/

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class SystemManagement {

    // Class attributes
    private ArrayList<Employee> employees;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Trip> trips;

    // Initializing attributes with constructor
    public SystemManagement() {
        this.employees = new ArrayList<Employee>(0);
        this.vehicles = new ArrayList<Vehicle>(0);
        this.trips = new ArrayList<Trip>(0);
    }

    public static void main(String[] args) {
        SystemManagement sm = new SystemManagement();
        String eFileName;
        String vFileName;
        String tFileName;
        Scanner input = new Scanner(System.in);
        System.out.print("Input employee filename: ");
        eFileName = input.nextLine();
        System.out.print("Input vehicle filename: ");
        vFileName = input.nextLine();
        System.out.print("Input trips filename: ");
        tFileName = input.nextLine();
        sm.readEmployeeData(eFileName);
        sm.readVehicleData(vFileName);
        sm.readTripsData(tFileName);
        sm.process(input);
    }

    // various methods  for defining the logic of the system
        public void writeEmployees(String name) throws IOException {

            Formatter out = new Formatter(name); // Opens an output file
            for (Employee e : employees) {
                e.writeData(out);
            }
            out.close();
        }

    public void writeVehicles(String name) throws IOException {

        Formatter out = new Formatter(name); // Opens an output file
        for (Vehicle v: vehicles) {
            v.writeData(out);
        }
        out.close();
    }

    public void writeTrips(String name) throws IOException {

        Formatter out = new Formatter(name); // Opens an output file
        for (Trip t: trips) {
            t.writeData(out);
        }
        out.close();
    }


    public void readEmployeeData(String name) {
        // reads data from a text file and stores in the respective container
        Path path = Paths.get(name);
        try {
            if (Files.exists(path)) {
                if (!Files.isDirectory(path)) {//if file is not a directory, read data
                    Scanner input = new Scanner(path);
                    input.useDelimiter(",|\r\n|\t|\n");
                    while (input.hasNext()) {
                        String data = input.next();
                        if (data.equals("D")) {
                            Driver d = new Driver();
                            d.readData(input);
                            employees.add(d);
                        } else if (data.equals("M")) {
                            Mechanic m = new Mechanic();
                            m.readData(input);
                            employees.add(m);
                        }
                    }
                    input.close();
                } else {
                    System.out.printf("File %s does not exist%n", path);
                }
            }
        } catch (IOException err) {
            System.out.println("IO exception error");
        } catch (NoSuchElementException e) {
            System.err.println("Exception occurred" + e);
        }
    }

    public void readVehicleData(String name) {
        // reads data from a text file and stores in the respective container
        Path path = Paths.get(name);
        try {
            if (Files.exists(path)) {
                if (!Files.isDirectory(path)) {//if file is not a directory, read data
                    Scanner input = new Scanner(path);
                    input.useDelimiter(",|\r\n|\t|\n");
                    while (input.hasNext()) {
                        String data = input.next();
                        if (data.equals("T")) {
                            Truck t = new Truck();
                            t.readData(input);
                            vehicles.add(t);
                        }
                    }
                    input.close();
                }
            } else {
                System.out.printf("File %s does not exist%n", path);
            }
        } catch (IOException err) {
            System.out.println("IO exception error");
        } catch (NoSuchElementException e) {
            System.err.println("Exception occurred" + e);
        }
    }

    public void readTripsData(String name) {
        // reads data from a text file and stores in the respective container
        Path path = Paths.get(name);
        try {
            if (Files.exists(path)) {
                if (!Files.isDirectory(path)) {//if file is not a directory, read data
                    Scanner input = new Scanner(path);
                    input.useDelimiter(",|\r\n|\t|\n");
                    while (input.hasNext()) {
                        Trip t = new Trip();
                        t.readData(input);
                        trips.add(t);
                    }
                    input.close();
                }
            } else {
                System.out.printf("File %s does not exist%n", path);
            }
        } catch (IOException err) {
            System.out.println("IO exception error");
        } catch (NoSuchElementException e) {
            System.err.println("Exception occurred" + e);
        }
    }
    public void readData() {
        /*Reads data from user input and stores in the respective container
            Refer to readEmployeeData(), readVehicleData() and readTripsData()
         */
    }
    public void writeData() {
        /*Writes data from user input and stores in the respective container
            Refer to writeEmployees(), writeVehicles() and writeTrips()
         */
    }

    private void printEmployees() {
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

    private void printVehicles() {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void printTrips() {
        for (Trip t : trips) {
            System.out.println(t);
        }
    }

    private boolean findEmployee(int employeeNumber) {
        boolean found = false;
        for (Employee e : employees) {
            if (e.getENumber() == employeeNumber) {
                found = true;
                System.out.print(e.toString()+"\n");
            }
        }
        return found;
    }

    private boolean findVehicle(String registrationNumber) {
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.getRego().equals(registrationNumber)) {
                found = true;
                System.out.printf(v.toString()+"\n");
            }
        }
        return found;
    }

    private boolean findTrip(String tripDate) {
        boolean found = false;
        for (Trip t : trips) {
            if (t.getTripDate().equals(tripDate)) {
                found = true;
                System.out.print("Trip: " + t.toString()+"\n");
            }
        }
        return found;
    }

    private boolean employeeExists(int eNumber) {
        boolean found = false;
        for(Employee e: employees) {
            if (e.getENumber() == eNumber) {
                found = true;
                break;
            }
        }
        return found;
    }

    private boolean vehicleExists(String rego) {
        boolean found = false;
        for(Vehicle v: vehicles) {
            if (v.getRego().equals(rego)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private void displayMenu() { // Defining the menu that the user will interact with
        System.out.println("\n");
        System.out.println("1. Display all employees");
        System.out.println("2. Display all vehicles");
        System.out.println("3. Display all trips");
        System.out.println("4. Find an employee");
        System.out.println("5. Find a vehicle");
        System.out.println("6. Find a trip");
        System.out.println("7. Add new trip");
        System.out.println("8. Add new file");
        System.out.print("\nPlease select one from the menu: ");
    }

    private void process(Scanner input) {
        // Defining the operation's user can perform in the menu
        boolean done = false;
        while (!done) {
            // hasNext() is called in order to avoid the NoSuchElementException
            displayMenu();
            try {
                int userInput = input.nextInt();
                switch (userInput) { // using switch case to allow users to interact with the display menu options
                    case 0:
                        System.out.println("Bye-bye");
                        done = true;
                        break;
                    case 1:
                        printEmployees();
                        break;
                    case 2:
                        printVehicles();
                        break;
                    case 3:
                        printTrips();
                        break;
                    case 4:
                        System.out.print("Input an employee number: ");
                        int empNumInput = input.nextInt();
                        if (!findEmployee(empNumInput)) {
                            System.out.print("Employee number " + empNumInput + " does not exist.\n");
                        }
                        break;
                    case 5:
                        System.out.print("Input vehicle registration number: ");
                        String regNumber = input.next();
                        if (!findVehicle(regNumber)) {
                            System.out.print("Vehicle registration number " + regNumber + " does not exist.\n");
                        }
                        break;
                    case 6:
                        System.out.print("Input a trip date: ");
                        String tripDate = input.next();
                        if (!findTrip(tripDate)) {
                            System.out.print("There is no trip on " + tripDate + "\n");
                        }
                        break;
                    case 7:
                        System.out.print("Input a trip number: ");
                        int tripNumber = input.nextInt();
                        System.out.print("Input a trip date: ");
                        String newTripDate = input.next();
                        System.out.print("Input an employee number: ");
                        int newENumber = input.nextInt();
                        if (!employeeExists(newENumber)) {
                            System.out.println("Employee " + newENumber + " does not exist.");
                            System.out.print("Input an employee number: ");
                            input.nextInt();
                        }
                        System.out.print("Input a truck registration number: ");
                        String newRego = input.next();
                        if (!vehicleExists(newRego)) {
                            System.out.println("Vehicle " + newRego + " does not exist.");
                            System.out.print("Input a truck registration number: ");
                            input.next();
                        }
                        ArrayList<TripLeg> newTripLegs = new ArrayList<>(0);
                        System.out.print("Total legs: ");
                        int newLeg = input.nextInt();
                        for (int i = 0; i < newLeg; i++) {
                            System.out.print("Leg number: ");
                            int newLegNumber = input.nextInt();
                            System.out.print("Departure: ");
                            String newDeparture = input.next();
                            System.out.print("Destination: ");
                            String newDestination = input.next();
                            newTripLegs.add(new TripLeg(newLegNumber, newDeparture, newDestination));
                            Trip t = new Trip(tripNumber,newTripDate,newENumber,newRego,newTripLegs);
                            trips.add(t);
                        }
                        break;
                    case 8:
                        System.out.println("Input new employee filename: ");
                        String newEFile = input.next();
                        writeEmployees(newEFile);
                        System.out.println("Input new vehicle filename: ");
                        String newVFile = input.next();
                        writeVehicles(newVFile);
                        System.out.println("Input new trip filename: ");
                        String newTFile = input.next();
                        writeTrips(newTFile);
                        break;
                    default:
                        System.out.println("Choice must be between 0 and 8.");
                }
            } catch (InputMismatchException e) {
                input.next();
            } catch (NoSuchElementException e) {
                input.next();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}