public class TaskList {
    private String taskNames[] = new String[100]; // maximum of 100 tasks
    private int taskIndex = 0;

    public void addTask(String taskName){
        this.taskNames[this.taskIndex] = taskName;
        this.taskIndex++;
    }

    public String listTasks(){
        String out = "";

        for(int i =0; i< this.taskIndex; i++){
            out += (i+1) + ". " + this.taskNames[i]+ "\n";
        }

        // if there are tasks, remove trailing new line
        if (out.length() != 0){
            out = out.substring(0, out.length()-1);
        } else {
            out = "You don't have any items!";
        }
        return out;
    }
}
