/*
------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 1
-------------------------------------------------------
*/
import java.util.ArrayList; // used for creating lists to store objects
import java.util.Scanner;

class StudentEnrolmentSystem {

    // creating empty lists to store objects later

    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private ArrayList<Enrolment> enrolments;


    public StudentEnrolmentSystem() {
        // initialization constructor which creates empty lists for all data members of the class.

        students = new ArrayList<Student>(0);
        subjects = new ArrayList<Subject>(0);
        enrolments = new ArrayList<Enrolment>(0);
    }

    public void addStudent(Student studentObject) {
        // checking if student already exists in the list. If not, we add it to the list.
        if(!students.contains(studentObject)) {
            students.add(studentObject);
        }
    }

    public void addSubject(Subject subjectObject) {
        // checking if subject already exists in the list. If not, we add it to the list.
        if(!subjects.contains(subjectObject)) {
            subjects.add(subjectObject);
        }
    }

    public void addEnrollment(Enrolment enrolmentObject) {
        // checking if enrolment record already exists in the list. if not, we add it to the list.
        if(!enrolments.contains(enrolmentObject)) {
            enrolments.add(enrolmentObject);
        }
    }

    public void findStudent() {
        // finding if given values corresponding to student attributes exists in our student list.

    }

    public void findSubject() {
        // finding if given values corresponding to subject attributes exists in our subject list.
    }

    public static void main(String[] args) {

        StudentEnrolmentSystem enrolmentObject = new StudentEnrolmentSystem();
        Student Swastik = new Student(7232408, "Swastik Satapathy", "26/10/2001", "ss7796@uowmail.edu.au", "Kooloobong" +
                "Villages", "+61 0493301202", "BSc Computer Science (766)");
        Subject CSIT121 = new Subject("CSIT121", "Object-Oriented Programming and Design", 6, "This subject teaches OOP"
                ,"CSIT111", 2, 2);
        enrolmentObject.addStudent(Swastik);
        enrolmentObject.addSubject(CSIT121);
        Scanner userInput = new Scanner(System.in); // using scanner for reading user's input

        System.out.println("1. Display all students");
        System.out.println("2. Display all subjects");
        System.out.println("3. Find a student");
        System.out.println("4. Find a subject");
        System.out.println("5. Add an enrolment");
        System.out.println("6. Display a student's enrolments");
        System.out.println("7. Exit");
        System.out.print("\nPlease select one from the menu: ");

        while(true) { // using a while loop so user can enter input values upto 7.
            int inputValue = userInput.nextInt();
            if(inputValue==7)
                break;
        /*
        Using switch to iterate through the above options.

        To-Do List:
        - Implement a system where the input will be asked until '7' is reached. (DONE)
        - Build the UML Diagram for the entire program.
        - Figure out the relationships between the 4 different classes for the UML.

        */
            switch (inputValue) {

                case 1:
                    // print out all the student details
                    System.out.println(enrolmentObject.students.toString());
                    break;

                case 2:
                    // print out all the subject details
                    System.out.println(enrolmentObject.subjects.toString());
                    break;

                case 3:
                    //finding a student
                    //this scanner will record user input for student number
                    int stdNumInput = userInput.nextInt();
                    if (enrolmentObject.students.contains(stdNumInput)) { // checking if student number exists in student list
                        System.out.println(enrolmentObject.students.get(stdNumInput).toString());
                    } else {
                        System.out.println("The student " + stdNumInput + " does not exist.");
                    }
                    break;

                case 4:
                    // finding a subject
                    // this scanner will record user input for subject number
                    int subNumInput = userInput.nextInt();
                    if (enrolmentObject.subjects.contains(subNumInput)) { // checking if subject number exists in subject list
                        System.out.println(enrolmentObject.subjects.get(subNumInput).toString());
                    } else {
                        System.out.println("The subject " + subNumInput + " does not exist.");
                    }
                    break;


                case 5:
                    // taking user input for student & subject to add into enrollment if not enrolled already.
                    int studentNum = userInput.nextInt();
                    String subjectNum = userInput.nextLine();
                    String enrolledDate= "26/03/2022";
                    Enrolment enrollmentObject = new Enrolment(studentNum, subjectNum, enrolledDate);
                    if(!enrolmentObject.enrolments.contains(studentNum)  && enrolmentObject.enrolments.contains(subjectNum)) {
                        enrolmentObject.enrolments.add(enrollmentObject);
                    } else {
                        System.out.println("The student " + studentNum + " has enrolled in the " +
                                "subject " + subjectNum + " already");
                    }
                    break;

                case 6:
                    // displaying a student's enrolment.
                    int studentId = userInput.nextInt();
                    if(enrolmentObject.enrolments.contains(studentId)) {
                        System.out.println(enrolmentObject.enrolments.get(studentId).toString());
                    } else {
                        System.out.println("The student " + studentId + " hasn't enrolled in a subject.");
                    }
                    break;

                case 7:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("nothing else matched!");
            }
            System.out.println(" "); // printing empty line for spacing according to assignment specifications.
            System.out.println("1. Display all students");
            System.out.println("2. Display all subjects");
            System.out.println("3. Find a student");
            System.out.println("4. Find a subject");
            System.out.println("5. Add an enrolment");
            System.out.println("6. Display a student's enrolments");
            System.out.println("7. Exit");
            System.out.print("\nPlease select one from the menu: ");
        }
    }

}
