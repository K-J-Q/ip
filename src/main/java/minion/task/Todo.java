package minion.task;

import minion.MinionException;

public class Todo extends Task {

    private static final String KEYWORD_TASK = "task";

    public Todo(String userInput) throws MinionException {
        userInput = userInput.substring(KEYWORD_TASK.length());
        this.title = userInput.trim();
        if (this.title.isEmpty()) {
            throw new MinionException("Ahhh!!! Todos must contain (1) description");
        }
    }

    @Override
    public String getTask() {
        return String.format("[T][%c] %s", this.isDone ? 'X' : ' ', this.title);
    }
}
