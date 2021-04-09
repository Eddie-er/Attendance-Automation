package sample.BLL;

import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;
import sample.DAL.StudentDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentManager {

    private StudentDAO studentDAO;


    public StudentManager() {
        studentDAO = new StudentDAO();
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    public List<Student> getStudentsInClass(Classes selectedClass) throws SQLException {
        return studentDAO.getStudentsInClass(selectedClass);
    }

    public void studentIsPresent(Attendance attendance) {
        studentDAO.studentIsPresent(attendance);
    }

    public void studentIsAbsent(Attendance attendance) {
        studentDAO.studentIsAbsent(attendance);
    }

    public List<Attendance> getAttendanceFromStudent(Student student) throws SQLException {
        return studentDAO.getAttendanceFromStudent(student);
    }
}
