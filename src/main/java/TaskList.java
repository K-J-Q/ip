public class TaskList {
    private Task tasks[] = new Task[100]; // maximum of 100 tasks
    private int taskIndex = 0;

    private Boolean isValidIndex(int index) {
        return (index >= 0 && index < this.taskIndex);
    }

    public String addTask(Task task) {
        this.tasks[this.taskIndex] = task;
        this.taskIndex++;
        return String.format("added: %s", task.getTitle());
    }

    public String getTaskString(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        Task task = this.tasks[index];
        return String.format("[%c] %s", task.isDone() ? 'X' : ' ', task.getTitle());
    }

    public String listTasks() {
        String out = "Here are the tasks in your list:\n";
        Task task;
        for (int i = 0; i < this.taskIndex; i++) {
            task = tasks[i];
            out += String.format("%d. [%c] %s\n", i + 1, task.isDone() ? 'X' : ' ', task.getTitle());
        }

        // if there are tasks, remove trailing new line
        if (out.length() != 0) {
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
        this.tasks[index].setDone(true);
        return "Nice! I've marked this task as done:\n  " + getTaskString(index);
    }

    public String unmarkDone(int index) {
        if (!isValidIndex(index)) {
            return "Invalid Selection!";
        }
        this.tasks[index].setDone(false);
        return "OK, I've marked this task as not done yet:\n  " + getTaskString(index);
    }
}
