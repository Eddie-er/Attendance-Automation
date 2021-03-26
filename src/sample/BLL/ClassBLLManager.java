package sample.BLL;

import javafx.collections.ObservableList;
import sample.BE.CurrentClass;
import sample.DAL.MockData.ClassMockDAL;

public class ClassBLLManager {
    private ClassMockDAL classMockDAL = new ClassMockDAL();

    public ClassBLLManager() {
        this.classMockDAL = classMockDAL;
    }

    public ObservableList<CurrentClass> loadClasses() throws Exception{
        return ClassMockDAL.loadClasses();
    }
}
