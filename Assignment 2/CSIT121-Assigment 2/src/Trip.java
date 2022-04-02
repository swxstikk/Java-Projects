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
import java.util.ArrayList;

public class Trip implements MyIO {
    private int tripNumber;
    private String tripDate;
    private int eNumber;
    private String rego;
    private ArrayList<TripLeg> tripLegs;

    public Trip(int tripNumberInput, String date, int eNum, String regoInput) {
        this.tripNumber=tripNumberInput;
        this.tripDate=date;
        this.eNumber=eNum;
        this.rego=regoInput;
        this.tripLegs= new ArrayList<>(0);
    }

    public Trip() {
        this(0,"",0,"");
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
