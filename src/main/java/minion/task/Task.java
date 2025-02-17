package minion.task;

public class Task {
    protected String title = "";
    protected Boolean isDone = false;

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getTitle() {
        return title;
    }

    public String getTask() {
        return String.format("[%c] %s", this.isDone ? 'X' : ' ', this.title);
    }
}
