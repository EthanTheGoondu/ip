import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String name, String at, boolean isDone) throws DukeException {
        super(name, TaskType.EVENT, isDone);
        try {
            this.at = LocalDateTime.parse(at.substring(1), Duke.dateTimeFormat);
        } catch (Exception e) {
            throw new DukeWrongFormattingException();
        }

    }

    /**
     * Adds a new event to the task list.
     */
    public static void newEvent(String inputSuffix, TaskList taskList, boolean isDone, boolean shouldAnnounce)
            throws DukeException {
        String[] eventParts = inputSuffix.split("/at", 2);
        String eventName = eventParts[0];
        if (eventParts.length == 1) {
            throw new DukeEmptyDescException(TaskType.EVENT);
        } else {

            String at = eventParts[1];
            if (Ui.isBlankString(at)) {
                throw new DukeEmptyDescException(TaskType.EVENT);
            } else {
                taskList.addTask(new Event(eventName, at, isDone), shouldAnnounce);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), at.format(Duke.dateTimeFormat));
    }

    public String toData() {
        return String.format("[E]%s/at %s", super.toString(), at.format(Duke.dateTimeFormat));
    }

}
