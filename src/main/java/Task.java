public class Task {
    private String title;
    private Boolean isDone = false;

    Task(String title) {
        this.title = title;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getTitle() {
        return title;
    }
}
