package minion.task;

import java.util.ArrayList;

import minion.MinionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private static final String FILE_PATH = "./taskInfo.txt";

    ArrayList<Task> tasks = new ArrayList<Task>(); // Create an ArrayList object

    File f = new File(FILE_PATH);


    private Boolean isValidIndex(int index) {
        return (index >= 0 && index < this.tasks.size());
    }


    private void saveTasks() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(FILE_PATH);

            for (int i = 0; i < this.taskIndex; i++) {
                fw.append(this.tasks[i].getSaveString());
                fw.append(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save tasks!");
        }
    }

    private void loadTask(String task) throws MinionException {
        String taskType = task.substring(0, 1);
        switch (taskType) {
        case "T":
            this.tasks[this.taskIndex] = new Todo(task);
            break;
        case "D":
            this.tasks[this.taskIndex] = new Deadline(task);
            break;
        case "E":
            this.tasks[this.taskIndex] = new Event(task);
            break;
        default:
            throw new MinionException("Unable to process task: " + task);
        }
        this.taskIndex++;
    }

    public void loadTasks() throws MinionException {
        if (!f.exists()) {
            return;
        }
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                loadTask(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load file!");
        }
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

        return "Noted. I've removed this task:\n" + taskStr + "\nNow you have " + this.tasks.size() + " task(s) in the list.";
    }
}
