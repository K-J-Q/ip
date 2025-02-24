package minion.task;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private String dueDate = "";

    /**
     * Constructs a Deadline task with the given title and due date.
     * 
     * @param title The title of the task.
     * @param doneBy The due date of the task.
     */
    public Deadline(String title, String doneBy) {
        this.title = title;
        this.dueDate = doneBy;
    }

    /**
     * Constructs a Deadline task with the given title, due date, and completion status. This is generally used for initialising from saved files.
     * 
     * @param title The title of the task.
     * @param doneBy The due date of the task.
     * @param isDone The completion status of the task.
     */
    public Deadline(String title, String doneBy, Boolean isDone) {
        this.title = title;
        this.dueDate = doneBy;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Deadline task.
     * 
     * @return The string representation of the Deadline task.
     */
    @Override
    public String getTask() {
        return String.format("[D][%c] %s (by: %s)", this.isDone ? 'X' : ' ', this.title, this.dueDate);
    }

    /**
     * Returns the string representation of the Deadline task for saving.
     * 
     * @return The string representation of the Deadline task for saving.
     */
    @Override
    public String getSaveString() {
        return super.getSaveString() + String.format("D|%s|%s|%s", this.isDone ? "1" : "0", this.title, this.dueDate);
    }
}
