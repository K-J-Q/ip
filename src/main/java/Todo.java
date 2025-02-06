public class Todo extends Task {

    private static final String KEYWORD_TASK = "task";

    Todo(String userInput) {
        userInput = userInput.substring(KEYWORD_TASK.length());
        this.title = userInput.trim();
    }

    @Override
    public String getTask() {
        return String.format("[T][%c] %s", this.isDone ? 'X' : ' ', this.title);
    }
}
