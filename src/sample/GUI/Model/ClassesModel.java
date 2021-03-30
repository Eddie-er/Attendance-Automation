package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Classes;
import sample.BLL.ClassesManager;

import java.sql.SQLException;

public class ClassesModel {

    private ObservableList<Classes> allClasses = FXCollections.observableArrayList();
    private ClassesManager classesManager;

    public ClassesModel() {
        classesManager = new ClassesManager();
    }

    public ObservableList<Classes> getAllClasses() throws SQLException {
        allClasses = FXCollections.observableArrayList();
        allClasses.addAll(classesManager.getAllClasses());
        return allClasses;
    }
}
