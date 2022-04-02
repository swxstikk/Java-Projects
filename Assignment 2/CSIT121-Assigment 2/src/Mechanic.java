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

public class Mechanic extends Employee {

    private String qualNum;
    private String experience;

    public Mechanic(String qualNumInput, String expInput) {
        this.qualNum=qualNumInput;
        this.experience=expInput;
    }

    public Mechanic() {
        this("","");
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
