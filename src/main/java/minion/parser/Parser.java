package minion.parser;

import minion.MinionException;
import minion.task.Deadline;
import minion.task.Event;
import minion.task.TaskList;
import minion.task.Todo;
import minion.ui.MessagePrinter;

public class Parser {
    public Command parse(String cmd) {
        switch (cmd) {
        case "bye":
            return (Command.BYE);
        case "list":
            return (Command.LIST_TASK);
        default:
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
            } else {
                return Command.UNKNOWN;
            }
        }
    }

    public Boolean execute(UserCommand cmd, TaskList tasks, MessagePrinter minionOut) throws MinionException {
        String messageOut;
        switch (cmd.command) {
        case BYE:
            return true;
        case LIST_TASK:
            minionOut.printMessageAndSep(tasks.listTasks());
            return false;
        case ADD_TODO:
            try {
                messageOut = tasks.addTask(new Todo(cmd.message));
                minionOut.printMessageAndSep(messageOut);
            } catch (MinionException e) {
                minionOut.printMessageAndSep(e.getMessage());
            }
            return false;
        case ADD_DEADLINE:
            try {
                messageOut = tasks.addTask(new Deadline(cmd.message));
                minionOut.printMessageAndSep(messageOut);
            } catch (MinionException e) {
                minionOut.printMessageAndSep(e.getMessage());
            }
            return false;
        case ADD_EVENT:
            try {
                messageOut = tasks.addTask(new Event(cmd.message));
                minionOut.printMessageAndSep(messageOut);
            } catch (MinionException e) {
                minionOut.printMessageAndSep(e.getMessage());
            }
            return false;
        case TASK_MARK:
            int markIndex = Integer.parseInt(cmd.message.substring("mark".length()).trim()) - 1;
            minionOut.printMessage(tasks.markDone(markIndex));
            return false;
        case TASK_UNMARK:
            int unmarkIndex = Integer.parseInt(cmd.message.substring("unmark".length()).trim()) - 1;
            minionOut.printMessage(tasks.unmarkDone(unmarkIndex));
            return false;
        case DELETE_TASK:
            int deleteIndex = Integer.parseInt(cmd.message.substring("delete".length()).trim()) - 1;
            minionOut.printMessage(tasks.delete(deleteIndex));
            return false;
        case UNKNOWN:
            minionOut.printMessageAndSep("OOPS!!! I'm sorry, but I don't know what that means :-(");
            return false;
        default:
            throw new MinionException("Keyword not caught!");
        }
    }
}