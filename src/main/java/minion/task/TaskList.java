package minion.task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<Task>(); // Create an ArrayList object

    private Boolean isValidIndex(int index) {
        return (index >= 0 && index < this.tasks.size());
    }

    public String addTask(Task task) {

        this.tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.", task.getTask(), this.tasks.size());
    }

    public String getTaskString(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        Task task = this.tasks.get(index);
        return String.format("[%c] %s", task.isDone() ? 'X' : ' ', task.getTitle());
    }

    public String listTasks() {
        String out = "Here are the tasks in your list:\n";
        int i = 1;
        for (Task task : this.tasks) {
            out += String.format("%d.%s\n", i++, task.getTask());
        }

        // if there are tasks, remove trailing new line character
        if (!out.isEmpty()) {
            out = out.substring(0, out.length() - 1);
        } else {
            out = "You don't have any items!";
        }
        return out;
    }

    public String markDone(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        this.tasks.get(index).setDone(true);
        return "Nice! I've marked this task as done:\n  " + getTaskString(index);
    }

    public String unmarkDone(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        this.tasks.get(index).setDone(false);
        return "OK, I've marked this task as not done yet:\n  " + getTaskString(index);
    }

    public String delete(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        String taskStr = getTaskString(index);
        this.tasks.remove(index);

        return "Noted. I've removed this task:\n" + taskStr + "\nNow you have " + this.tasks.size() + " task(s) in the list.";
    }
}
