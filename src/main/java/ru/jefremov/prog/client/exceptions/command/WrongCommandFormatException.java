package ru.jefremov.prog.client.exceptions.command;

import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;

/**
 * Исключение, возникающее, когда команда запускается с неправильным набором аргументов.
 */
public class WrongCommandFormatException extends CommandLaunchException {
    public WrongCommandFormatException() {
    }

    public WrongCommandFormatException(String message) {
        super(message);
    }

    public WrongCommandFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCommandFormatException(Throwable cause) {
        super(cause);
    }

    public WrongCommandFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
