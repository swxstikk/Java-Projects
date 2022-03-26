/*
------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 1
-------------------------------------------------------
*/

public class Enrolment {
    private int number; //student number
    private String code, date; //subject code and enrolment date


    public Enrolment() {

        // creating a default empty constructor to avoid system to generate null values for the data members.
        this(0,"","");

    }
    public Enrolment(int numberInput, String codeInput, String dateInput) {

        // assigning values to all data members

        this.number=numberInput;
        this.code=codeInput;
        this.date=dateInput;
    }

    // defining the public access methods which returns value of private data members.

    public int getNumber() {
        return number;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    // defining public update methods that updates values of private data members.

    public void setNumber(int newNumber) {
        this.number=newNumber;
    }

    public void setCode(String newCode) {
        this.code=newCode;
    }

    public void setDate(String newDate) {
        this.date=newDate;
    }

    public String toString() {
        String e = "------------\n";

        e += "Enrolment details: " + number + code + date + "\n\n";

        /*
        'enrolments' arrayList is defined in another class(StudentEnrolmentSystem) Therefore, the error in the below lines of code.
        Commenting out the for loop temporarily until I get a clarification from the lecturer regarding my question.
            - Sent a mail to the lecturer on further clarity regarding the addition of data members and methods apart
            from the ones mentioned in the assignment document.
            - Test the toString override method in the StudentEnrolmentSystem class to check if the output is
            properly formatted according to assignment specifications.

        for(Enrolment enrolment:enrolments)
            e+=enrolments;

        return e+"------------";
    }
        */
        return e;
    }

}
