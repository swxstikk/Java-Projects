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

    public Trip(int tripNumberInput, String date, int eNum, String regoInput, ArrayList<TripLeg>list) {
        this.tripNumber=tripNumberInput;
        this.tripDate=date;
        this.eNumber=eNum;
        this.rego=regoInput;
        this.tripLegs= list;
    }

    public Trip() {
        this(0,"",0,"",new ArrayList<TripLeg>(0));
    }

    @Override
    public String toString() {
        String s = tripNumber + "," + tripDate + "," + eNumber + "," + rego + "\n" + getTripLegs().size() + "\n";
        for (TripLeg t : tripLegs) {
            s += t.getLegNumber() + "," + t.getDeparture() + "," + t.getDestination() + "\n";
        }
        return s;
    }

    public String getTripDate() {
        return tripDate;
    }

    public ArrayList<TripLeg> getTripLegs() {
        return tripLegs;
    }

    public void readData(Scanner s) {
        tripNumber = s.nextInt();
        tripDate = s.next();
        eNumber = s.nextInt();
        rego = s.next();
        int totalTrips = s.nextInt();
        for(int i=0; i<totalTrips; i++) {
            TripLeg tripLeg = new TripLeg();
            tripLeg.readData(s);
            tripLegs.add(tripLeg);
        }
    }

    public void writeData(Formatter f) {
        f.format("%s",toString());
    }
}
