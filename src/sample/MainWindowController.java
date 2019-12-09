package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    Label labelText;
    @FXML
    Button buttonLog, buttonExit, buttonNew, buttonStats, buttonTasks, buttonLogout;
    @FXML
    TextField textField1;

    Map<User, List<Task>> taskMap1 = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogout.setDisable(true);
    }

    public void buttonLogInClicked() {
        String textFromTexField = textField1.getText();
        if (!textFromTexField.equals("")) {

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
        Stage pStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        StackPane stackPane = fxmlLoader.load(getClass().getResource("windowTasks.fxml").openStream());
        WindowTasks controller = (WindowTasks) fxmlLoader.getController();
        controller.setLabelUserName(labelText.getText());
        controller.setTasks(taskMap1);
        controller.setParent(this);

        Scene scene = new Scene(stackPane);
        pStage = new Stage();
        pStage.setTitle("Tasks");
//        pStage.initModality(Modality.APPLICATION_MODAL);
        pStage.setResizable(false);
        pStage.setScene(scene);
        pStage.show();

    }
    public void passMap(Map<User, List<Task>> newMap) {
        taskMap1.putAll(newMap);
        }



    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void setStage(Stage primaryStage) {
    }

    public void setButtonNew() {
//        for (Map.Entry<User, Task> m : taskMap1.entrySet()) {
//            String user = m.getKey().toString();
//            String task = m.getValue().toString();
//            System.out.println(user + " : " + task);
//        }
        System.out.println(taskMap1.size());
    }
}
