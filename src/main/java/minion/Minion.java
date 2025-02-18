package minion;

import minion.task.*;
import minion.ui.MessagePrinter;

import java.util.Scanner;

public class Minion {

    public static void main(String[] args) {
        // Constant variables
        final String LOGO = "  __  __ _       _\n"
                + " |  \\/  (_)_ __ (_) ___  _ __\n"
                + " | |\\/| | | '_ \\| |/ _ \\| '_ \\\n"
                + " | |  | | | | | | | (_) | | | |\n"
                + " |_|  |_|_|_| |_|_|\\___/|_| |_|\n";
        final String NAME = "Minion";
        String userInput;

        // Initialise the message inputs and outputs
        MessagePrinter minionOut = new MessagePrinter();
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();

        try {
            tasks.loadTasks();
        } catch (MinionException e) {
            minionOut.printMessageAndSep("Unable to load task from memory!");
        }


        // Display logo and messages
        minionOut.printMessageAndSep("Hello from\n" + LOGO);
        minionOut.printMessage("Hello! I'm [" + NAME + "]");
        minionOut.printMessageAndSep("What can I do for you?");

        Boolean toExit = false;
        Task newTask;
        String messageOut;

        // Keep reading the user's input until exit condition reached
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
                if (userInput.startsWith("todo")) {
                    try {
                        messageOut = tasks.addTask(new Todo(userInput));
                        minionOut.printMessageAndSep(messageOut);
                    } catch (MinionException e) {
                        minionOut.printMessageAndSep(e.getMessage());
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        messageOut = tasks.addTask(new Deadline(userInput));
                        minionOut.printMessageAndSep(messageOut);
                    } catch (MinionException e) {
                        minionOut.printMessageAndSep(e.getMessage());
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        messageOut = tasks.addTask(new Event(userInput));
                        minionOut.printMessageAndSep(messageOut);
                    } catch (MinionException e) {
                        minionOut.printMessageAndSep(e.getMessage());
                    }
                } else if (userInput.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(userInput.substring("mark".length()).trim()) - 1;
                    minionOut.printMessage(tasks.markDone(taskIndex));
                } else if (userInput.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(userInput.substring("unmark".length()).trim()) - 1;
                    minionOut.printMessage(tasks.unmarkDone(taskIndex));
                } else {
                    minionOut.printMessageAndSep("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }

        // Handle final message after "bye" was sent by user
        minionOut.printMessageAndSep("Bye. Hope to see you again soon!");
    }
}
