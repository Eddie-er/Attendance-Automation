package sample.BLL;

import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;
import sample.DAL.IStudentDAO;
import sample.DAL.StudentDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentManager {

    private IStudentDAO IStudentDAO;


    public StudentManager() {
        IStudentDAO = new StudentDAO();
    }

    public List<Student> getAllStudents() throws SQLException {
        return IStudentDAO.getAllStudents();
    }

    public List<Student> getStudentsInClass(Classes selectedClass) throws SQLException {
        return IStudentDAO.getStudentsInClass(selectedClass);
    }

    public double getNewAttendancePercentage(int StudentID) {
        return IStudentDAO.getNewAttendancePercentage(StudentID);
    }

    public List<Classes> getClassFromStudent(Student student) {
        return IStudentDAO.getClassFromStudent(student);
    }

    public void studentIsPresent(Attendance attendance) {
        IStudentDAO.studentIsPresent(attendance);
    }

    public void studentIsAbsent(Attendance attendance) {
        IStudentDAO.studentIsAbsent(attendance);
    }

    public List<Attendance> getAttendanceFromStudent(Student student) throws SQLException {
        return IStudentDAO.getAttendanceFromStudent(student);
    }

    public boolean checkExistingAttendance(int StudentID, Date date) {
        return IStudentDAO.checkExistingAttendance(StudentID, date);
    }

    public void updateAttendancePercentage(int StudentID, double attendancePercentage) {
        IStudentDAO.updateAttendancePercentage(StudentID, attendancePercentage);
    }

    public List<LocalDate> getAbsentDays(int StudentID) {
        return IStudentDAO.getAbsentDays(StudentID);
    }

}
