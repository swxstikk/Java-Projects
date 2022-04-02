/*
------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 2
-------------------------------------------------------
*/

import java.util.Formatter;
import java.util.Scanner;
public class Employee implements MyIO {

    private int eNumber;
    private String fname;
    private String iname;
    private String dob;
    private String addresses;
    private int postcode;

    public Employee(int eNum, String fn, String inameInput, String dobInput, String address, int postcodeInput) {
        this.eNumber=eNum;
        this.fname=fn;
        this.iname=inameInput;
        this.dob=dobInput;
        this.addresses=address;
        this.postcode=postcodeInput;
    }

    public Employee() {
        this(0,"","","","",0);
    }

    public String toString() {
        String s="";
        return s;
    }

    public int geteNumber() {
        return eNumber;
    }

    public void readData(Scanner s) {

    }

    public void writeData(Formatter f) {

    }
}
