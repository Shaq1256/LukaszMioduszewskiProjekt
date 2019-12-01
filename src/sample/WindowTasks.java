package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class WindowTasks implements Initializable {

    Stage windowTask;

    @FXML Button buttonTaskExit, buttonNewTask, buttonDeleteTask;
    @FXML private TableView<Task> tableViewTask;
    @FXML private TableColumn<Task, String> tasksToDo;
    @FXML TextArea textArea;
    @FXML Label labelTextUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasksToDo.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tableViewTask.setItems(getTask());

        tableViewTask.setEditable(true);
        tasksToDo.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void addTaskToTable() {
        String textFromTextArea = textArea.getText();
        if (!textFromTextArea.equals("")) {
            Task newTask = new Task(textArea.getText());
            tableViewTask.getItems().add(newTask);

            String textFromTextField = labelTextUser.getText();
            User user1 = new User(textFromTextField);
            Map<User, Task> taskMap = new HashMap<>();
            taskMap.put(user1, newTask);
            System.out.println(taskMap);

            textArea.clear();
        } else {
            textArea.setPromptText("Type your task first.");
        }
    }

    public void deleteTaskFromTable() {
        ObservableList<Task> selectedTask, allTasks;
        try {
            allTasks = tableViewTask.getItems();
            if (allTasks.size() != 0) {
                selectedTask = tableViewTask.getSelectionModel().getSelectedItems();
                selectedTask.forEach(allTasks::remove);
            }
        } catch (Exception e) {
            //Table is empty, do nothng
        }
    }

    public void cellTableEdit(TableColumn.CellEditEvent cellEditEvent) {
        Task taskRow = tableViewTask.getSelectionModel().getSelectedItem();
        taskRow.setTask(cellEditEvent.getNewValue().toString());
    }

    public ObservableList<Task> getTask() {
        ObservableList<Task> taskList = FXCollections.observableArrayList();
        taskList.add(new Task("Jutro o godzinie 12.00 spotkanie z dyrektorem w kwestii nowego projektu!!!"));
        taskList.add(new Task("Sroda, godz 9.00. Spotkanie z klientem dotyczące zatwierdzenia zmian na drugim etapie " +
                "wdrażanie nowego \nsystemu w firmie Anovo"));
        return taskList;
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

    public void setButtonTaskExit(Button buttonTaskExit) {

    }
    public void setLabelUserName(String labelTextUserName) {
        System.out.println(labelTextUserName);
//        labelTextUser.setText(labelTextUserName);
    }

    public void closeWindowTasks() {
        windowTask = (Stage) buttonTaskExit.getScene().getWindow();
        windowTask.close();
    }

}
