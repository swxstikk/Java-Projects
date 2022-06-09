/*------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 3
-------------------------------------------------------*/

import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer implements MyFileIO {

    private String code;
    private String name;
    private String address;
    private String phone;

    public Customer(String code, String name, String address, String phone) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Customer() {}

    public void readData(Scanner s) {
        try {
            code = s.next();
            name = s.next();
            address = s.next();
            phone = s.next();
        } catch(InputMismatchException e)  {
            System.out.printf("%sWrong input type "+e);
            s.next(); //
        }
    }

    public void writeData(Formatter f) {
        f.format("%s\n", this);
    }

    @Override
    public String toString() {
        String s = "Customer: ";
        s+= "Code: " + code;
        s+= "Name: " + name;
        s+= "Address: " + address;
        s+= "Phone: " + phone;
        return s;
    }

    public String displayInformation() {
        String s = "Customer Code: " + code + "\nName: " + name + "\nAddress: " + address + "\nPhone: " + phone;
        return s;
    }

    public String getCode() {
        return code;
    }
}
