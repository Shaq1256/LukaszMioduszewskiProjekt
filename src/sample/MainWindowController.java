package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    Label labelText;
    @FXML
    Button buttonLog, buttonExit, buttonNew, buttonStats, buttonTasks, buttonLogout;
    @FXML
    TextField textField1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        buttonExit.setGraphic(new ImageView("sample/logout.png"));
        buttonLogout.setDisable(true);
    }

    public void buttonLogInClicked() {
        String textFromTexField = textField1.getText();
        if (!textFromTexField.equals("")) {
            labelText.setText("Welcome:    " + textFromTexField);
            textField1.setText("");
            buttonNew.setDisable(false);
            buttonStats.setDisable(false);
            buttonTasks.setDisable(false);
            buttonLog.setDisable(true);
            buttonLogout.setDisable(false);
        } else {
            labelText.setText("Please log in first!!!");
        }
    }

    public void buttonLogOutClicked() {
        labelText.setText("Please log in first!!!");
        buttonNew.setDisable(true);
        buttonStats.setDisable(true);
        buttonTasks.setDisable(true);
        buttonLog.setDisable(false);
        buttonLogout.setDisable(true);
    }

    public void openTasks() throws IOException {
        WindowTasks windowTasks = new WindowTasks();
        windowTasks.openTasks();
    }

    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void setStage(Stage primaryStage) {
    }
}
