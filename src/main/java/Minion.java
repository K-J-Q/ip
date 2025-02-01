import java.util.Scanner;

public class Minion {

    public static void main(String[] args) {
        // Constant variables
        String logo = "  __  __ _       _             \n"
            + " |  \\/  (_)_ __ (_) ___  _ __  \n"
            + " | |\\/| | | '_ \\| |/ _ \\| '_ \\ \n"
            + " | |  | | | | | | | (_) | | | |\n"
                + " |_|  |_|_|_| |_|_|\\___/|_| |_|\n";
        String name = "Minion";
        String userInput;

        // Initialise the message inputs and outputs
        MessagePrinter minionOut = new MessagePrinter();
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();


        // Display logo and messages
        minionOut.printMessageAndSep("Hello from\n" + logo);
        minionOut.printMessage("Hello! I'm [" + name + "]");
        minionOut.printMessageAndSep("What can I do for you?");

        Boolean toExit = false;
        Task newTask;
        String messageOut;


        while (!toExit) {
            userInput = in.nextLine();
            switch (userInput) {
            case "bye":
                toExit = true;
                break;
            case "list":
                minionOut.printMessageAndSep(tasks.listTasks());
                break;

            default:
                if (userInput.startsWith("todo")){
                    newTask = new Todo(userInput);
                    messageOut = tasks.addTask(newTask);
                    minionOut.printMessageAndSep(messageOut);
                }else if (userInput.startsWith("deadline")){
                    newTask = new Deadline(userInput);
                    messageOut = tasks.addTask(newTask);
                    minionOut.printMessageAndSep(messageOut);
                }else if (userInput.startsWith("event")){
                    newTask = new Event(userInput);
                    messageOut = tasks.addTask(newTask);
                    minionOut.printMessageAndSep(messageOut);
                }
                else if (userInput.startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
                    minionOut.printMessage(tasks.markDone(taskIndex));
                } else if (userInput.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
                    minionOut.printMessage(tasks.unmarkDone(taskIndex));
                }
            }
        }

        // Handle final message after "bye" was sent by user
        minionOut.printMessageAndSep("Bye. Hope to see you again soon!");
    }
}
