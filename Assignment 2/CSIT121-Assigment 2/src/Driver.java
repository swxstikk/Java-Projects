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

public class Driver {

    private int license;
    private String status;

    public Driver(int licenseInput,String statusInput) {
        this.license=licenseInput;
        this.status=statusInput;
    }

    public Driver() {
        this(0,"");
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
