package minion.task;

import minion.MinionException;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private static final String KEYWORD_SAVED_EVENT = "E|";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_FROM = "from";
    private static final String KEYWORD_TO = "to";

    String fromDateTime = "";
    String toDateTime = "";

    /**
     * Constructs an Event task with the given title, start datetime, and end datetime.
     * 
     * @param title The title of the task.
     * @param fromDateTime The start datetime of the event.
     * @param toDateTime The end datetime of the event.
     */
    public Event(String title, String fromDateTime, String toDateTime) {
        this.title = title;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Constructs an Event task with the given title, start datetime, end datetime, and completion status.This is generally used for initialising from saved files.
     * 
     * @param title The title of the task.
     * @param fromDateTime The start datetime of the event.
     * @param toDateTime The end datetime of the event.
     * @param isDone The completion status of the task.
     */
    public Event(String title, String fromDateTime, String toDateTime, Boolean isDone) {
        this.title = title;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Event task.
     * 
     * @return The string representation of the Event task.
     */
    @Override
    public String getTask() {
        return String.format("[E][%c] %s (from: %s to: %s)", this.isDone ? 'X' : ' ', this.title, this.fromDateTime, this.toDateTime);
    }

    /**
     * Returns the string representation of the Event task for saving.
     * 
     * @return The string representation of the Event task for saving.
     */
    @Override
    public String getSaveString() {
        return super.getSaveString() + String.format("E|%s|%s|%s|%s", this.isDone ? "1" : "0", this.title, this.fromDateTime, this.toDateTime);
    }
}
