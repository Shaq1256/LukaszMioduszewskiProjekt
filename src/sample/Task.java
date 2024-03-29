package sample;

import java.time.LocalDate;

public class Task {
    private String task;
//    private SimpleStringProperty taskStatus;
    private String taskStatus;
    private String deadLineTask;

    public Task(String task, String taskStatus, String deadLineTask) {
        this.task = task;
        this.taskStatus = taskStatus;
        this.deadLineTask = deadLineTask;
    }

    public String getDeadLineTask() {
        return deadLineTask;
    }

    public void setDeadLineTask(String deadLineTask) {
        this.deadLineTask = deadLineTask;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", deadLineTask=" + deadLineTask +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task1 = (Task) o;

        if (!task.equals(task1.task)) return false;
        return taskStatus.equals(task1.taskStatus);
    }

    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + taskStatus.hashCode();
        return result;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Task task1 = (Task) o;
//
//        return task.equals(task1.task);
//    }
//
//    @Override
//    public int hashCode() {
//        return task.hashCode();
//    }
}
