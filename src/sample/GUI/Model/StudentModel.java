package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;
import sample.BLL.StudentManager;

import java.sql.SQLException;
import java.time.LocalDate;

public class StudentModel {

    private ObservableList<Student> allStudents = FXCollections.observableArrayList();
    private ObservableList<Student> allStudentsInClass = FXCollections.observableArrayList();
    private StudentManager studentManager;

    public StudentModel() {
        studentManager = new StudentManager();
    }

    public ObservableList<Student> getAllStudents() throws SQLException {
        allStudents = FXCollections.observableArrayList();
        allStudents.addAll(studentManager.getAllStudents());
        return allStudents;
    }

    public ObservableList<Student> getStudentsInClass(Classes selectedClass) throws SQLException {
        allStudentsInClass = FXCollections.observableArrayList();
        allStudentsInClass.addAll(studentManager.getStudentsInClass(selectedClass));
        return allStudentsInClass;
    }

    public void studentIsPresent(Attendance attendance) {
        studentManager.studentIsPresent(attendance);
    }

    public void studentIsAbsent(Attendance attendance) {
        studentManager.studentIsAbsent(attendance);
    }
}
