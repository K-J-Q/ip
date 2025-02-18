package minion.task;

import minion.MinionException;

public class Todo extends Task {

    private static final String KEYWORD_SAVED_TASK = "T|";
    private static final String KEYWORD_TASK = "todo";

    public Todo(String input) throws MinionException {
        if (input.startsWith(KEYWORD_SAVED_TASK)) {
            input = input.substring(KEYWORD_SAVED_TASK.length());
            String[] inputs = input.split("\\|");
            this.isDone = inputs[0].trim().equals("1");
            this.title = inputs[1].trim();
        } else if (input.startsWith(KEYWORD_TASK)) {
            input = input.substring(KEYWORD_TASK.length());
            this.title = input.trim();
            if (this.title.isEmpty()) {
                throw new MinionException("Ahhh!!! Todos must contain (1) description");
            }
        } else {
            throw new MinionException("Unknown start pattern.");
        }
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
