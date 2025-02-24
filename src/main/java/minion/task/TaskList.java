package minion.task;

import java.util.ArrayList;

import minion.MinionException;
import minion.Storage;
import minion.parser.Parser;
import minion.parser.UserCommand;

/**
 * Manages the list of tasks.
 * Any tasks that are extended from class Task can be included here.
 */
public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>(); // Create an ArrayList object
    Storage taskStorage = new Storage();

    /**
     * Checks if the given index is valid.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    private Boolean isValidIndex(int index) {
        return (index >= 0 && index < this.tasks.size());
    }

    /**
     * Loads a saved task from the given string.
     *
     * @param task   The string representation of the task.
     * @param parser The parser class
     * @throws MinionException If the task cannot be processed.
     */
    private void loadSavedTask(String task, Parser parser) throws MinionException {
        if (task.isEmpty()) {
            return;
        }
        UserCommand cmd = new UserCommand();
        cmd.command = parser.parseSaved(task);
        cmd.message = task;

        switch (cmd.command) {
        case ADD_TODO:
            this.tasks.add(parser.getSavedTodo(cmd));
            break;
        case ADD_DEADLINE:
            this.tasks.add(parser.getSavedDeadline(cmd));
            break;
        case ADD_EVENT:
            this.tasks.add(parser.getSavedEvent(cmd));
            break;
        default:
            throw new MinionException("Unable to process task: " + task);
        }
    }

    /**
     * Loads tasks from storage.
     *
     * @param parser The parser class
     * @throws MinionException If the tasks cannot be loaded.
     */
    public void loadTasks(Parser parser) throws MinionException {
        String tasks = taskStorage.getString();
        for (String task : tasks.split(System.lineSeparator())) {
            loadSavedTask(task, parser);
        }
    }

    private void saveTasks() {
        String[] taskStrs = new String[tasks.size()];
        int i = 0;
        for (Task task : tasks) {
            taskStrs[i++] = task.getSaveString();
        }
        taskStorage.saveString(taskStrs);
    }

    /**
     * Adds a task to the list and saves the updated list.
     *
     * @param task The task to be added.
     * @return Message confirming the addition of the task, to be printed to the console.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        saveTasks();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.", task.getTask(), this.tasks.size());
    }

    /**
     * Returns the string representation of a task at the given index.
     *
     * @param index The index of the task.
     * @return The string representation of the task.
     * <p>
     * Example:
     * If the task at index 0 is a Todo task with the title "Read book" and is not done,
     * the returned string will be "[T][ ] Read book".
     */
    public String getTaskString(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        Task task = this.tasks.get(index);
        saveTasks();
        return String.format("[%c] %s", task.isDone() ? 'X' : ' ', task.getTitle());
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return A string representation of all tasks.
     */
    public String listTasks() {
        StringBuilder out = new StringBuilder("Here are the tasks in your list:\n");
        int i = 1;
        for (Task task : this.tasks) {
            out.append(String.format("%d.%s\n", i++, task.getTask()));
        }

        // if there are tasks, remove trailing new line character
        if (!out.isEmpty()) {
            out = new StringBuilder(out.substring(0, out.length() - 1));
        } else {
            out = new StringBuilder("You don't have any items!");
        }
        return out.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return A message confirming the task has been marked as done.
     */
    public String markDone(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        this.tasks.get(index).setDone(true);
        saveTasks();
        return "Nice! I've marked this task as done:\n  " + getTaskString(index);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return A message confirming the task has been marked as not done.
     */
    public String unmarkDone(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        this.tasks.get(index).setDone(false);
        saveTasks();
        return "OK, I've marked this task as not done yet:\n  " + getTaskString(index);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return A message confirming the deletion of the task.
     */
    public String delete(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        String taskStr = getTaskString(index);
        this.tasks.remove(index);
        saveTasks();
        return "Noted. I've removed this task:\n" + taskStr + "\nNow you have " + this.tasks.size() + " task(s) in the list.";
    }

    public String findTitle(String findText) {
        StringBuilder out = new StringBuilder();
        int i = 1;
        for (Task task : this.tasks) {
            if (task.getTitle().contains(findText)) {
                out.append(String.format("%d.%s %s", i++, task.getTask(), System.lineSeparator()));
            }
        }
        return out.toString();
    }
}
