package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.TaskType;
import colress.Ui;
import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;

/**
 * Represents the add command that add a task to the list of tasks.
 */
public final class AddCommand extends Command {
    public AddCommand() {
        super("Okay. I have added this task to your list:\n");
    }

    /**
     * Facilitates adding a task to the provided TaskList as not done, using the provided Ui object to receive input
     * from the user regarding what type of task to add and the various fields of the task to be added.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        TaskType taskType = ui.promptTaskType();
        String description = ui.promptDescription(taskType);
        LocalDate date;
        LocalTime from;
        LocalTime to;
        Task task;

        switch (taskType) {
        case DEADLINE:
            date = ui.promptDate(taskType);
            task = new Deadline(description, date);
            break;
        case EVENT:
            date = ui.promptDate(taskType);
            from = ui.promptTime("from");
            to = ui.promptTime("to");
            task = new Event(description, date, from, to);
            break;
        default:
            task = new ToDo(description);
        }

        ui.printConfirmationMessage(taskList, getSuccessfulExecutionMessage() + taskList.addTask(task));
    }
}
