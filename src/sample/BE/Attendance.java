package sample.BE;

import java.util.Date;

public class Attendance {
    private int AttendanceID;
    private boolean IsPresent;
    private Date date;
    private int StudentID;

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public Attendance(int attendanceID, boolean isPresent, Date date) {
        AttendanceID = attendanceID;
        IsPresent = isPresent;
        this.date = date;
    }

    public Attendance(boolean isPresent, Date date, int studentID) {
        IsPresent = isPresent;
        this.date = date;
        StudentID = studentID;
    }

    public int getAttendanceID() {
        return AttendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        AttendanceID = attendanceID;
    }

    public boolean isPresent() {
        return IsPresent;
    }

    public void setPresent(boolean present) {
        IsPresent = present;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
