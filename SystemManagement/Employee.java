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

public abstract class Employee implements MyIO {

    private int eNumber;
    private String fname;
    private String iname;
    private String dob;
    private String addresses;
    private int postcode;

    public Employee(int eNum, String fn, String inameInput, String dobInput, String address, int postcodeInput) {
        this.eNumber = eNum;
        this.fname = fn;
        this.iname = inameInput;
        this.dob = dobInput;
        this.addresses = address;
        this.postcode = postcodeInput;
    }

    public Employee() {
        this(0, "", "", "", "", 0);
    }

    public int getENumber() {
        return eNumber;
    }
    public String getFname() {
        return fname;
    }
    public String getIname() {
        return iname;
    }
    public String getDob() {
        return dob;
    }
    public String getAddresses() {
        return addresses;
    }
    public int getPostcode() {
        return postcode;
    }

    @Override
    public String toString() {
        String s = " ";
        s += eNumber + "," + fname + "," + iname + "," + dob + "," + addresses + "," + postcode;
        return s;
    }

    public void readData(Scanner s) {
        eNumber = s.nextInt();
        fname = s.next();
        iname = s.next();
        dob = s.next();
        addresses = s.next();
        postcode = s.nextInt();
    }

    public void writeData (Formatter f){
        f.format("%s\n",toString());
    }

}