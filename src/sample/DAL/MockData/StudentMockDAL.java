package sample.DAL.MockData;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Student;

import java.util.HashMap;


public class StudentMockDAL {
    public ObservableList<Student> getAllStudents() {
        return allStudents;
    }

    ObservableList<Student> allStudents;

    public static ObservableList<Student> loadStudents() {
        return getStudents();
    }

    /**
     * @return A list of students
     */
    static ObservableList<Student> getStudents() {
        ObservableList<Student> allStudents = FXCollections.observableArrayList();

        Student s1 = new Student(2, "Mathias", "Kristensen", "CSe_20A", "Datamatiker", 12, "redhead");
        allStudents.add(s1);
        Student s2 = new Student(2, "Haraldur", "Jóhannesson", "CSe_20A", "Datamatiker", 67, "oklahoma");
        allStudents.add(s2);
        Student s3 = new Student(2, "Trine", "Knudsen", "CSe_20A", "Datamatiker", 6,"snack");
        allStudents.add(s3);
        Student s4 = new Student(2, "Julian", "Petersen", "CSe_20A", "Datamatiker", 1,"alabama");
        allStudents.add(s4);
        Student s5 = new Student(2, "Christian", "Hussmann", "CSe_20A", "Datamatiker", 34,"skat");
        allStudents.add(s5);
        return allStudents;
    }
}
