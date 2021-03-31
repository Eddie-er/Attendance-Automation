package sample.GUI.Model;

import sample.BE.MockBE.StudentMock;

public class StudentAttendanceModelMock {

    private static StudentAttendanceModelMock instance = null;

    private StudentAttendanceModelMock() {
    }

    public static StudentAttendanceModelMock getInstance() {
        if (instance == null)
            instance = new StudentAttendanceModelMock();

        return instance;
    }

    public StudentMock getLoggedInStudent() {
        return loggedInStudentMock;
    }


    public void setLoggedInStudent(StudentMock loggedInStudentMock) {
        this.loggedInStudentMock = loggedInStudentMock;
    }

    private StudentMock loggedInStudentMock = null;

}
