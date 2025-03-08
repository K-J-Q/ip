package minion.parser;

import minion.MinionException;
import minion.task.Deadline;
import minion.task.Event;
import minion.task.TaskList;
import minion.task.Todo;
import minion.ui.MessagePrinter;

/**
 * Parses user commands and executes them.
 */
public class Parser {

    private static final String KEYWORD_SAVED_TODO = "T|";
    private static final String KEYWORD_TODO = "todo";
    private static final String KEYWORD_SAVED_EVENT = "E|";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_FROM = "from";
    private static final String KEYWORD_TO = "to";
    private static final String KEYWORD_SAVED_DEADLINE = "D|";
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_BY = "by";

    private static final String KEYWORD_FIND = "find";


    /**
     * Parses a user command.
     *
     * @param cmd The user input text (command) as a string.
     * @return The parsed command as a class Command
     */
    public Command parse(String cmd) {
        switch (cmd) {
        case "bye":
            return (Command.BYE);
        case "list":
            return (Command.LIST_TASK);
        default:
            return getTaskCommand(cmd);
        }
    }

    private static Command getTaskCommand(String cmd) {
        if (cmd.startsWith("mark")) {
            return (Command.TASK_MARK);
        } else if (cmd.startsWith("unmark")) {
            return (Command.TASK_UNMARK);
        } else if (cmd.startsWith("delete")) {
            return (Command.DELETE_TASK);
        } else if (cmd.startsWith("todo")) {
            return Command.ADD_TODO;
        } else if (cmd.startsWith("deadline")) {
            return Command.ADD_DEADLINE;
        } else if (cmd.startsWith("event")) {
            return Command.ADD_EVENT;
        } else if (cmd.startsWith("find")) {
            return (Command.TASK_FIND);
        } else {
            return Command.UNKNOWN;
        }
    }

    private static Command getSavedTaskCommand(String cmd) {
        if (cmd.startsWith("T")) {
            return Command.ADD_TODO;
        } else if (cmd.startsWith("D")) {
            return Command.ADD_DEADLINE;
        } else if (cmd.startsWith("E")) {
            return Command.ADD_EVENT;
        } else {
            return Command.UNKNOWN;
        }
    }

    /**
     * Executes a user command.
     *
     * @param cmd       The processed user command
     * @param tasks     The task list.
     * @param minionOut The message printer.
     * @return True if the command is to exit, false otherwise.
     * @throws MinionException If the command cannot be executed.
     */
    public Boolean execute(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) throws MinionException {
        switch (cmd.command) {
        case BYE:
            return true;
        case LIST_TASK:
            listTask(tasks, minionOut);
            return false;
        case ADD_TODO:
            addTodo(cmd, tasks, minionOut);
            return false;
        case ADD_DEADLINE:
            addDeadline(cmd, tasks, minionOut);
            return false;
        case ADD_EVENT:
            addEvent(cmd, tasks, minionOut);
            return false;
        case TASK_MARK:
            markTask(cmd, tasks, minionOut);
            return false;
        case TASK_UNMARK:
            unmarkTask(cmd, tasks, minionOut);
            return false;
        case DELETE_TASK:
            deleteTask(cmd, tasks, minionOut);
            return false;
        case TASK_FIND:
            findTask(cmd, tasks, minionOut);
            return false;
        case UNKNOWN:
            minionOut.printMessageAndSep("OOPS!!! I'm sorry, but I don't know what that means :-(");
            return false;
        default:
            throw new MinionException("Keyword not caught!");
        }
    }

    private void findTask(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) {
        String messageOut = "Here are the matching tasks in your list:" + System.lineSeparator();
        String findText = cmd.message.substring(KEYWORD_FIND.length()).trim();
        messageOut += tasks.findTitle(findText);

        minionOut.printMessageAndSep(messageOut);
    }

    private static void listTask(TaskList tasks, MessagePrinter minionOut) {
        minionOut.printMessageAndSep(tasks.listTasks());
    }

    private static void deleteTask(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) {
        try {
            int deleteIndex = Integer.parseInt(cmd.message.substring("delete".length()).trim()) - 1;
            minionOut.printMessage(tasks.delete(deleteIndex));
        } catch (NumberFormatException e) {
            minionOut.printMessage("I don't see a number here....");
        }
    }

    private static void unmarkTask(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) {
        try {
            int unmarkIndex = Integer.parseInt(cmd.message.substring("unmark".length()).trim()) - 1;
            minionOut.printMessage(tasks.unmarkDone(unmarkIndex));
        } catch (NumberFormatException e) {
            minionOut.printMessage("I don't see a number here....");
        }
    }

    private static void markTask(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) {
        try {
            int markIndex = Integer.parseInt(cmd.message.substring("mark".length()).trim()) - 1;
            minionOut.printMessage(tasks.markDone(markIndex));
        } catch (NumberFormatException e) {
            minionOut.printMessage("I don't see a number here....");
        }
    }


