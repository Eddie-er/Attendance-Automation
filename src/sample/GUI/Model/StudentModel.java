package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;
import sample.BLL.StudentManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentModel {

    private ObservableList<Student> allStudents = FXCollections.observableArrayList();
    private ObservableList<Student> allStudentsInClass = FXCollections.observableArrayList();
    private ObservableList<Attendance> attendanceFromStudent = FXCollections.observableArrayList();
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

    public ObservableList<Attendance> getAttendanceFromStudent(Student student) throws SQLException {
        attendanceFromStudent = FXCollections.observableArrayList();
        attendanceFromStudent.addAll(studentManager.getAttendanceFromStudent(student));
        return attendanceFromStudent;
    }

    public List<Attendance> getAllAttendances() throws SQLException {
        return studentManager.getAllAttendances();
    }

    public void studentIsPresent(Attendance attendance) {
        studentManager.studentIsPresent(attendance);
    }

    public void studentIsAbsent(Attendance attendance) {
        studentManager.studentIsAbsent(attendance);
    }

    public boolean checkExistingAttendance(int StudentID, Date date) {
        return studentManager.checkExistingAttendance(StudentID, date);
    }

    public void updateAttendancePercentage(int StudentID, double attendancePercentage) {
        studentManager.updateAttendancePercentage(StudentID, attendancePercentage);
    }

    public List<LocalDate> getAbsentDays (int StudentID) {
        return studentManager.getAbsentDays(StudentID);
    }
}
