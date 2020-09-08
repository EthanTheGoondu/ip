import java.time.LocalDateTime;
import java.util.Objects;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String name, String by, boolean isDone) throws DukeException {
        super(name, TaskType.DEADLINE, isDone);
        try {
            this.by = LocalDateTime.parse(by.substring(1), Duke.dateTimeFormat);
        } catch (Exception e) {
            throw new DukeWrongFormattingException();
        }

    }

    /**
     * Adds a new deadline to the task list.
     */
    public static void newDeadline(String inputSuffix, TaskList taskList, boolean isDone, boolean shouldAnnounce)
            throws DukeException {
        String[] deadlineParts = inputSuffix.split("/by", 2);
        String deadlineName = deadlineParts[0];
        if (deadlineParts.length == 1) {
            throw new DukeEmptyDescException(TaskType.EVENT);
        } else {
            String by = deadlineParts[1];
            if (Ui.isBlankString(by)) {
                throw new DukeEmptyDescException(TaskType.EVENT);
            } else {
                taskList.addTask(new Deadline(deadlineName, by, isDone), shouldAnnounce);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), by.format(Duke.dateTimeFormat));
    }

    public String toData() {
        return String.format("[D]%s/by %s", super.toString(), by.format(Duke.dateTimeFormat));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        } else {
            Deadline deadline = (Deadline) o;
            return by.equals(deadline.by);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), by);
    }

}