    private static void addEvent(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) {
        String messageOut;
        String message = cmd.message;
        String title, fromDateTime = "", toDateTime = "";
        try {
            if (message.startsWith(KEYWORD_EVENT)) {
                message = message.substring(KEYWORD_EVENT.length());
                String[] userInputs = message.split("/");
                if (userInputs.length != 3 || userInputs[0].trim().isEmpty()) {
                    throw new MinionException("Ahhh!!! The events must include (1) description, (2) from datetime and (3) to datetime");
                }
                title = userInputs[0].trim();
                for (int i = 1; i < 3; i++) {
                    if (userInputs[i].startsWith(KEYWORD_FROM)) {
                        fromDateTime = userInputs[i].substring(KEYWORD_FROM.length()).trim();
                        if (fromDateTime.isEmpty()) {
                            throw new MinionException("Ahhh!!! from datetime is empty");
                        }
                    } else if (userInputs[i].startsWith(KEYWORD_TO)) {
                        toDateTime = userInputs[i].substring(KEYWORD_TO.length()).trim();
                        if (toDateTime.isEmpty()) {
                            throw new MinionException("Ahhh!!! to datetime is empty");
                        }
                    } else {
                        throw new MinionException("Ahhh!!! I don't understand this: /" + userInputs[i]);
                    }
                }
                if (fromDateTime.isEmpty() || toDateTime.isEmpty()) {
                    throw new MinionException("OOPS!!! The event timings are invalid.");
                }
            } else {
                throw new MinionException("Unknown start pattern.");
            }
            messageOut = tasks.addTask(new Event(title, fromDateTime, toDateTime));
            minionOut.printMessageAndSep(messageOut);
        } catch (MinionException e) {
            minionOut.printMessageAndSep(e.getMessage());
        }
    }


    private static void addDeadline(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) {
        String messageOut;
        String message = cmd.message;
        String title, dueDate;
        try {
            if (message.startsWith(KEYWORD_DEADLINE)) {
                message = message.substring(KEYWORD_DEADLINE.length());
                String[] userInputs = message.split("/");
                if (userInputs.length != 2 || userInputs[0].trim().isEmpty()) {
                    throw new MinionException("Ahhh!!! The deadlines must include (1) description and (2) done by datetime");
                }
                title = userInputs[0].trim();
                if (userInputs[1].startsWith(KEYWORD_BY)) {
                    dueDate = userInputs[1].substring(KEYWORD_BY.length()).trim();
                    if (dueDate.isEmpty()) {
                        throw new MinionException("Ahhh!!! Deadline is empty");
                    }
                } else {
                    throw new MinionException("Ahhh!!! I don't understand this: /" + userInputs[1]);
                }
            } else {
                throw new MinionException("Unknown start pattern.");
            }
            messageOut = tasks.addTask(new Deadline(title, dueDate));
            minionOut.printMessageAndSep(messageOut);
        } catch (MinionException e) {
            minionOut.printMessageAndSep(e.getMessage());
        }
    }

    /**
     * Gets a saved deadline task.
     *
     * @param cmd The parsed user command
     * @return The saved deadline task.
     * @throws MinionException If the task cannot be parsed.
     */
    public Deadline getSavedDeadline(UserCommand cmd) throws MinionException {
        String message = cmd.message;
        String title, dueDate;
        boolean isDone;
        if (message.startsWith(KEYWORD_SAVED_DEADLINE)) {
            message = message.substring(KEYWORD_SAVED_DEADLINE.length());


            String[] userInputs = message.split("\\|");

            title = userInputs[1].trim();
            dueDate = userInputs[2].trim();
            isDone = userInputs[0].trim().equals("1");
        } else {
            throw new MinionException("Unknown start pattern.");
        }
        return new Deadline(title, dueDate, isDone);
    }

    /**
     * Gets a saved todo task.
     *
     * @param cmd The parsed user command
     * @return The saved todo task.
     * @throws MinionException If the task cannot be parsed.
     */
    public Todo getSavedTodo(UserCommand cmd) throws MinionException {
        String message = cmd.message;
        String title;
        boolean isDone;
        if (message.startsWith(KEYWORD_SAVED_TODO)) {
            message = message.substring(KEYWORD_SAVED_TODO.length());
            String[] userInputs = message.split("\\|");
            isDone = userInputs[0].trim().equals("1");
            title = userInputs[1].trim();
        } else {
            throw new MinionException("Unknown start pattern.");
        }
        return new Todo(title, isDone);
    }

    /**
     * Gets a saved event task.
     *
     * @param cmd The parsed user command
     * @return The saved event task.
     * @throws MinionException If the task cannot be parsed.
     */
    public Event getSavedEvent(UserCommand cmd) throws MinionException {
        String message = cmd.message;
        String title, fromDateTime, toDateTime;
        boolean isDone;
        if (message.startsWith(KEYWORD_SAVED_EVENT)) {
            message = message.substring(KEYWORD_SAVED_EVENT.length());
            String[] inputs = message.split("\\|");
            isDone = inputs[0].trim().equals("1");
            title = inputs[1].trim();
            fromDateTime = inputs[2].trim();
            toDateTime = inputs[3].trim();
        } else {
            throw new MinionException("Unknown start pattern.");
        }
        return new Event(title, fromDateTime, toDateTime, isDone);
    }

    private static void addTodo(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) {
        String messageOut;
        String message = cmd.message;
        String title;
        try {
            if (message.startsWith(KEYWORD_TODO)) {
                title = message.substring(KEYWORD_TODO.length()).trim();
                if (title.isEmpty()) {
                    throw new MinionException("Ahhh!!! Todos must contain (1) description");
                }
            } else {
                throw new MinionException("Unknown start pattern.");
            }
            messageOut = tasks.addTask(new Todo(title));
            minionOut.printMessageAndSep(messageOut);
        } catch (MinionException e) {
            minionOut.printMessageAndSep(e.getMessage());
        }
    }

    /**
     * Parses a saved task command.
     *
     * @param cmd The saved task command as a string.
     * @return The parsed command.
     */
    public Command parseSaved(String cmd) {
        return getSavedTaskCommand(cmd);
    }
}