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

public class Vehicle implements MyIO {
    private String rego;

    public Vehicle(String regoInput) {
        this.rego=regoInput;
    }

    public Vehicle() {
        this("");
    }

    public String toString() {
        String s="";
        return s;
    }

    public String getRego() {
        return rego;
    }

    public void readData(Scanner s) {

    }

    public void writeData(Formatter f) {

    }
}
