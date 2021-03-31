package sample.BLL.MockBLL;

import javafx.collections.ObservableList;
import sample.BE.MockBE.StudentMock;
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
