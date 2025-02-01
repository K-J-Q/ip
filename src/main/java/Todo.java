public class Todo extends Task{
    Todo(String userInput) {
        userInput = userInput.substring("task".length());
        this.title = userInput.trim();
    }

    @Override
    public String getTask() {
        return String.format("[T][%c] %s", this.isDone ? 'X' : ' ', this.title);
    }
}
