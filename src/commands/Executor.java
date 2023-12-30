package commands;

/**
 * Клас ініціалізатора виконання команд
 */
public class Executor {
    public void executeCommand(Command command) {
        command.execute();
    }
}
