package sample.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.BE.Student;

import java.sql.*;
import java.util.ArrayList;
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

        String query = "SELECT StudentID, FirstName, LastName, Email, ClassID, Attendance FROM Student";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Student student = new Student(
                    resultSet.getInt("StudentID"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Email"),
                    resultSet.getInt("ClassID"),
                    resultSet.getDouble("Attendance")
            );
            students.add(student);
        }
        connection.close();
        statement.close();
        resultSet.close();

        return students;
    }
}
