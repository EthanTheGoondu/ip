public class Todo extends Task {

    /**
     * The todo class, a variant of a task.
     */
    public Todo(String name) throws DukeEmptyDescException {
        super(name, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}