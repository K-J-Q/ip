package minion.task;


/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    private static final String KEYWORD_SAVED_TASK = "T|";
    private static final String KEYWORD_TASK = "todo";

    /**
     * Constructs a Todo task with the given title.
     *
     * @param title The title of the task.
     */
    public Todo(String title) {
        this.title = title;
    }

    /**
     * Constructs a Todo task with the given title and completion status. This is generally used for initialising from saved files.
     *
     * @param title  The title of the task.
     * @param isDone The completion status of the task.
     */
    public Todo(String title, Boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String getTask() {
        return String.format("[T][%c] %s", this.isDone ? 'X' : ' ', this.title);
    }

    /**
     * Returns the string representation of the Todo task for saving.
     *
     * @return The string representation of the Todo task for saving.
     */
    @Override
    public String getSaveString() {
        return super.getSaveString() + String.format("T|%s|%s|", this.isDone ? "1" : "0", this.title);
    }
}
