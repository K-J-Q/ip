public class Event extends Task
{
    String startDateTime= "";
    String endDateTime= "";

    Event(String userInput){
        userInput = userInput.substring("event".length());
        String[] userInputs = userInput.split("/");
        if (userInputs.length != 3){
            return;
        }
        this.title = userInputs[0];

        for(int i = 1; i < 3; i++){
            if(userInputs[i].startsWith("from")) {
                this.startDateTime = userInputs[i].substring(4);
            } else if (userInputs[i].startsWith("to")){
                this.endDateTime = userInputs[i].substring(2);
            }
        }

        this.title = this.title.trim();
        this.startDateTime = this.startDateTime.trim();
        this.endDateTime = this.endDateTime.trim();
    }

    Event(String title, String startDateTime, String endDateTime) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime= endDateTime;
    }

    @Override
    public String getTask() {
        return String.format("[E][%c] %s (by: %s to: %s)", this.isDone ? 'X' : ' ', this.title, this.startDateTime, this.endDateTime);
    }
}
