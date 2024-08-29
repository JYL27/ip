import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public final class Storage {
    private final File TASK_FILE;
    private FileWriter writer;

    public Storage(String filePath) {
        this.TASK_FILE = new File(filePath);
    }
    private LocalDate readDate(String date) throws FileCorruptedException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new FileCorruptedException();
        }
    }

    private LocalTime readTime(String time) throws FileCorruptedException {
        try {
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new FileCorruptedException();
        }
    }

    private void repopulateTasks(TaskList taskList)
            throws FileCorruptedException, FileNotFoundException {
        Scanner reader = new Scanner(TASK_FILE);
        String[] strings;
        String currLine;

        if (reader.hasNextLine()) {
            while (reader.hasNextLine()) {
                currLine = reader.nextLine();
                strings = currLine.split(" \\| ");
                boolean isChecked;
                if (Objects.equals(strings[0], "[X]")) {
                    isChecked = true;
                } else if (Objects.equals(strings[0], "[ ]")) {
                    isChecked = false;
                } else {
                    throw new FileCorruptedException();
                }

                switch (strings[1]) {
                case "To-Do":
                    taskList.addTask(new ToDo(strings[2], isChecked));
                    break;
                case "Deadline":
                    LocalDate deadline = readDate(strings[3]);

                    taskList.addTask(new Deadline(strings[2], deadline, isChecked));
                    break;
                case "Event":
                    LocalDate eventDate = readDate(strings[3]);
                    String[] times = strings[4].split(" to ");
                    LocalTime from = readTime(times[0]);
                    LocalTime to = readTime(times[1]);

                    taskList.addTask(new Event(strings[2], eventDate, from, to, isChecked));
                    break;
                default:
                    throw new FileCorruptedException();
                }
            }
        }
    }

    public boolean loadTasks(TaskList taskList) throws IOException, FileCorruptedException {
        boolean createdNewFile = TASK_FILE.createNewFile();
        repopulateTasks(taskList);
        return createdNewFile;
    }

    private void initialiseFileWriter() throws IOException {
        writer = new FileWriter(TASK_FILE, false);
    }
    public void writeToTaskFile(TaskList taskList) throws IOException {
        String result = "";
        for(int i = 0; i < taskList.size(); i++) {
            result += String.format(taskList.get(i).toTextFile() + "\n", i + 1);
        }
        initialiseFileWriter();
        writer.write(result);
        writer.close();
    }
}
