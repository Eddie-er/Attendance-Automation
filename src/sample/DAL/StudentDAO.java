package sample.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO  {
    private DBConnector dbConnector;

    public StudentDAO() {
        dbConnector = new DBConnector();
    }


    public List<Student> getAllStudents() throws SQLException {
        Connection connection = dbConnector.getConnection();
        List<Student> students = new ArrayList<>();

        String query = "SELECT StudentID, FirstName, LastName, Email, ClassID, Attendance, Password FROM Student";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Student student = new Student(
                    resultSet.getInt("StudentID"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Email"),
                    resultSet.getInt("ClassID"),
                    resultSet.getDouble("Attendance"),
                    resultSet.getString("Password")
            );
            students.add(student);
        }
        connection.close();
        statement.close();
        resultSet.close();

        return students;
    }

    public List<Student> getStudentsInClass(Classes selectedClass) throws SQLException {
        List<Student> studentsInClass = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection()) {

            Integer classID = selectedClass.getClassID();
            String query = "SELECT * FROM Student, Classes WHERE Student.ClassID = Classes.ClassID AND Classes.ClassID = " + classID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("StudentID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getInt("ClassID"),
                        resultSet.getDouble("Attendance"),
                        resultSet.getString("Password")
                );
                studentsInClass.add(student);
            }
            return studentsInClass;
        }
    }

    public void studentIsPresent(Attendance attendance) {
        String query = "INSERT INTO Attendance (isPresent, Date, StudentID) VALUES (?,?,?)";
        try (Connection connection = dbConnector.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, attendance.isPresent());
            preparedStatement.setDate(2, (java.sql.Date) attendance.getDate());
            preparedStatement.setInt(3, attendance.getStudentID());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void studentIsAbsent(Attendance attendance) {
        String query = "INSERT INTO Attendance (isPresent, Date, StudentID) VALUES (?,?,?)";
        try (Connection connection = dbConnector.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, attendance.isPresent());
            preparedStatement.setDate(2, attendance.getDate());
            preparedStatement.setInt(3, attendance.getStudentID());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Attendance> getAttendanceFromStudent(Student student) {
        List<Attendance> studentsAttendance = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection()) {

            Integer studentID = student.getStudentID();
            String query = "SELECT * FROM Attendance, Student WHERE Attendance.StudentID = Student.StudentID AND Student.StudentID = " + studentID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Attendance attendance = new Attendance(
                        resultSet.getInt("AttendanceID"),
                        resultSet.getBoolean("IsPresent"),
                        resultSet.getDate("Date"),
                        resultSet.getInt("StudentID")
                );
                studentsAttendance.add(attendance);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentsAttendance;
    }
}
