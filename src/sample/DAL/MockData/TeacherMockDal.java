package sample.DAL.MockData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Teacher;
import sample.GUI.Model.StudentAttendanceModel;

public class TeacherMockDal {

    ObservableList<Teacher> allTeachers;

    public ObservableList<Teacher> getAllTeachers() {

        ObservableList<Teacher> allTeachers = FXCollections.observableArrayList();

        Teacher t1 = new Teacher("Hr.", "Lærer");
        allTeachers.add(t1);
        return allTeachers;
    }

}
