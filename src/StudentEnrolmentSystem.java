import java.util.ArrayList; // used for creating lists to store objects
import java.util.Scanner;

class StudentEnrolmentSystem {

    // creating empty lists to store objects later

    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private ArrayList<Enrolment> enrolments;


    public StudentEnrolmentSystem() {
        // default constructor
    }

    public StudentEnrolmentSystem(ArrayList<Student> students, ArrayList<Subject> subjects, ArrayList<Enrolment> enrolments) {

        // assigning empty lists to data members
        this.students = students;
        this.subjects = subjects;
        this.enrolments = enrolments;
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
        Scanner userInput = new Scanner(System.in); // using scanner for reading user's input
        int inputValue = userInput.nextInt();

        System.out.print("1. Display all students");
        System.out.print("2. Display all subjects");
        System.out.print("3. Find a student");
        System.out.print("4. Find a subject");
        System.out.print("5. Add an enrolment");
        System.out.print("6. Display a student's enrolments");
        System.out.print("7. Exit");

        /*
        Using switch to iterate through the above options.

        To-Do List:
        - Implement a system where the input will be asked until '7' is reached.
        - Build the UML Diagram for the entire program.
        - Figure out the relationships between the 4 different classes for the UML.

        */
        switch(inputValue) {

            case 1:
                // print out all the student details
                System.out.println(enrolmentObject.students);
                break;

            case 2:
                // print out all the subject details
                System.out.println(enrolmentObject.subjects);
                break;

            case 3:
                // finding a student
                if(enrolmentObject.students.contains(userInput)) {
                    System.out.println(enrolmentObject.students);
                }
                break;

            case 4:
                // finding a subject
                if(enrolmentObject.subjects.contains(userInput)) {
                    System.out.println(enrolmentObject.subjects);
                }
                break;

            /*case 5:
            // adding an enrollment

            break;

            case 6:
            break;

            case 7:
            break;*/

            default:
                System.out.println("nothing else matched!");
        }


    }

}
