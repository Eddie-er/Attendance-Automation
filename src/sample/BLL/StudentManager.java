package sample.BLL;

import sample.BE.Classes;
import sample.BE.Student;
import sample.DAL.StudentDAO;

import java.sql.SQLException;
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
}
