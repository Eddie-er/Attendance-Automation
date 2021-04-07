package sample.BE;

import java.sql.Date;

public class Attendance {
    private int AttendanceID;
    private boolean IsPresent;
    private java.sql.Date date;
    private int StudentID;

    public Attendance(int attendanceID, boolean isPresent, Date date, int studentID) {
        AttendanceID = attendanceID;
        IsPresent = isPresent;
        this.date = date;
        StudentID = studentID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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


    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }
}
