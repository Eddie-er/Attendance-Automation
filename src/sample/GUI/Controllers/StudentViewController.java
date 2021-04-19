package sample.GUI.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.BE.Attendance;
import sample.BLL.util.AlertSystem;
import sample.GUI.Model.StudentLoggedInModel;
import sample.GUI.Model.StudentModel;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {

    public Label lblName;
    public Label lblEducation;
    public Label lblClass;
    public Label lblEmail;
    public Label lblAttendance;

    // Line chart
    public CategoryAxis x;
    public NumberAxis y;
    public LineChart<?, ?> chartAttendance;

    // Bar chart
    public BarChart barChart;
    public NumberAxis yAxis;
    public CategoryAxis xAxis;

    public PieChart pieChart;

    public ImageView imgStudent;

    private StudentModel studentModel = new StudentModel();
    private StudentLoggedInModel studentLoggedInModel = StudentLoggedInModel.getInstance();

    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;

    private int absent;
    private int present;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblName.setText(studentLoggedInModel.getLoggedInStudent().getFirstName() + " " + studentLoggedInModel.getLoggedInStudent().getLastName());
        lblEmail.setText(studentLoggedInModel.getLoggedInStudent().getEmail());
        lblAttendance.setText(Double.toString(studentLoggedInModel.getLoggedInStudent().getAttendance()));
        
        try {
            updateInformation();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // // Sets the image for the student
        File file = new File("Billeder/DefaultBilledeFb.png");
        Image image = new Image(file.toURI().toString());
        imgStudent.setImage(image);
    }

    public void updateInformation() throws SQLException {
        lblAttendance.setText(Double.toString(studentLoggedInModel.getLoggedInStudent().getAttendance()));
        checkAbsentDays();
        checkAbsentAndPresentDays();
        setBarChartData();
        setPieChartData();
    }

    public void setBarChartData() {
        barChart.getData().clear();
        barChart.layout();
        yAxis.setTickUnit(1.0);
        yAxis.setLowerBound(0);
        yAxis.setAutoRanging(false);

        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("Mandag", monday));
        series.getData().add(new XYChart.Data("Tirsdag", tuesday));
        series.getData().add(new XYChart.Data("Onsdag", wednesday));
        series.getData().add(new XYChart.Data("Torsdag", thursday));
        series.getData().add(new XYChart.Data("Fredag", friday));

        barChart.getData().add(series);
    }

    public void setPieChartData() {
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(20);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Present", present),
                new PieChart.Data("Absent", absent)
        );

        pieChart.setData(pieChartData);
    }

    public void handleSelectIsAbsent(ActionEvent actionEvent) throws SQLException {
        int StudentID = studentLoggedInModel.getLoggedInStudent().getStudentID();
        Date date = new Date(System.currentTimeMillis());

        if (studentModel.checkExistingAttendance(StudentID, date)) {
            AlertSystem.alertUser("Fravær allerede registreret", "Fejl opstod...", "Der er allerede blevet registreret fravær idag" );
        } else {
            Attendance attendance = new Attendance(-1, false, date, StudentID);
            studentModel.studentIsAbsent(attendance);
            AlertSystem.alertUser("Fravær registreret", "Du har registreret", "At du ikke er tilstede");
            lblAttendance.setText(Double.toString(studentLoggedInModel.getLoggedInStudent().getAttendance()));
            updateInformation();
        }
    }

    public void handleSelectIsPresent(ActionEvent actionEvent) throws SQLException {
        int StudentID = studentLoggedInModel.getLoggedInStudent().getStudentID();
        Date date = new Date(System.currentTimeMillis());

        if (studentModel.checkExistingAttendance(StudentID, date)) {
            AlertSystem.alertUser("Fravær allerede registreret", "Fejl opstod...", "Der er allerede blevet registreret fravær idag");
        } else {
            Attendance attendance = new Attendance(-1, true, date, StudentID);
            studentModel.studentIsPresent(attendance);
            AlertSystem.alertUser("Fravær registreret", "Du har registreret", "At du er tilstede");
            updateInformation();
        }
    }

    public void checkAbsentDays() {
        monday = 0;
        tuesday = 0;
        wednesday = 0;
        thursday = 0;
        friday = 0;

        int StudentID = studentLoggedInModel.getLoggedInStudent().getStudentID();
        List<LocalDate> absentDays = new ArrayList<>(studentModel.getAbsentDays(StudentID));

        for (LocalDate date: absentDays) {
            if (date.getDayOfWeek().toString().equals("MONDAY")) {
                monday++;
            } else if (date.getDayOfWeek().toString().equals("TUESDAY")) {
                tuesday++;
            } else if (date.getDayOfWeek().toString().equals("WEDNESDAY")) {
                wednesday++;
            } else if (date.getDayOfWeek().toString().equals("THURSDAY")) {
                thursday++;
            } else if (date.getDayOfWeek().toString().equals("FRIDAY")) {
                friday++;
            }
        }
    }

    public void checkAbsentAndPresentDays() throws SQLException {
        absent = 0;
        present = 0;

        List<Attendance> attendances = new ArrayList<>(studentModel.getAttendanceFromStudent(studentLoggedInModel.getLoggedInStudent()));

        for (Attendance attendance: attendances) {
            if (attendance.isPresent()) {
                present++;
            } else if (!attendance.isPresent()) {
                absent++;
            }
        }
    }
}
