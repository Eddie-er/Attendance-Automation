package sample.BE;

public class Classes {
    private int ClassID;
    private String ClassName;
    private String Education;

    public Classes(int classID, String className, String education) {
        ClassID = classID;
        ClassName = className;
        Education = education;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "ClassID=" + ClassID +
                ", ClassName='" + ClassName + '\'' +
                ", Education='" + Education + '\'' +
                '}';
    }
}
