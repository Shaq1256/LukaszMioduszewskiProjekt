package sample;

import com.sun.javafx.collections.MappingChange;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
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

    Map<User, Task> taskMap1 = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogout.setDisable(true);
    }

    public void buttonLogInClicked() {
        String textFromTexField = textField1.getText();
        if (!textFromTexField.equals("")) {

//            Map<User, Task> taskMap = new HashMap<>();
//            taskMap.put(user1, task);
//            System.out.println(taskMap);

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

        Scene scene = new Scene(stackPane);
        pStage = new Stage();
        pStage.setTitle("Tasks");
        pStage.initModality(Modality.APPLICATION_MODAL);
        pStage.setResizable(false);
        pStage.setScene(scene);
        pStage.show();


//        WindowTasks windowTasks = new WindowTasks();
//        windowTasks.openTasks();
//        windowTasks.setLabelUserName(labelText.getText());
    }

    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void setStage(Stage primaryStage) {
    }

    public void setButtonNew() {
        for (Map.Entry<User, Task> m : taskMap1.entrySet()) {
            String user = m.getKey().toString();
            String task = m.getValue().toString();
            System.out.println(user + " : " + task);
        }
        int size = taskMap1.size();
        System.out.println(size);
    }
}
