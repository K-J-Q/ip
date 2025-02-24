package minion.task;

/**
 * Represents a generic task.
 */
public class Task {
    protected String title = "";
    protected Boolean isDone = false;

    /**
     * Returns the completion status of the task.
     * 
     * @return True if the task is done, false otherwise.
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     * 
     * @param done The completion status to be set.
     */
    public void setDone(Boolean done) {
        isDone = done;
    }

    /**
     * Returns the title of the task.
     * 
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return The string representation of the task.
     */
    public String getTask() {
        return String.format("[%c] %s", this.isDone ? 'X' : ' ', this.title);
    }

    /**
     * Returns the string representation of the task for saving.
     * 
     * @return The string representation of the task for saving.
     */
    public String getSaveString() {
        return "";
    }
}
