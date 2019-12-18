package sample;

import com.sun.xml.internal.bind.XmlAccessorFactory;
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
import java.time.LocalDate;
import java.util.*;

public class WindowTasks implements Initializable {

    Stage windowTask;
    MainWindowController controller;

    @FXML
    Button buttonTaskExit, buttonNewTask, buttonDeleteTask, buttonEdit;
    @FXML
    DatePicker deadLineDatePicker;
    @FXML
    private TableView<Task> tableViewTask;
    @FXML
    private TableColumn<Task, String> tasksToDo, status, deadLineTask;
    @FXML
    private TableColumn<User, String> userTaskList;
    @FXML
    TextArea textArea;
    @FXML
    TextField textFieldStatus;
    @FXML
    Label labelTextUser;

    public Map<User, List<Task>> taskMap = new HashMap<>();
    public List<Task> taskList = new ArrayList<>();
    TableView<Task> tableToEdit;
    Task value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasksToDo.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        status.setCellValueFactory(new PropertyValueFactory<Task, String>("taskStatus"));
        deadLineTask.setCellValueFactory(new PropertyValueFactory<Task, String>("deadLineTask"));
        userTaskList.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        tableViewTask.setItems(getTask());

        tableViewTask.setEditable(true);
        tasksToDo.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        deadLineTask.setCellFactory(TextFieldTableCell.forTableColumn());
        userTaskList.setCellFactory(TextFieldTableCell.forTableColumn());
        deadLineDatePicker.setValue(LocalDate.now());

        getClickedTask();
    }

    public void addTaskToTable() {

        String textFromTextArea = textArea.getText();
        String textFromTextFieldStatus = textFieldStatus.getText();
        LocalDate deadLineDate = deadLineDatePicker.getValue();

        if (!textFromTextArea.equals("") && !textFromTextFieldStatus.equals("")) {
            Task newTask = new Task(textFromTextArea, textFromTextFieldStatus, deadLineDate.toString());
            taskList.add(newTask);

            String textFromLabel = labelTextUser.getText();
            User user1 = new User(textFromLabel);
            taskMap.put(user1, taskList);


            for (Map.Entry<User, List<Task>> m : taskMap.entrySet()) {
                String user = m.getKey().toString();
                String task = m.getValue().toString();
                System.out.println(user + " : " + task);
            }

            tableViewTask.setItems(getTask());

            textArea.clear();
            textFieldStatus.clear();
            deadLineDatePicker.setValue(LocalDate.now());

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
        ObservableList<Task> selectedTask, allTasks;
        try {
            allTasks = tableViewTask.getItems();
            if (allTasks.size() != 0) {
                selectedTask = tableViewTask.getSelectionModel().getSelectedItems();
                selectedTask.forEach(allTasks::remove);
                taskMap.values().remove(selectedTask);
                System.out.println(taskMap.size());
            }
        } catch (Exception e) {
            //Table is empty, do nothing
        }
    }

    public void cellTableEdit(TableColumn.CellEditEvent cellEditEvent) {
        Task taskRow = (Task) tableViewTask.getSelectionModel().getSelectedItem();
        taskRow.setTask(cellEditEvent.getNewValue().toString());
    }

    public void tasksEditor() {

        Task taskToEdit = tableViewTask.getSelectionModel().getSelectedItem();
        tableToEdit = tableViewTask;
        textArea.setText(taskToEdit.getTask());
        textFieldStatus.setText(taskToEdit.getTaskStatus());
        LocalDate localDate = LocalDate.parse(taskToEdit.getDeadLineTask());
        deadLineDatePicker.setValue(localDate);
        buttonNewTask.setDisable(true);
        buttonEdit.setDisable(true);
        buttonDeleteTask.setDisable(true);

    }

    public void saveEditedTask() {
        int selectedRow = tableViewTask.getSelectionModel().getSelectedIndex();
        tableToEdit.getItems().get(selectedRow).setTask(textArea.getText());
        tableToEdit.getItems().get(selectedRow).setTaskStatus(textFieldStatus.getText());
        tableToEdit.getItems().get(selectedRow).setDeadLineTask(deadLineDatePicker.getValue().toString());
        tableToEdit.refresh();
        textArea.setText("");
        textFieldStatus.setText("");
        buttonNewTask.setDisable(false);
        buttonEdit.setDisable(false);
        buttonDeleteTask.setDisable(false);
    }

    public ObservableList<Task> getTask() {

        ObservableList<Task> taskList = FXCollections.observableArrayList();
        List<Task> savedTasks = taskMap.get(new User(labelTextUser.getText()));
        if (savedTasks != null && savedTasks.size() > 0) {
            for (Task task : savedTasks) {
                taskList.add(task);
            }
        }

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