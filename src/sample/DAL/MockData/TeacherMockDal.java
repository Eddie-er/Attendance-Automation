package sample.DAL.MockData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.MockBE.TeacherMock;

public class TeacherMockDal {

    ObservableList<TeacherMock> allTeacherMocks;

    public ObservableList<TeacherMock> getAllTeachers() {

        ObservableList<TeacherMock> allTeacherMocks = FXCollections.observableArrayList();

        TeacherMock t1 = new TeacherMock("Hr.", "Lærer");
        allTeacherMocks.add(t1);
        return allTeacherMocks;
    }

}
