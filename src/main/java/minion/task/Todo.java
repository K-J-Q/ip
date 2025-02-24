package minion.task;


public class Todo extends Task {

    private static final String KEYWORD_SAVED_TASK = "T|";
    private static final String KEYWORD_TASK = "todo";

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, Boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    @Override
    public String getTask() {
        return String.format("[T][%c] %s", this.isDone ? 'X' : ' ', this.title);
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + String.format("T|%s|%s|", this.isDone ? "1" : "0", this.title);
    }
}
