package sample.BE;

import java.util.Date;

public class Attendance {
    private int AttendanceID;
    private boolean IsPresent;
    private Date date;

    public Attendance(int attendanceID, boolean isPresent, Date date) {
        AttendanceID = attendanceID;
        IsPresent = isPresent;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
