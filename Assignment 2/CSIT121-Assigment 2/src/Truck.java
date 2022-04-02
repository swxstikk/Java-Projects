/*
------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 2
-------------------------------------------------------
*/

import java.util.Scanner;
import java.util.Formatter;

public class Truck extends Vehicle {
    private double capacity;
    private double weight;

    public Truck(double capacityInput, double weightInput) {
        this.capacity=capacityInput;
        this.weight=weightInput;
    }

    public Truck() {
        this(0.0,0.0);
    }

    public String toString() {
        String s="";
        return s;
    }

    public void readData(Scanner s) {

    }

    public void writeData(Formatter f) {

    }
}
