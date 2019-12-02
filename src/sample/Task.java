package sample;

import javafx.beans.property.SimpleStringProperty;

public class Task {
    private SimpleStringProperty task;

    public Task(String task) {
        this.task = new SimpleStringProperty(task);
    }

    public String getTask() {
        return task.get();
    }

    public SimpleStringProperty taskProperty() {
        return task;
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    @Override
    public String toString() {
        return "Task =" + task ;
    }
}
