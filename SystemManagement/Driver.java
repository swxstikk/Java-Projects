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

public class Driver extends Employee {

    private int license;
    private String status;

    public Driver(int licenseInput, String statusInput) {
        this.license = licenseInput;
        this.status = statusInput;
    }

    public Driver() {
        this(0, "");
    }


    @Override
    public String toString() {
        String s = "Driver: ";
        s += getENumber() + "," + getFname() + "," + getIname() + "," + getDob() + "," + getAddresses()
                + "," + getDob() + "," + getPostcode() + "," + license + "," + status;
        return s;
    }

    public void readData(Scanner s) {
        super.readData(s);
        license = s.nextInt();
        status = s.next();
    }

        public void writeData (Formatter f){
            f.format("%s\n",toString());
        }

}

