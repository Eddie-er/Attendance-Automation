package sample.GUI.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.BE.Classes;
import sample.BE.Student;
import sample.BLL.MockBLL.ClassBLLManagerMock;
import sample.BLL.MockBLL.StudentBLLManagerMock;
import sample.GUI.Model.ClassesModel;
import sample.GUI.Model.StudentModel;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {

    public ComboBox<Student> cmboxStudent;
    public ComboBox<Classes> cmboxClasses;

    // Line chart
    public CategoryAxis x;
    public NumberAxis y;
    public LineChart<?, ?> chartAttendance;

    // Bar chart
    public BarChart barChart;
    public NumberAxis yAxis;
    public CategoryAxis xAxis;
    
    public PieChart pieChart;

    public Label labelName;
    public Label labelEducation;
    public Label labelClass;
    public Label labelYear;
    public Label labelSemester;

    public Button btnClassList;
    public ImageView imgStudent;

    private ClassBLLManagerMock classBLLManagerMock;
    private StudentBLLManagerMock studentBLLManagerMock;
    private StudentModel studentModel;
    private ClassesModel classesModel;

    private Student selectedStudentMock = null;
    private Classes selectedClasses = null;

    public TeacherViewController() {
        studentBLLManagerMock = new StudentBLLManagerMock();
        classBLLManagerMock = new ClassBLLManagerMock();
        studentModel = new StudentModel();
        classesModel = new ClassesModel();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // Line chart
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("1", 5));
        series.getData().add(new XYChart.Data("2", 10));
        series.getData().add(new XYChart.Data("3", 8));
        series.getData().add(new XYChart.Data("4", 12));
        series.getData().add(new XYChart.Data("5", 10));

        chartAttendance.getData().addAll(series);

        // Bar chart
        XYChart.Series series2 = new XYChart.Series();

        series2.getData().add(new XYChart.Data("Mandag", 15));
        series2.getData().add(new XYChart.Data("Tirsdag", 20));
        series2.getData().add(new XYChart.Data("Onsdag", 5));
        series2.getData().add(new XYChart.Data("Torsdag", 7));
        series2.getData().add(new XYChart.Data("Fredag", 2));

        barChart.getData().add(series2);


        // Pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("SCO", 3000),
                new PieChart.Data("ITO", 1500),
                new PieChart.Data("DBOS", 500),
                new PieChart.Data("SDE", 800)
        );

        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        pieChart.setData(pieChartData);

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
            selectedStudentMock = newValue;
            updateInformation();
        });
    }


    // Update the labels
    public void updateInformation() {
        if (selectedStudentMock != null) {
            //labelName.setText(selectedStudentMock.getFirstName() + " " + selectedStudentMock.getLastName());
            //labelEducation.setText(selectedStudentMock.getEducation());
            //labelClass.setText(selectedStudentMock.getClassYear());
            //labelYear.setText(Integer.toString(selectedStudentMock.getSemester()));
        }
    }

    public void handleSelectStudent(ActionEvent actionEvent) {
    }


    public void handleSelectClasses(ActionEvent actionEvent) {

    }

    /**
     * Shows the class list
     * @throws IOException
     */
    public void handleSelectClassList(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/GUI/View/ClassListView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Class List");
        stage.show();

    }
}
