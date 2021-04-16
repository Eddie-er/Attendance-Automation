package sample.GUI.Model;

import sample.BE.Student;

public class StudentLoggedInModel {

    private StudentModel studentModel = new StudentModel();
    private static StudentLoggedInModel instance = null;

    private StudentLoggedInModel() {
    }

    public static StudentLoggedInModel getInstance() {
        if (instance == null)
            instance = new StudentLoggedInModel();

        return instance;
    }

    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public void setLoggedInStudent(Student loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }

    private Student loggedInStudent = null;

}
