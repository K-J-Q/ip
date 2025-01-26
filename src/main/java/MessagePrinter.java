public class MessagePrinter {
    private Boolean isNewMessage = true;
    private String indentation = "   ";
    private String lineSeparator = "____________________________________________________________";

    // adds an indentation of 4 spaces "    "
    private String addIndentation(String message){
        // add indentation after every new line too
        message = message.replace("\n", "\n" + indentation);
        return this.indentation + message;
    }

    public void printSep(){
        System.out.println(addIndentation(lineSeparator));
    }

    public void printMessage(String message){
        System.out.println(addIndentation(message));
    }

    public void printMessageAndSep(String message){
        this.printSep();
        this.printMessage(message);
        this.printSep();
    }
}
