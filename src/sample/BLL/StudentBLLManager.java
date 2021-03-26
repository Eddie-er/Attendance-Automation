package sample.BLL;

import javafx.collections.ObservableList;
import sample.BE.Student;
import sample.DAL.MockData.StudentMockDAL;

public class StudentBLLManager {
    private StudentMockDAL studentMockDAL = new StudentMockDAL();


    public StudentBLLManager() {
        this.studentMockDAL = studentMockDAL;
    }

    public ObservableList<Student> loadStudents() throws Exception {
        return StudentMockDAL.loadStudents();
    }
}
