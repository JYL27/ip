public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("""
                I do not recognise that command.
                Here are the commands I recognise:
                add: add a new item to your list of tasks.
                check: check off an item to your list of tasks.
                uncheck: uncheck an item to your list of tasks.
                delete: delete an item from your list of tasks
                list: print out your list of tasks.
                date: print out deadlines and events that occur on a specific date.
                bye: bid me farewell."""
        );
    }
}
