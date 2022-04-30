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

public abstract class Vehicle implements MyIO {
    private String rego;

    public Vehicle(String data) {
        this.rego = data;
    }

    public Vehicle() {
        this("");
    }

    public String toString() {
        String s="";
        s+= rego;
        return s;
    }

    public String getRego() {
        return rego;
    }

    public void readData(Scanner s) {
        rego = s.next();
    }

    public void writeData(Formatter f) {
        f.format("%s\n",toString());
    }
}
