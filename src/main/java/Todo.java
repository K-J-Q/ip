public class Todo extends Task {

    private static final String KEYWORD_TASK = "task";

    Todo(String userInput) {
        userInput = userInput.substring(KEYWORD_TASK.length());
        this.title = userInput.trim();
        if (this.title.isEmpty()) {
            throw new IllegalArgumentException("Ahhh!!! Todos must contain (1) description");
        }
    }

    @Override
    public String getTask() {
        return String.format("[T][%c] %s", this.isDone ? 'X' : ' ', this.title);
    }
}
