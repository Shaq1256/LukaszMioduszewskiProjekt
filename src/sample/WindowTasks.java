package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WindowTasks implements Initializable {

    Stage windowTask;

    @FXML
    Button buttonTaskExit;
    @FXML private TableView<Task> tableViewTask;
    @FXML private TableColumn<Task, String> TasksToDo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void openTasks() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("windowTasks.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        windowTask = new Stage();
        windowTask.setTitle("Tasks");
        windowTask.initModality(Modality.APPLICATION_MODAL);
        windowTask.setResizable(false);
        windowTask.setScene(scene);
        windowTask.show();
    }
    public void closeWindowTasks() {
        windowTask = (Stage) buttonTaskExit.getScene().getWindow();
        windowTask.close();
    }
}
