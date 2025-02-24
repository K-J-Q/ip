package minion;

import minion.parser.Parser;
import minion.parser.UserCommand;
import minion.task.TaskList;
import minion.ui.MessagePrinter;

import java.util.Scanner;

public class Minion {

    public static void main(String[] args) {

        // Initialise the message inputs and outputs
        MessagePrinter minionOut = new MessagePrinter();
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Parser parser = new Parser();

        try {
            tasks.loadTasks(parser);
        } catch (MinionException e) {
            minionOut.printMessageAndSep("Unable to load task from memory!");
        }

        Boolean isExiting = false;
        UserCommand userCommand = new UserCommand();

        minionOut.printIntro();
        // Keep reading the user's input until exit condition reached
        while (!isExiting) {
            userCommand.message = in.nextLine();
            userCommand.command = parser.parse(userCommand.message);
            try {
                isExiting = parser.execute(userCommand, tasks, minionOut);
            } catch (MinionException e) {
                throw new RuntimeException(e);
            }
        }

        // Handle final message after "bye" was sent by user
        minionOut.printMessageAndSep("Bye. Hope to see you again soon!");
    }
}
