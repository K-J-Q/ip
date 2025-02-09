public class TaskList {
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS]; // maximum of 100 tasks
    private int taskIndex = 0;

    private Boolean isValidIndex(int index) {
        return (index >= 0 && index < this.taskIndex);
    }

    public String addTask(Task task) {

        this.tasks[this.taskIndex] = task;
        this.taskIndex++;
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.", task.getTask(), this.taskIndex);
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
            out += String.format("%d.%s\n", i + 1, tasks[i].getTask());
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
