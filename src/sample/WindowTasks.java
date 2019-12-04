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
import java.util.Map;
import java.util.ResourceBundle;

public class WindowTasks implements Initializable {

    Stage windowTask;

    @FXML Button buttonTaskExit, buttonNewTask, buttonDeleteTask, buttonSave;
    @FXML private TableView<Task> tableViewTask;
    @FXML private TableColumn<Task, String> tasksToDo;
    @FXML TextArea textArea;
    @FXML Label labelTextUser;

    public Map<User, Task> taskMap = new HashMap<>();
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
            Task newTask = new Task(textArea.getText());
            tableViewTask.getItems().add(newTask);

            String textFromLabel = labelTextUser.getText();
            User user1 = new User(textFromLabel);
            taskMap.put(user1, newTask);

            for (Map.Entry<User, Task> m : taskMap.entrySet()) {
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
                    value = tableViewTask.getSelectionModel().getSelectedItem();
                }
            }
        });
    }

    public void deleteTaskFromTable() {
        ObservableList<Task> selectedTask, allTasks;
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
        MainWindowController controller = new MainWindowController();
//        controller.taskMap1.putAll(taskMap);
//        HashMap<User, Task> cloneMap = (HashMap<User, Task>) taskMap.replaceAll();
        controller.passMap(taskMap);

        System.out.println("taskMap1 :  " + controller.taskMap1.size());
        System.out.println("taskMap :  " + taskMap.size());
    }

    public void cellTableEdit(TableColumn.CellEditEvent cellEditEvent) {
        Task taskRow = tableViewTask.getSelectionModel().getSelectedItem();
        taskRow.setTask(cellEditEvent.getNewValue().toString());
    }

    public ObservableList<Task> getTask() {

        ObservableList<Task> taskList = FXCollections.observableArrayList();
        return taskList;
    }

//    public void openTasks() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("windowTasks.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load());
//        windowTask = new Stage();
//        windowTask.setTitle("Tasks");
//        windowTask.initModality(Modality.APPLICATION_MODAL);
//        windowTask.setResizable(false);
//        windowTask.setScene(scene);
//        windowTask.show();
//    }

    public void setLabelUserName(String labelTextUserName) {
        System.out.println(labelTextUserName);
        labelTextUser.setText(labelTextUserName);

    }

    public void closeWindowTasks() {


        windowTask = (Stage) buttonTaskExit.getScene().getWindow();
        windowTask.close();
    }

}
