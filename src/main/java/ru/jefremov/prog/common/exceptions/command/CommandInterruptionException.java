package ru.jefremov.prog.common.exceptions.command;

/**
 * Исключение, прерывающее выполнение команды из-за внутренней ошибки при её запуске.
 */
public class CommandInterruptionException extends CommandLaunchException{
    public CommandInterruptionException() {
    }

    public CommandInterruptionException(String message) {
        super(message);
    }

    public CommandInterruptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandInterruptionException(Throwable cause) {
        super(cause);
    }

    public CommandInterruptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
