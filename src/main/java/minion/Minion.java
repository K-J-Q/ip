package minion;

import minion.parser.Parser;
import minion.parser.UserCommand;
import minion.task.*;
import minion.ui.MessagePrinter;

import java.util.Scanner;

public class Minion {

    public static void main(String[] args) {
        // Constant variables
        final String LOGO = """
                  __  __ _       _
                 |  \\/  (_)_ __ (_) ___  _ __
                 | |\\/| | | '_ \\| |/ _ \\| '_ \\
                 | |  | | | | | | | (_) | | | |
                 |_|  |_|_|_| |_|_|\\___/|_| |_|
                """;
        final String NAME = "Minion";

        // Initialise the message inputs and outputs
        MessagePrinter minionOut = new MessagePrinter();
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Parser parser = new Parser();

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
        UserCommand userCommand = new UserCommand();

        // Keep reading the user's input until exit condition reached
        while (!toExit) {
            userCommand.message = in.nextLine();
            userCommand.command = parser.parse(userCommand.message);
            try {
                toExit = parser.execute(userCommand, tasks, minionOut);
            } catch (MinionException e) {
                throw new RuntimeException(e);
            }
        }

        // Handle final message after "bye" was sent by user
        minionOut.printMessageAndSep("Bye. Hope to see you again soon!");
    }
}
