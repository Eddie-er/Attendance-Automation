package sample.DAL;

import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IStudentDAO {
    List<Student> getAllStudents() throws SQLException;

    List<Student> getStudentsInClass(Classes selectedClass) throws SQLException;

    double getNewAttendancePercentage(int StudentID);

    List<Classes> getClassFromStudent(Student student);

    void studentIsPresent(Attendance attendance);

    void studentIsAbsent(Attendance attendance);

    List<Attendance> getAttendanceFromStudent(Student student);

    boolean checkExistingAttendance(int StudentID, Date date);

    void updateAttendancePercentage(int StudentID, double attendancePercentage);

    List<LocalDate> getAbsentDays(int StudentID);
}
