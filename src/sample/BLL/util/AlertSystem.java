package sample.BLL.util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertSystem {

    /**
     * A popup window to warn the user or to show a confirmation window
     * @param title
     * @param subMsg
     * @param msg
     */
    public static  void alertUser(String title, String subMsg, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.setTitle(title);
        alert.setHeaderText(subMsg);
        alert.setContentText(msg);
        alert.show();
    }
}
