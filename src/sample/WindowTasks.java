package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class WindowTasks implements Initializable {

    Stage windowTask;
    MainWindowController controller;

    @FXML Button buttonTaskExit, buttonNewTask, buttonDeleteTask, buttonSave;
    @FXML private TableView<List<Task>> tableViewTask;
    @FXML private TableColumn<Task, String> tasksToDo;
    @FXML TextArea textArea;
    @FXML Label labelTextUser;

    public Map<User, List<Task>> taskMap = new HashMap<>();
    List<Task> taskList;
    Task value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasksToDo.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tableViewTask.setItems(getTask());

        tableViewTask.setEditable(true);
        tasksToDo.setCellFactory(TextFieldTableCell.forTableColumn());

        getClickedTask();
    }

    public void addTaskToTable() {
        String textFromTextArea = textArea.getText();
        if (!textFromTextArea.equals("")) {
            Task newTask = new Task(textFromTextArea);
//            tableViewTask.getItems().add(newTask);
            taskList.add(newTask);

            String textFromLabel = labelTextUser.getText();
            User user1 = new User(textFromLabel);
//            taskMap.put(user1, newTask);
            taskMap.put(user1, taskList);

            for (Map.Entry<User, List<Task>> m : taskMap.entrySet()) {
                String user = m.getKey().toString();
                String task = m.getValue().toString();
                System.out.println(user + " : " + task);
            }

            textArea.clear();
        } else {
            textArea.setPromptText("Type your task first.");
        }
    }
    public void getClickedTask() {
        tableViewTask.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 0) {
                    value = (Task) tableViewTask.getSelectionModel().getSelectedItem();
                }
            }
        });
    }

    public void deleteTaskFromTable() {
        ObservableList<List<Task>> selectedTask;
        ObservableList<List<Task>> allTasks;
        try {
            allTasks = tableViewTask.getItems();
            if (allTasks.size() != 0) {
                selectedTask = tableViewTask.getSelectionModel().getSelectedItems();
                selectedTask.forEach(allTasks::remove);
                taskMap.values().remove(value);
                System.out.println(taskMap.size());
            }
        } catch (Exception e) {
            //Table is empty, do nothng
        }
    }

    public void saveMap() {
//        MainWindowController controller = new MainWindowController();
//        controller.taskMap1.putAll(taskMap);
//        HashMap<User, Task> cloneMap = (HashMap<User, Task>) taskMap.replaceAll();
        controller.passMap(taskMap);

        System.out.println("taskMap1 :  " + controller.taskMap1.size());
        System.out.println("taskMap :  " + taskMap.size());
    }

    public void cellTableEdit(TableColumn.CellEditEvent cellEditEvent) {
        Task taskRow = (Task) tableViewTask.getSelectionModel().getSelectedItem();
        taskRow.setTask(cellEditEvent.getNewValue().toString());
    }

    public ObservableList<List<Task>> getTask() {

        ObservableList<List<Task>> taskList = FXCollections.observableArrayList();
        taskList.add(taskMap.get(new User(labelTextUser.getText())));
        return taskList;
    }

    public void setLabelUserName(String labelTextUserName) {
        System.out.println(labelTextUserName);
        labelTextUser.setText(labelTextUserName);

    }
    public void setTasks(Map<User, List<Task>> tasks) {
        taskMap = tasks;
        tableViewTask.setItems(getTask());
    }

    public void closeWindowTasks() {
        windowTask = (Stage) buttonTaskExit.getScene().getWindow();
        windowTask.close();
    }
    public void setParent(MainWindowController controller) {
        this.controller = controller;
    }

}
