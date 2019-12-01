package sample;

import com.sun.javafx.collections.MappingChange;
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
import java.util.HashMap;
import java.util.Map;
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
        buttonLogout.setDisable(true);
    }

    public void buttonLogInClicked() {
        String textFromTexField = textField1.getText();
        if (!textFromTexField.equals("")) {

            User user1 = new User(textFromTexField);
            Map<User, Task> taskMap = new HashMap<>();
//            taskMap.put(user1, task);
            System.out.println(taskMap);

            labelText.setText(textFromTexField);
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
//        windowTasks.setLabelUserName(labelText.getText());
    }

    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void setStage(Stage primaryStage) {
    }
}
