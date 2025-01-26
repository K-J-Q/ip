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
        // Level-1 read back the user's message, unless it is "bye"
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
                if (userInput.startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
                    minionOut.printMessage(tasks.markDone(taskIndex));
                } else if (userInput.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
                    minionOut.printMessage(tasks.unmarkDone(taskIndex));
                } else {
                    Task newTask = new Task(userInput);
                    tasks.addTask(newTask);
                    minionOut.printMessageAndSep("added: " + userInput);
                }
            }
        }

        // Handle final message after "bye" was sent by user
        minionOut.printMessageAndSep("Bye. Hope to see you again soon!");
    }
}
