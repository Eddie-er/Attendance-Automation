package sample.GUI.Controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.BE.StudentMock;
import sample.BLL.StudentBLLManagerMock;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassListViewController implements Initializable {
    public TableView<StudentMock> tblClassList;
    public TableColumn<StudentMock, String> colName;
    public TableColumn<StudentMock, Integer> colAttendance;

    private StudentBLLManagerMock studentBLLManagerMock;

    public ClassListViewController() {
        studentBLLManagerMock =  new StudentBLLManagerMock();
    }

    /**
     * Loads students and lists them with their attendance
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tblClassList.setItems(studentBLLManagerMock.loadStudents());
            colName.setCellValueFactory(celldata -> Bindings.concat(celldata.getValue().nameProperty(), " ", celldata.getValue().lastNameProperty()));
            colAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        colAttendance.setSortType(TableColumn.SortType.DESCENDING);
        tblClassList.getSortOrder().add(colAttendance);

    }
}
