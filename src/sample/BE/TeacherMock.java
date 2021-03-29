package sample.BE;

import javafx.beans.property.SimpleStringProperty;

public class TeacherMock {

    private String name;
    private String lastName;
    private String currentClass;
    private String education;
    private int semester;
    private int classYear;


    public TeacherMock(String name, String lastName ){
        setName(name);
        setLastName(lastName);
    }

    public TeacherMock(String currentClass, int semester, int classYear, String education)
    {
        setCurrentClass(currentClass);
        setSemester(semester);
        setClassYear(classYear);
        setEducation(education);
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
