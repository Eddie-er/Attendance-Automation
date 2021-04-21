package sample.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO implements IStudentDAO {
    private DBConnector dbConnector;

    public StudentDAO() {
        dbConnector = new DBConnector();
    }

    /**
     * Gets all the students from the DB
     * @return A list with all the students
     * @throws SQLException
     */
    @Override
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

    /**
     * Gets all the students belonging to a specific class
     * @param selectedClass: Selected class in the gui
     * @return A list of students
     * @throws SQLException
     */
    @Override
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

    /**
     * Used for updating the gui in the view, after a student has been marked absent or present
     * @param StudentID
     * @return new absent percentage
     */
    @Override
    public double getNewAttendancePercentage(int StudentID) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT Student.Attendance FROM Student WHERE Student.StudentID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, StudentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double newAttendancePercentage = resultSet.getDouble("Attendance");
                return newAttendancePercentage;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Double.parseDouble(null);
    }

    /**
     * Gets a class belonging to the selected student
     * @param student
     * @return a class to the student
     */
    @Override
    public List<Classes> getClassFromStudent(Student student) {
        List<Classes> classesFromStudent = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection()) {

            Integer ClassID = student.getClassID();
            String query = "SELECT * FROM Classes, Student WHERE Classes.ClassID = Student.ClassID AND Student.ClassID = " + ClassID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Classes classes = new Classes(
                        resultSet.getInt("ClassID"),
                        resultSet.getString("ClassName"),
                        resultSet.getString("Education")
                );
                classesFromStudent.add(classes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classesFromStudent;
    }

    /**
     * Adds a new attendance in the DB where the student is present
     * @param attendance
     */
    @Override
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

    /**
     * Adds a new attendance in the DB where the student is absent
     * @param attendance
     */
    @Override
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

    /**
     * Gets the attendances from a specific student
     * @param student
     * @return a list with attendances from a student
     */
    @Override
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

    /**
     * Checks if a students has already been registered
     * @param StudentID
     * @param date
     * @return true if a student has been registered for the date
     */
    @Override
    public boolean checkExistingAttendance(int StudentID, Date date) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT Attendance.StudentID, Attendance.Date FROM Attendance JOIN Student ON Student.StudentID = Attendance.StudentID WHERE Attendance.StudentID = ? AND Date = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, StudentID);
            preparedStatement.setDate(2, date);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * Updating the absent percentage, which is calculated in the controller
     * @param StudentID
     * @param attendancePercentage
     */
    @Override
    public void updateAttendancePercentage(int StudentID, double attendancePercentage) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "UPDATE Student SET Student.Attendance = ? WHERE Student.StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDouble(1, attendancePercentage);
            preparedStatement.setInt(2, StudentID);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Gets a list with all the days a student has been
     * @param StudentID
     * @return a list of absent dates
     */
    @Override
    public List<LocalDate> getAbsentDays(int StudentID) {
        List<LocalDate> daysAbsent = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT Date FROM Attendance WHERE IsPresent = 'False' AND Attendance.StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, StudentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocalDate date = resultSet.getDate("Date").toLocalDate();
                daysAbsent.add(date);
            }
            return  daysAbsent;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
