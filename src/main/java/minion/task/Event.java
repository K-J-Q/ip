package minion.task;

import minion.MinionException;

public class Event extends Task {
    private static final String KEYWORD_SAVED_EVENT = "E|";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_FROM = "from";
    private static final String KEYWORD_TO = "to";

    String fromDateTime = "";
    String toDateTime = "";

    public Event(String title, String fromDateTime, String toDateTime) {
        this.title = title;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public Event(String title, String fromDateTime, String toDateTime, Boolean isDone) {
        this.title = title;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.isDone = isDone;
    }

    @Override
    public String getTask() {
        return String.format("[E][%c] %s (from: %s to: %s)", this.isDone ? 'X' : ' ', this.title, this.fromDateTime, this.toDateTime);
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + String.format("E|%s|%s|%s|%s", this.isDone ? "1" : "0", this.title, this.fromDateTime, this.toDateTime);
    }
}
