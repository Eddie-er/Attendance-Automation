package sample.BLL;

import sample.BE.Student;
import sample.DAL.IStudentDAO;
import sample.DAL.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentManager {

    private IStudentDAO studentDAO = new StudentDAO();

    public StudentManager() {
    }


    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }
}
