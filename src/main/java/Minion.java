public class Minion {
    public static void main(String[] args) {
        // Constant variables
        String logo = "  __  __ _       _             \n"
            + " |  \\/  (_)_ __ (_) ___  _ __  \n"
            + " | |\\/| | | '_ \\| |/ _ \\| '_ \\ \n"
            + " | |  | | | | | | | (_) | | | |\n"
                + " |_|  |_|_|_| |_|_|\\___/|_| |_|\n";
        String lineSeparator = "____________________________________________________________";
        String name = "Minion";

        // Display logo and messages
        System.out.println("Hello from\n" + logo);
        System.out.println(lineSeparator);
        System.out.println("Hello! I'm [" + name + "]");
        System.out.println("What can I do for you?");
        System.out.println(lineSeparator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineSeparator);
    }
}
