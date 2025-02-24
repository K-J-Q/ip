package minion.ui;

/**
 * The MessagePrinter class prints messages with a specific format.
 * This includes separator, indentation, and logos.
 */
public class MessagePrinter {
    // Constant variables
    final String LOGO = """
              __  __ _       _
             |  \\/  (_)_ __ (_) ___  _ __
             | |\\/| | | '_ \\| |/ _ \\| '_ \\
             | |  | | | | | | | (_) | | | |
             |_|  |_|_|_| |_|_|\\___/|_| |_|
            """;
    final String NAME = "Minion";
    private String indentation = "   ";
    private String lineSeparator = "____________________________________________________________";

    /**
     * Adds an indentation of 4 spaces "    " to the given message.
     * 
     * @param message The message to be indented.
     * @return The indented message.
     */
    private String addIndentation(String message) {
        // add indentation after every new line too
        message = message.replace("\n", "\n" + indentation);
        return this.indentation + message;
    }

    /**
     * Prints a line separator with indentation.
     */
    public void printSep() {
        System.out.println(addIndentation(lineSeparator));
    }

    /**
     * Prints a given message with indentation.
     * 
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(addIndentation(message));
    }

    /**
     * Prints a given message with indentation and surrounds it with line separators.
     * 
     * @param message The message to be printed.
     */
    public void printMessageAndSep(String message) {
        this.printSep();
        this.printMessage(message);
        this.printSep();
    }

    /**
     * Prints the introductory message with the logo and name.
     */
    public void printIntro() {
        this.printMessageAndSep("Hello from\n" + LOGO);
        this.printMessage("Hello! I'm [" + NAME + "]");
        this.printMessageAndSep("What can I do for you?");
    }
}
