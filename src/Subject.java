public class Subject {

    private String code, name, description, preRequisites; //subject code, name, description and pre-requisites
    private int credit, classTime, labTime; //subject credits, class and lab timings.


    public Subject() {

        // creating a default empty constructor to avoid system to generate null values for the data members.
        this("","",0,"","",0,0);

    }

    public Subject(String code, String name, int credit, String description, String preRequisites, int classTime, int labTime) {

        // assigning values to all data members

        this.code = code;
        this.name = name;
        this.credit = credit;
        this.description = description;
        this.preRequisites = preRequisites;
        this.classTime = classTime;
        this.labTime = labTime;

    }


    // defining the public access and update methods which returns and updates value of private data members.

    public String getCode() {
        return code;
    }


    public void setCode(String newCode) {
        this.code = newCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name=newName;
    }


    public int getCredit() {
        return credit;
    }


    public void setCredit(int credit) {
        this.credit = credit;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getPreRequisites() {
        return preRequisites;
    }


    public void setPreRequisites(String preRequisites) {
        this.preRequisites = preRequisites;
    }


    public int getClassTime() {
        return classTime;
    }


    public void setClassTime(int classTime) {
        this.classTime = classTime;
    }


    public int getLabTime() {
        return labTime;
    }


    public void setLabTime(int labTime) {
        this.labTime = labTime;
    }

}
