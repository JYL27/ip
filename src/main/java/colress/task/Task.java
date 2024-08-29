package colress.task;

import java.time.LocalDate;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void check() {
        this.isDone = true;
    }

    public void uncheck() {
        this.isDone = false;
    }
    public abstract boolean fallsOnDate(LocalDate date);

    public abstract String toTextFile();
}
