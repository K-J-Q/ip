package minion.task;

import java.util.ArrayList;

import minion.MinionException;
import minion.Storage;
import minion.parser.Parser;
import minion.parser.UserCommand;


public class TaskList {
    private static final String FILE_PATH = "./taskInfo.txt";

    ArrayList<Task> tasks = new ArrayList<>(); // Create an ArrayList object
    Storage taskStorage = new Storage();

    private Boolean isValidIndex(int index) {
        return (index >= 0 && index < this.tasks.size());
    }

    private void loadSavedTask(String task, Parser parser) throws MinionException {
        if (task.isEmpty()) {
            return;
        }
        UserCommand cmd = new UserCommand();
        cmd.command = parser.parseSaved(task);
        ;
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


    public String addTask(Task task) {
        this.tasks.add(task);
        saveTasks();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.", task.getTask(), this.tasks.size());
    }

    public String getTaskString(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        Task task = this.tasks.get(index);
        saveTasks();
        return String.format("[%c] %s", task.isDone() ? 'X' : ' ', task.getTitle());
    }

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

    public String markDone(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        this.tasks.get(index).setDone(true);
        saveTasks();
        return "Nice! I've marked this task as done:\n  " + getTaskString(index);
    }

    public String unmarkDone(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        this.tasks.get(index).setDone(false);
        saveTasks();
        return "OK, I've marked this task as not done yet:\n  " + getTaskString(index);
    }

    public String delete(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        String taskStr = getTaskString(index);
        this.tasks.remove(index);
        saveTasks();
        return "Noted. I've removed this task:\n" + taskStr + "\nNow you have " + this.tasks.size() + " task(s) in the list.";
    }
}
