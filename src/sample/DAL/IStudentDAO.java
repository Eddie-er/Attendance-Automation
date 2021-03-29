package sample.DAL;

import sample.BE.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    List<Student> getAllStudents() throws SQLException;
}
