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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task1 = (Task) o;

        return task.equals(task1.task);
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }
}
