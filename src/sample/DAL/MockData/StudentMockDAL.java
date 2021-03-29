package sample.DAL.MockData;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.StudentMock;


public class StudentMockDAL {
    public ObservableList<StudentMock> getAllStudents() {
        return allStudentMocks;
    }

    ObservableList<StudentMock> allStudentMocks;

    public static ObservableList<StudentMock> loadStudents() {
        return getStudents();
    }

    /**
     * @return A list of students
     */
    static ObservableList<StudentMock> getStudents() {
        ObservableList<StudentMock> allStudentMocks = FXCollections.observableArrayList();

        StudentMock s1 = new StudentMock(2, "Mathias", "Kristensen", "CSe_20A", "Datamatiker", 12, "redhead");
        allStudentMocks.add(s1);
        StudentMock s2 = new StudentMock(2, "Haraldur", "Jóhannesson", "CSe_20A", "Datamatiker", 67, "oklahoma");
        allStudentMocks.add(s2);
        StudentMock s3 = new StudentMock(2, "Trine", "Knudsen", "CSe_20A", "Datamatiker", 6,"snack");
        allStudentMocks.add(s3);
        StudentMock s4 = new StudentMock(2, "Julian", "Petersen", "CSe_20A", "Datamatiker", 1,"alabama");
        allStudentMocks.add(s4);
        StudentMock s5 = new StudentMock(2, "Christian", "Hussmann", "CSe_20A", "Datamatiker", 34,"skat");
        allStudentMocks.add(s5);
        return allStudentMocks;
    }
}
