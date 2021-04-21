package sample.BLL;

import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;
import sample.DAL.StudentDAO;

import java.sql.Date;
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

    public List<Classes> getClassFromStudent(Student student) {
        return studentDAO.getClassFromStudent(student);
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

    public boolean checkExistingAttendance(int StudentID, Date date) {
        return studentDAO.checkExistingAttendance(StudentID, date);
    }

    public void updateAttendancePercentage(int StudentID, double attendancePercentage) {
        studentDAO.updateAttendancePercentage(StudentID, attendancePercentage);
    }

    public List<LocalDate> getAbsentDays(int StudentID) {
        return studentDAO.getAbsentDays(StudentID);
    }

}
