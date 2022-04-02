/*
------------------------------------------------------
My name: Swastik Satapathy
My student number: 7232408
My course code: CSIT121
My email address: ss7796@uowmail.edu.au
Assignment number: 1
-------------------------------------------------------
*/
import java.util.ArrayList; // used for creating lists to store objects.
import java.util.Scanner; // used for taking user inputs.

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
        if (!students.contains(studentObject)) {
            students.add(studentObject);
        } else {
            System.out.println("The student " + studentObject.getNumber() + " already exists.");
        }
    }

    public void addSubject(Subject subjectObject) {
        // checking if subject already exists in the list. If not, we add it to the list.
        if (!subjects.contains(subjectObject)) {
            subjects.add(subjectObject);
        } else {
            System.out.println("The subject "+subjectObject.getCode()+" already exists.");
        }
    }

    public void addEnrollment(Enrolment enrolmentObject) {
            enrolments.add(enrolmentObject);
            }

    public boolean findStudent(Student studentObject) {
        // finding if given values corresponding to student attributes exists in our student list.
        boolean found=false;
        for(Student st: students)// traversing the arrayLists and comparing values to check if the student exists
            if(st.getNumber()==studentObject.getNumber()) {
                found=true;
            System.out.print(st.toString());
            }
        return found;
        }

    public boolean findSubject(Subject subjectObject) {
        // finding if given values corresponding to subject attributes exists in our subject list.
        boolean found=false;
        for(Subject sb: subjects) // traversing the arrayLists and comparing values to check if the subject exists
            if(sb.getCode().equals(subjectObject.getCode())) {
                found=true;
                System.out.print(sb.toString());
            }
        return found;
        }

    public boolean findEnrolment(Enrolment enrolmentObject) {
        // finding an enrolment when given student number and subject code
        boolean found = false;
        for (Enrolment en : enrolments) { // traversing the arrayLists and comparing values till we find if the enrolment exists
            if (en.getNumber() == enrolmentObject.getNumber() && en.getCode().equals(enrolmentObject.getCode())) {
                found = true;
                System.out.println("The student "+en.getNumber() +" has enrolled in the subject "
                        +en.getCode() +"\n" + " already.\n");
            }
        }
        return found;
    }

    public boolean printEnrolment(Enrolment enrol) {
        // printing the enrolment arrayList contents
        boolean found=false;
        for(Enrolment e: enrolments)
            if(e.getNumber() == enrol.getNumber()) {
                found=true;
                System.out.print(e.toString());
            }
        System.out.print("\n");
        return found;
    }

    private void menu() {
        System.out.println("1. Display all students");
        System.out.println("2. Display all subjects");
        System.out.println("3. Find a student");
        System.out.println("4. Find a subject");
        System.out.println("5. Add an enrolment");
        System.out.println("6. Display a student's enrolments");
        System.out.println("7. Exit");
        System.out.print("\nPlease select one from the menu: ");
    }

    public static void main(String[] args) {
        StudentEnrolmentSystem mainClass = new StudentEnrolmentSystem();
        // creating the Student objects and assigning values given in the assignment pdf.
        Student Albert = new Student(100100, "Albert", "13/10/1965", "a100@uni.edu.au",
                "12 Robert street Woonona NSW 2517", "12345678", "Bacholar of CS");
        Student Alvin = new Student(100110, "Alvin", "13/10/1977", "a110@uni.edu.au",
                "56 Marlo road Wollongong NSW 2500", "11223344", "Bacholar of CS");
        Student Alice = new Student(100120, "Alice", "17/06/1973", "a120@uni.edu.au",
                "43 Collaery road Russell Vale NSW 2517", "12345677", "Bachelor of CS");
        Student Bob = new Student(100150, "Bob", "02/07/1960", "a150@uni.edu.au",
                "23 Kendall street Wollongong NSW 2500", "12345688", "Bachelor of CS");
        Student Carl = new Student(100200, "Carl", "02/02/1967", "c200@uni.edu.au",
                "44 Mount Keira road West Wollongong NSW 2500", "21345687", "Bachelor of IT");
        Student Douglas = new Student(100250, "Douglass", "14/04/1983", "d250@uni.edu.au",
                "78 Uralba street West Wollongong NSW 2500", "010123456", "Bachelor of CS");
        Student Peter = new Student(100101, "Peter", "13/11/1976", "p101@uni.edu.au",
                "77 Gipps road Wollongong NSW 2500", "0102123456", "Bachelor of Physics");
        Student Ami = new Student(100103, "Ami", "12/09/1985", "a103@uni.edu.au",
                "51 Mackie street Coniston NSW 2500", "0242211234", "Bachelor of Science");
        Student Wendy = new Student(100107, "Wendy", "12/09/1988", "w107@uni.edu.au",
                "41 Wall street Wollongong NSW 2500", "0281234567", "Bachelor of Science");
        Student Michael = new Student(100109, "Michael", "12/09/1990", "m109@uni.edu.au",
                "112 Smith road Wollongong NSW 2500", "0242201234", "Bachelor of CS");
        Student Angela = new Student(100125, "Angela", "20/11/1990", "a125@uni.edu.au",
                "23 Gibsons road Figtree NSW 2525", "0201123456", "Bachelor of Education");
        Student Robert = new Student(100105, "Robert", "15/01/1986", "r105@uni.edu.au",
                "66 Risely road Figtree NSW 2525", "0202213123", "Bachelor of CS");
        Student Aban = new Student(100136, "Aban", "15/01/1990", "a136@uni.edu.au",
                "187 Princes Highway Wollongong NSW 2500", "0103123456", "Bachelor of IT");
        Student Eadger = new Student(100187, "Eadger", "07/04/1986", "e187@uni.edu.au",
                "73 Ocean street Wollongong NSW 2500", "0104123321", "Bachelor of Science");
        Student Swastik = new Student(7232408, "Swastik Satapathy", "26/10/2001", "ss7796@uowmail.edu.au",
                "Kooloobong Villages", "+61 0493301202", "BSc Computer Science (766)");

        // creating the Subject objects and assigning values given in the assignment pdf.
        Subject CSCI213 = new Subject("CSCI213", "Java Programming & Object Oriented Design", 6,
                "This subject provides an introduction to the Java language and some of its standard " +
                        "class libraries, you will have experience with object oriented design " +
                        "and implementation techniques",
                "CSCI124 or CSCI121 or CSCI192", 3, 2);
        Subject CSCI124 = new Subject("CSCI124", "Applied Programming", 6,
                "This subject develops a thorough understanding of program design using data structures. " +
                        "It extends CSCI114 and presents pointers, dynamic memory management and exception handling",
                "CSCI114 & CSCI103 or CSCI111 & CSCI103", 4, 2);
        Subject CSCI235 = new Subject("CSCI235", "Database system", 6,
                "This subject investigates three major areas of modern database systems:" +
                        " 1. design of relational databases 2. programming of relational databases",
                "CSIT115", 3, 2);
        Subject CSIT115 = new Subject("CSIT115", "Data management and security", 6,
                "The subject investigates three major areas of modern data management systems:" +
                        " data modelling, data processing, and data security.",
                "", 2, 2);
        Subject CSIT111 = new Subject("CSIT111", "Programming Fundamentals", 6,
                "The broad aim of this subject is to develop in students an understanding " +
                        "of the fundamental principles of programming.",
                "", 2, 2);
        Subject CSIT121 = new Subject("CSIT121", "Object oriented design and programming", 6,
                "The aims of this subject are to consolidate and extend student's knowledge and skills" +
                        " in structured programming and to develop their understanding and " +
                        "practice of object oriented programming.",
                "CSIT111 OR ENGG100", 2, 2);
        Subject CSCI251 = new Subject("CSCI251", "Advanced programming", 6,
                "This subject develops a thorough understanding of advanced programming features, " +
                        "and how to implement them in modern C++.",
                "CSIT121", 3, 2);

        // adding all the student and subject objects to their respective arrayLists below
        mainClass.addStudent(Albert);
        mainClass.addStudent(Alvin);
        mainClass.addStudent(Alice);
        mainClass.addStudent(Bob);
        mainClass.addStudent(Carl);
        mainClass.addStudent(Douglas);
        mainClass.addStudent(Peter);
        mainClass.addStudent(Ami);
        mainClass.addStudent(Wendy);
        mainClass.addStudent(Michael);
        mainClass.addStudent(Angela);
        mainClass.addStudent(Robert);
        mainClass.addStudent(Aban);
        mainClass.addStudent(Eadger);
        mainClass.addStudent(Swastik);
        mainClass.addSubject(CSCI213);
        mainClass.addSubject(CSCI124);
        mainClass.addSubject(CSCI235);
        mainClass.addSubject(CSIT115);
        mainClass.addSubject(CSIT111);
        mainClass.addSubject(CSIT121);
        mainClass.addSubject(CSCI251);

        Scanner userInput = new Scanner(System.in); // using scanner for reading user's input
        mainClass.menu();
        boolean done = false;
        while(!done) { // using a while loop so user can enter input values upto 7.
            int inputValue = userInput.nextInt();
        // Using switch to iterate through menu()
            switch (inputValue) {
                case 1:
                    // print out all the student details
                    System.out.println(mainClass.students.toString());
                    mainClass.menu();
                    break;
                case 2:
                    // print out all the subject details
                    System.out.println(mainClass.subjects.toString());
                    mainClass.menu();
                    break;
                case 3:
                    // finding a student, this scanner will record user input for student number.
                    System.out.print("Input a student number: ");
                    int stdNumInput = userInput.nextInt();
                    Student newStudent = new Student(stdNumInput); //storing user-input in object
                    if(!mainClass.findStudent(newStudent)) {
                        System.out.println("The student " +stdNumInput +" does not exist.\n");
                    }
                    mainClass.menu();
                    break;
                case 4:
                    // finding a subject, this scanner will record user input for subject number.
                    System.out.print("Input a subject code: ");
                    String subNumInput = userInput.next();
                    Subject newSubject = new Subject(subNumInput); //storing user-input in object
                    if(!mainClass.findSubject(newSubject)) {
                        System.out.println("The subject "+subNumInput+" does not exist.\n");
                    }
                    mainClass.menu();
                    break;
                case 5:
                    // taking user input for student & subject to add into enrollment if not enrolled already.
                    System.out.print("Input a student number: ");
                    int studentNum = userInput.nextInt();
                    System.out.print("Input a subject code: ");
                    String subjectCode = userInput.next();
                    Enrolment enrol = new Enrolment(studentNum, subjectCode, "01/03/2022");
                    if(!mainClass.findEnrolment(enrol)) {
                        mainClass.addEnrollment(enrol);
                        System.out.println("A new enrolment for the student " + enrol.getNumber() + " on the subject " +
                                enrol.getCode() + "\n" + " has been added to the list.\n");
                    }
                    mainClass.menu();
                    break;
                case 6:
                    // displaying a student's enrolment.
                    System.out.print("Input a student number: ");
                    int studentId = userInput.nextInt();
                    if(!mainClass.printEnrolment(new Enrolment(studentId))) {
                        System.out.println("The student "+studentId +" hasn't enrolled in a subject.\n");
                    }
                    mainClass.menu();
                    break;
                case 7:
                    System.out.println("Bye");
                    done = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}