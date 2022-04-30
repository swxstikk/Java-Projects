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

    public Mechanic(String qualNum, String experience) {
        this.qualNum = qualNum;
        this.experience = experience;
    }

    public Mechanic() {
        this("", "");
    }

    @Override
    public String toString() {
        String s = "Mechanic: ";
        s+= getENumber() + "," + getFname() + "," + getIname() + "," + getDob() + "," + getAddresses()
                + "," + getDob() + "," + getPostcode() + "," + qualNum + "," + experience;
        return s;
    }

    @Override
    public void readData(Scanner s) {
        super.readData(s);
        qualNum = s.next();
        experience = s.next();
    }

        public void writeData(Formatter f) {
            f.format("%s\n",toString());
        }
    }
