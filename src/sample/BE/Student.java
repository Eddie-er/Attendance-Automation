package sample.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private int StudentID;
    private StringProperty FirstName;
    private StringProperty LastName;
    private String Email;
    private int ClassID;
    private double Attendance;

    public Student(int studentID, String firstName, String lastName, String email, int classID, double attendance) {
        StudentID = studentID;
        this.FirstName = new SimpleStringProperty(firstName);
        this.LastName = new SimpleStringProperty(lastName);
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
        return FirstName.get();
    }

    public StringProperty firstNameProperty() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName.set(firstName);
    }

    public StringProperty lastNameProperty() {
        return LastName;
    }

    public String getLastName() {
        return LastName.get();
    }

    public void setLastName(String lastName) {
        this.LastName.set(lastName);
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
