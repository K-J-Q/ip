package minion.ui;

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

    // adds an indentation of 4 spaces "    "
    private String addIndentation(String message) {
        // add indentation after every new line too
        message = message.replace("\n", "\n" + indentation);
        return this.indentation + message;
    }

    public void printSep() {
        System.out.println(addIndentation(lineSeparator));
    }

    public void printMessage(String message) {
        System.out.println(addIndentation(message));
    }

    public void printMessageAndSep(String message) {
        this.printSep();
        this.printMessage(message);
        this.printSep();
    }

    public void printIntro() {
        this.printMessageAndSep("Hello from\n" + LOGO);
        this.printMessage("Hello! I'm [" + NAME + "]");
        this.printMessageAndSep("What can I do for you?");
    }
}
