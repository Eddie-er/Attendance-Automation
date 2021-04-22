package sample.GUI.Controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import sample.BE.Attendance;
import sample.BE.Classes;
import sample.BE.Student;


import sample.BLL.util.AlertSystem;
import sample.GUI.Model.ClassesModel;
import sample.GUI.Model.StudentModel;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {

    public ComboBox<Student> cmboxStudent;
    public ComboBox<Classes> cmboxClasses;

    // Bar chart
    public BarChart barChart;
    public NumberAxis yAxis;
    public CategoryAxis xAxis;
    
    public PieChart pieChart;

    public Label labelName;
    public Label labelEducation;
    public Label labelClass;
    public Label labelEmail;
    public Label labelAttendance;

    public Button btnCloseClassList;
    public Button btnClassList;
    public ImageView imgStudent;

    public TableView<Student> tblClassList;
    public TableColumn<Student, String> colName;
    public TableColumn<Student, Integer> colAttendance;


    private StudentModel studentModel;
    private ClassesModel classesModel;

    private Student selectedStudent = null;
    private Classes selectedClasses = null;

    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;

    private int present;
    private int absent;

    public TeacherViewController() {

        studentModel = new StudentModel();
        classesModel = new ClassesModel();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblClassList.setVisible(false);
        btnCloseClassList.setVisible(false);


        // Sets the image for the student
        File file = new File("Billeder/DefaultBilledeFb.png");
        Image image = new Image(file.toURI().toString());
        imgStudent.setImage(image);


        // Fills the combobox with a list of classes
        try {
            cmboxClasses.setItems(classesModel.getAllClasses());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        // Converting Class object to string
        cmboxClasses.setConverter(new StringConverter<Classes>() {
            @Override
            public String toString(Classes classes) {
                return classes.getClassName();
            }

            @Override
            public Classes fromString(String s) {
                return null;
            }
        });

        //Listener for the Classes combobox
        cmboxClasses.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            selectedClasses = newValue;
            try {
                cmboxStudent.setItems(studentModel.getStudentsInClass(selectedClasses));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });

        // Fills the combobox with a list of students
        try {
            cmboxStudent.setItems(studentModel.getAllStudents());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Converting student object to string
        cmboxStudent.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student student) {
                return student.getFirstName() + " " + student.getLastName();
            }

            @Override
            public Student fromString(String s) {
                return null;
            }
        });

        // Listener for the Student combobox
        cmboxStudent.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            selectedStudent = newValue;
            try {
                updateInformation();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    /**
     * Updating the labels and charts
     * @throws SQLException
     */
    public void updateInformation() throws SQLException {
        if (selectedStudent != null) {
            labelName.setText(selectedStudent.getFirstName() + " " + selectedStudent.getLastName());
            labelClass.setText(selectedClasses.getClassName());
            labelEmail.setText(selectedStudent.getEmail());
            labelEducation.setText(selectedClasses.getEducation());
            labelAttendance.setText(String.format("%.2f", studentModel.getNewAttendancePercentage(selectedStudent.getStudentID())));
            checkAbsentDays();
            checkAbsentAndPresentDays();
            setBarChartData();
            setPieChartData();
        }
    }

    /**
     * Setting the data in the pie chart
     * The two parts represent how many days the student has been absent and present
     */
    public void setPieChartData() {
        pieChart.getData().clear();
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(20);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Tilstede", present),
                new PieChart.Data("Fraværende", absent)
        );
        pieChart.setData(pieChartData);
    }

    /**
     * Setting the data in the bar chart
     * The values are the days which a student has been absent
     */
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

        barChart.getData().addAll(series);
    }

    public void handleSelectStudent(ActionEvent actionEvent) {
    }


    public void handleSelectClasses(ActionEvent actionEvent) {
    }

    /**
     * Shows the class list
     * Sorts the list by attendance with most absent students at the top
     * @throws IOException
     */
    public void handleSelectClassList(ActionEvent actionEvent) throws IOException {
        tblClassList.setVisible(true);
        btnCloseClassList.setVisible(true);

        barChart.setVisible(false);
        pieChart.setVisible(false);

        try {
            tblClassList.setItems(studentModel.getStudentsInClass(selectedClasses));
            colName.setCellValueFactory(celldata -> Bindings.concat(celldata.getValue().firstNameProperty(), " ", celldata.getValue().lastNameProperty()));
            colAttendance.setCellValueFactory(new PropertyValueFactory<>("Attendance"));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        colAttendance.setSortType(TableColumn.SortType.DESCENDING);
        tblClassList.getSortOrder().add(colAttendance);
    }

    /**
     * Closing the class list
     * @param actionEvent
     */
    public void handleCloseClassList(ActionEvent actionEvent) {
        tblClassList.setVisible(false);
        btnCloseClassList.setVisible(false);

        barChart.setVisible(true);
        pieChart.setVisible(true);
    }

    /**
     * When pressed a student is marked as present
     * A confirmation windows pops up
     * If the students attendance has already been registered a message will pop up
     * @param actionEvent
     * @throws SQLException
     */
    public void handleSelectIsPresent(ActionEvent actionEvent) throws SQLException {
        int StudentID = selectedStudent.getStudentID();
        Date date = new Date(System.currentTimeMillis());

        if (studentModel.checkExistingAttendance(StudentID, date)) {
            AlertSystem.alertUser("Fravær allerede registreret", "Fejl opstod...", "Der er allerede blevet registreret fravær idag" );
        } else {
            Attendance attendance = new Attendance(-1, true, date, StudentID);
            studentModel.studentIsPresent(attendance);
            AlertSystem.alertUser("Fravær registreret", "Du har registreret" + " " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName(), "Som at være tilstede");
            updateAttendancePercentage();
            updateInformation();
        }
    }

    /**
     * When pressed a student will be marked as absent
     * A confirmation windows pops up
     * If the students attendance has already been registered a message will pop up
     * @param actionEvent
     * @throws SQLException
     */
    public void handleSelectIsAbsent(ActionEvent actionEvent) throws SQLException {
        int StudentID = selectedStudent.getStudentID();
        Date date = new Date(System.currentTimeMillis());

        if (studentModel.checkExistingAttendance(StudentID, date)){
            AlertSystem.alertUser("Fravær allerede registreret", "Fejl opstod...", "Der er allerede blevet registreret fravær idag" );
        } else {
            Attendance attendance = new Attendance(-1, false, date, StudentID);
            studentModel.studentIsAbsent(attendance);
            AlertSystem.alertUser("Fravær registreret", "Du har registreret" + " " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName(), "Som at ikke være tilstede");
            updateAttendancePercentage();
            updateInformation();
        }
    }

    /**
     * Updates the absent attendance
     * Counts the absent and present days and calculates a percentage
     * @throws SQLException
     */
    public void updateAttendancePercentage() throws SQLException {
        List<Attendance> attendances = studentModel.getAttendanceFromStudent(selectedStudent);
        int StudentID = selectedStudent.getStudentID();
        double present = 0;
        double absent = 0;
        double sum;

        for (Attendance attendance: attendances) {
            if (attendance.isPresent()) {
                present++;
            } else {
                absent++;
            }
        }

        sum = present + absent;
        double attendancePercentage = (absent * 100) / sum;
        studentModel.updateAttendancePercentage(StudentID, attendancePercentage);
    }

    /**
     * Gets the days a student has been absent
     * Counts the weekdays a student has been absent
     * Used for the bar chart
     * @throws SQLException
     */
    public void checkAbsentDays() throws SQLException {
        monday = 0;
        tuesday = 0;
        wednesday = 0;
        thursday = 0;
        friday = 0;

        int StudentID = selectedStudent.getStudentID();
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

    /**
     * Gets the days a student has been present and absent
     * Used for the pie chart
     * @throws SQLException
     */
    public void checkAbsentAndPresentDays() throws SQLException {
        absent = 0;
        present = 0;

        List<Attendance> attendances = new ArrayList<>(studentModel.getAttendanceFromStudent(selectedStudent));

        for (Attendance attendance: attendances) {
            if (attendance.isPresent()) {
                present++;
            } else if (!attendance.isPresent()) {
                absent++;
            }
        }
    }
}
