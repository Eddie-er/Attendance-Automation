package sample.DAL.MockData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.CurrentClassMock;


public class ClassMockDAL {


    public static ObservableList<CurrentClassMock> loadClasses() {
        return getCurrentClasses();
    }

    /**
     * @return A list of subjects, which belongs to a class.
     */
    static ObservableList<String> getSubjects() {

        ObservableList<String> subjects = FXCollections.observableArrayList();
        subjects.add("SCO");
        subjects.add("DBOS");
        subjects.add("SDE");
        subjects.add("ITO");
        return subjects;

    }

    /**
     * @return A list of classes
     */
    static ObservableList<CurrentClassMock> getCurrentClasses() {
        ObservableList<CurrentClassMock> allCurrentClassMocks = FXCollections.observableArrayList();

        CurrentClassMock cc1 = new CurrentClassMock(2,"CSe_20A", "Datamatiker", getSubjects());
        allCurrentClassMocks.add(cc1);
        CurrentClassMock cc2 = new CurrentClassMock(2,"CSe_20B", "Datamatiker", getSubjects());
        allCurrentClassMocks.add(cc2);
        CurrentClassMock cc3 = new CurrentClassMock(4,"CSe_19A", "Datamatiker", getSubjects());
        allCurrentClassMocks.add(cc3);
        CurrentClassMock cc4 = new CurrentClassMock(4,"CSe_19B", "Datamatiker", getSubjects());
        allCurrentClassMocks.add(cc4);
        return allCurrentClassMocks;
    }

}
