package sample.BE.MockBE;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CurrentClassMock {

    private int semester;
    private String classYear;
    private String education;
    private ObservableList<String> subjects;


    public CurrentClassMock(int semester, String classYear, String education, ObservableList<String> subjects) {
        this.semester = semester;
        this.classYear = classYear;
        this.education = education;
        this.subjects = subjects;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public ObservableList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ObservableList<String> subjects) {
        this.subjects = subjects;
    }
}


