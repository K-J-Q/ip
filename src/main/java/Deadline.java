public class Deadline extends Task {
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_BY = "by";
    private String dueDate = "";


    // Expect user input with one '/'  as separator for by
    Deadline(String userInput) {
        userInput = userInput.substring(KEYWORD_DEADLINE.length());
        String[] userInputs = userInput.split("/");
        if (userInputs.length != 2) {
            return;
        }
        this.title = userInputs[0];
        if (userInputs[1].startsWith(KEYWORD_BY)) {
            this.dueDate = userInputs[1].substring(KEYWORD_BY.length());
        } else {
            this.dueDate = "NA";
        }

        this.title = this.title.trim();
        this.dueDate = this.dueDate.trim();
    }

    Deadline(String title, String doneBy) {
        this.title = title;
        this.dueDate = doneBy;
    }

    @Override
    public String getTask() {
        return String.format("[D][%c] %s (by: %s)", this.isDone ? 'X' : ' ', this.title, this.dueDate);
    }
}
