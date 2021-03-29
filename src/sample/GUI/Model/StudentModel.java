package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Student;
import sample.BLL.StudentManager;

import java.sql.SQLException;

public class StudentModel {

    private ObservableList<Student> allStudents = FXCollections.observableArrayList();
    private StudentManager studentManager;

    public StudentModel() {
        this.studentManager = new StudentManager();
    }


    public ObservableList<Student> getAllStudents() throws SQLException {
        allStudents = FXCollections.observableArrayList();
        allStudents.addAll(studentManager.getAllStudents());
        return getAllStudents();
    }
}
