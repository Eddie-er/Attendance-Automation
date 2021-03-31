package sample.GUI.Controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.BE.Student;
import sample.BLL.MockBLL.StudentBLLManagerMock;
import sample.GUI.Model.StudentModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassListViewController implements Initializable {
    public TableView<Student> tblClassList;
    public TableColumn<Student, String> colName;
    public TableColumn<Student, Integer> colAttendance;

    private StudentBLLManagerMock studentBLLManagerMock;
    private StudentModel studentModel;

    public ClassListViewController() {
        studentBLLManagerMock =  new StudentBLLManagerMock();
        studentModel = new StudentModel();
    }

    /**
     * Loads students and lists them with their attendance
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tblClassList.setItems(studentModel.getAllStudents());
            colName.setCellValueFactory(celldata -> Bindings.concat(celldata.getValue().firstNameProperty(), " ", celldata.getValue().lastNameProperty()));
            colAttendance.setCellValueFactory(new PropertyValueFactory<>("Attendance"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colAttendance.setSortType(TableColumn.SortType.DESCENDING);
        tblClassList.getSortOrder().add(colAttendance);
    }
}
