package ru.jefremov.prog.common.exceptions.command;

/**
 * Исключение, отвечающее за ошибки при попытке запуска команды.
 */
public class CommandLaunchException extends Exception{
    public CommandLaunchException() {
    }

    public CommandLaunchException(String message) {
        super(message);
    }

    public CommandLaunchException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandLaunchException(Throwable cause) {
        super(cause);
    }

    public CommandLaunchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
