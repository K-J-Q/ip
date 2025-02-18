package minion.task;

import minion.MinionException;

public class Deadline extends Task {
    private static final String KEYWORD_SAVED_DEADLINE = "D|";
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_BY = "by";
    private String dueDate = "";

    // Expect user input with one '/'  as separator for by
    public Deadline(String input) throws MinionException {
        if (input.startsWith(KEYWORD_SAVED_DEADLINE)) {
            input = input.substring(KEYWORD_SAVED_DEADLINE.length());
            String[] inputs = input.split("\\|");
            this.isDone = inputs[0].trim().equals("1");
            this.title = inputs[1].trim();
            this.dueDate = inputs[2].trim();
        } else if (input.startsWith((KEYWORD_DEADLINE))) {
            input = input.substring(KEYWORD_DEADLINE.length());
            String[] userInputs = input.split("/");
            if (userInputs.length != 2 || userInputs[0].trim().isEmpty()) {
                throw new MinionException("Ahhh!!! The deadlines must include (1) description and (2) done by datetime");
            }
            this.title = userInputs[0].trim();
            if (userInputs[1].startsWith(KEYWORD_BY)) {
                this.dueDate = userInputs[1].substring(KEYWORD_BY.length()).trim();
                if (this.dueDate.isEmpty()) {
                    throw new MinionException("Ahhh!!! Deadline is empty");
                }
            } else {
                throw new MinionException("Ahhh!!! I don't understand this: /" + userInputs[1]);
            }
        } else {
            throw new MinionException("Unknown start pattern.");
        }
    }

    Deadline(String title, String doneBy) {
        this.title = title;
        this.dueDate = doneBy;
    }

    @Override
    public String getTask() {
        return String.format("[D][%c] %s (by: %s)", this.isDone ? 'X' : ' ', this.title, this.dueDate);
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + String.format("D|%s|%s|%s", this.isDone ? "1" : "0", this.title, this.dueDate);
    }
}
