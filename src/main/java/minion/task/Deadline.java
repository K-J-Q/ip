package minion.task;

public class Deadline extends Task {
    private String dueDate = "";

    public Deadline(String title, String doneBy) {
        this.title = title;
        this.dueDate = doneBy;
    }

    public Deadline(String title, String doneBy, Boolean isDone) {
        this.title = title;
        this.dueDate = doneBy;
        this.isDone = isDone;
    }

    @Override
    public String getTask() {
        return String.format("[D][%c] %s (by: %s)", this.isDone ? 'X' : ' ', this.title, this.dueDate);
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + String.format("D|%s|%s|%s", this.isDone ? "1" : "0", this.title, this.dueDate);
    }
}
