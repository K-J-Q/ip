public class Event extends Task {
    String fromDateTime = "";
    String toDateTime = "";

    // Expect user input with three '/'  as separator for from and to
    Event(String userInput) {
        userInput = userInput.substring("event".length());
        String[] userInputs = userInput.split("/");

        if (userInputs.length != 3) {
            return;
        }
        this.title = userInputs[0];

        for (int i = 1; i < 3; i++) {
            if (userInputs[i].startsWith("from")) {
                this.fromDateTime = userInputs[i].substring("from".length());
            } else if (userInputs[i].startsWith("to")) {
                this.toDateTime = userInputs[i].substring("to".length());
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
        return String.format("[E][%c] %s (by: %s to: %s)", this.isDone ? 'X' : ' ', this.title, this.fromDateTime, this.toDateTime);
    }
}
