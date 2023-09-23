package ru.jefremov.prog.client.exceptions.command;

import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;

/**
 * Общий класс для исключений, связанных с аргументами запускаемых команд.
 */
public class CommandArgumentException extends CommandLaunchException {
    public CommandArgumentException() {
    }

    public CommandArgumentException(String message) {
        super(message);
    }

    public CommandArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandArgumentException(Throwable cause) {
        super(cause);
    }

    public CommandArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
