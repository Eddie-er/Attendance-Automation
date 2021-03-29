package sample.BE;

public class Student {
    private int StudentID;
    private String FirstName;
    private String LastName;
    private String Email;
    private int ClassID;
    private double Attendance;

    public Student(int studentID, String firstName, String lastName, String email, int classID, double attendance) {
        StudentID = studentID;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        ClassID = classID;
        Attendance = attendance;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public double getAttendance() {
        return Attendance;
    }

    public void setAttendance(double attendance) {
        Attendance = attendance;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentID=" + StudentID +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", ClassID=" + ClassID +
                ", Attendance=" + Attendance +
                '}';
    }
}
