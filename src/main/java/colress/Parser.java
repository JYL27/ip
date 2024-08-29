package colress;

import colress.command.*;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public final class Parser {
    private final Scanner SCANNER = new Scanner(System.in);
    private final String TASK_TYPE_DEADLINE = "deadline";
    private final String TASK_TYPE_EVENT = "event";
    private final String TASK_TYPE_TODO = "todo";
    private String input = "";
    public Parser() {}

    private String getInput() {
        input = SCANNER.nextLine().toLowerCase();
        return input;
    }

    public Command getCommand() throws UnknownCommandException {
        getInput();
        switch (input) {
        case "add":
            return new AddCommand();
        case "check":
            return new CheckCommand();
        case "date":
            return new DateCommand();
        case "delete":
            return new DeleteCommand();
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "uncheck":
            return new UncheckCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    public String getDescription() {
        return getInput();
    }

    public String getTaskType() throws UnknownTaskTypeException {
        String result = getInput();
        if (Objects.equals(result, TASK_TYPE_DEADLINE)
                || Objects.equals(result, TASK_TYPE_TODO)
                || Objects.equals(result, TASK_TYPE_EVENT)) {
            return result;
        } else {
            throw new UnknownTaskTypeException();
        }
    }

    public LocalDate readDate() {
        getInput();
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public LocalTime readTime() {
        getInput();
        try {
            return LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public int getTaskNumber() {
        try {
            return Integer.parseInt(getInput());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
