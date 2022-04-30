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
public class TripLeg {

    private int legNumber;
    private String departure;
    private String destination;

    public TripLeg(int lNumber, String dep, String dest) {
        this.legNumber=lNumber;
        this.departure=dep;
        this.destination=dest;
    }

    public TripLeg() {
        this(0,"","");
    }

    public String toString() {
       String s = "";
        s += legNumber + "," + departure + ","+ destination;
       // String list = Arrays.toString(getTripLegs().toArray()).replace("[", "").replace("]", "");
        return s;
    }

    public void readData(Scanner s) {
        legNumber = s.nextInt();
        departure = s.next();
        destination = s.next();

    }

    public void writeData(Formatter f) {
        f.format("%s",toString());
    }

    public int getLegNumber() {
        return legNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }
}
