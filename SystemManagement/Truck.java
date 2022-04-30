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

    @Override
    public String toString() {
        String s = "Truck: ";
        s += getRego();
        String formattedCapacity = s.format("%.2f", capacity);
        String formattedWeight = s.format("%.2f",weight);
        s+= "," + formattedCapacity + "," + formattedWeight;
        return s;

    }

    public void readData(Scanner s) {
        super.readData(s);
        capacity = s.nextDouble();
        weight = s.nextDouble();
}

    public void writeData(Formatter f) {
        f.format("%s\n",toString());
    }
}