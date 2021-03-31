package sample.BLL.MockBLL;

import javafx.collections.ObservableList;
import sample.BE.MockBE.CurrentClassMock;
import sample.DAL.MockData.ClassMockDAL;

public class ClassBLLManagerMock {
    private ClassMockDAL classMockDAL = new ClassMockDAL();

    public ClassBLLManagerMock() {
        this.classMockDAL = classMockDAL;
    }

    public ObservableList<CurrentClassMock> loadClasses() throws Exception{
        return ClassMockDAL.loadClasses();
    }
}
