package sample.GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.BE.Student;
import sample.DAL.LoginDAO;

import sample.GUI.Model.StudentLoggedInModel;
import sample.GUI.Model.StudentModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {


    @FXML
    private ImageView imageView;
    @FXML
    public Button logIn;
    @FXML
    private TextField UserName;
    @FXML
    private TextField PassWord;
    @FXML
    private Label ErrorLabel;

    LoginDAO loginDAL = new LoginDAO();

    StudentModel studentModel = new StudentModel();


    /**
     * Sets the image for the login view
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("Billeder/EASV-med-ramme.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    /***
     * Checks the student's usernames and passwords in order to log into the student view
     * @throws Exception
     */
    @FXML
    public void login(ActionEvent event) throws Exception {
        List<Student> students = studentModel.getAllStudents();

        String Name = UserName.getText();
        String Code = PassWord.getText();
        boolean LoginData = false;

        loginDAL.statementsVSPreparedStatements(Name, Code);

        for (Student student : students) {
            if(UserName.getText().equals(student.getEmail()) && PassWord.getText().equals(student.getPassword())){
                LoginData = true;
                StudentLoggedInModel.getInstance().setLoggedInStudent(student);
            }
        }


            if (LoginData) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/GUI/View/StudentView.fxml"));
                    GridPane layout = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(layout);
                    stage.setScene(scene);
                    stage.setTitle("Elev");
                    stage.show();
                    stage.setResizable(false);


                } catch (IOException e){
                    e.printStackTrace();
                }
                /**
                 * Login for teacherview
                 */
            } else if (UserName.getText().equals("lærer") && PassWord.getText().equals("lærer")) {
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("/sample/GUI/View/TeacherView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Attendance");
                    stage.show();
                    stage.setResizable(false);
                }catch (IOException e){
                    e.printStackTrace();
                }
            } else {
                ErrorLabel.setText("Wrong username or password. Please try again :D");
            }

        }

    /**
     * Empties the textfields
     */
    public void reset(ActionEvent actionEvent) {
        UserName.setText("");
        PassWord.setText("");
        ErrorLabel.setText("");
    }
}
