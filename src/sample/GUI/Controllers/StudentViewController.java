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
import sample.GUI.Model.StudentAttendanceModelMock;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {

    LoginViewController loginViewController = new LoginViewController();

    public ComboBox cmboxStudent;

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
    public Label NavnLabel;
    public Label UddannelseLabel;
    public Label KlasseLabel;
    public Label SemesterLabel;

    private StudentAttendanceModelMock sam = StudentAttendanceModelMock.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            NavnLabel.setText(sam.getLoggedInStudent().getName());
            UddannelseLabel.setText(sam.getLoggedInStudent().getEducation());
            KlasseLabel.setText(sam.getLoggedInStudent().getClassYear());
            SemesterLabel.setText(Integer.toString(sam.getLoggedInStudent().getSemester()));

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
        pieChart.setLabelLineLength(20);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        pieChart.setData(pieChartData);

        // // Sets the image for the student
        File file = new File("Billeder/DefaultBilledeFb.png");
        Image image = new Image(file.toURI().toString());
        imgStudent.setImage(image);
    }

    public void handleSelectStudent(ActionEvent actionEvent) {
        
    }

}
