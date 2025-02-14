package minion.task;

import minion.MinionException;

public class Event extends Task {
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_FROM = "from";
    private static final String KEYWORD_TO = "to";

    String fromDateTime = "";
    String toDateTime = "";

    public Event(String userInput) throws MinionException {
        userInput = userInput.substring(KEYWORD_EVENT.length());
        String[] userInputs = userInput.split("/");
        if (userInputs.length != 3 || userInputs[0].trim().isEmpty()) {
            throw new MinionException("Ahhh!!! The events must include (1) description, (2) from datetime and (3) to datetime");
        }
        this.title = userInputs[0].trim();
        for (int i = 1; i < 3; i++) {
            if (userInputs[i].startsWith(KEYWORD_FROM)) {
                this.fromDateTime = userInputs[i].substring(KEYWORD_FROM.length()).trim();
                if (this.fromDateTime.isEmpty()){
                    throw new MinionException("Ahhh!!! from datetime is empty");
                }
            } else if (userInputs[i].startsWith(KEYWORD_TO)) {
                this.toDateTime = userInputs[i].substring(KEYWORD_TO.length()).trim();
                if(this.toDateTime.isEmpty()) {
                    throw new MinionException("Ahhh!!! to datetime is empty");
                }
            } else {
                throw new MinionException("Ahhh!!! I don't understand this: /" + userInputs[i]);
            }
        }
        if (this.fromDateTime.isEmpty() || this.toDateTime.isEmpty()) {
            throw new MinionException("OOPS!!! The event timings are invalid.");
        }
    }

    Event(String title, String fromDateTime, String toDateTime) {
        this.title = title;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String getTask() {
        return String.format("[E][%c] %s (from: %s to: %s)", this.isDone ? 'X' : ' ', this.title, this.fromDateTime, this.toDateTime);
    }
}
