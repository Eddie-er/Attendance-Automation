package sample.BLL;

import javafx.collections.ObservableList;
import sample.BE.StudentMock;
import sample.DAL.MockData.StudentMockDAL;

public class StudentBLLManagerMock {
    private StudentMockDAL studentMockDAL = new StudentMockDAL();


    public StudentBLLManagerMock() {
        this.studentMockDAL = studentMockDAL;
    }

    public ObservableList<StudentMock> loadStudents() throws Exception {
        return StudentMockDAL.loadStudents();
    }
}
