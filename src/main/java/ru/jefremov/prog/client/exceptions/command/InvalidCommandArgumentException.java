package ru.jefremov.prog.client.exceptions.command;

/**
 * Исключение, возникающее, когда в аргумент команды передаётся значение, не подходящее ему по формату.
 * Означает невозможность преобразования строкового значения аргумента в значение его типа.
 */
public class InvalidCommandArgumentException extends CommandArgumentException {
    public InvalidCommandArgumentException() {
    }

    public InvalidCommandArgumentException(String message) {
        super(message);
    }

    public InvalidCommandArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandArgumentException(Throwable cause) {
        super(cause);
    }

    public InvalidCommandArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
