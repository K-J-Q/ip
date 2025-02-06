public class Event extends Task {
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_FROM = "from";
    private static final String KEYWORD_TO = "to";

    String fromDateTime = "";
    String toDateTime = "";

    // Expect user input with three '/'  as separator for from and to
    Event(String userInput) {
        userInput = userInput.substring(KEYWORD_EVENT.length());
        String[] userInputs = userInput.split("/");

        if (userInputs.length != 3) {
            return;
        }
        this.title = userInputs[0];

        for (int i = 1; i < 3; i++) {
            if (userInputs[i].startsWith(KEYWORD_FROM)) {
                this.fromDateTime = userInputs[i].substring(KEYWORD_FROM.length());
            } else if (userInputs[i].startsWith(KEYWORD_TO)) {
                this.toDateTime = userInputs[i].substring(KEYWORD_TO.length());
            }
        }

        this.title = this.title.trim();
        this.fromDateTime = this.fromDateTime.trim();
        this.toDateTime = this.toDateTime.trim();
    }

    Event(String title, String fromDateTime, String toDateTime) {
        this.title = title;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String getTask() {
        return String.format("[E][%c] %s (from: %s to: %s)", this.isDone ? 'X' : ' ', this.title, this.fromDateTime, this.toDateTime);
    }
}
