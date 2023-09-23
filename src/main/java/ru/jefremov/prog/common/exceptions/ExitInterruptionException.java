package ru.jefremov.prog.common.exceptions;

/**
 * Исключение, отвечающее за прерывание программы для её завершения.
 */
public class ExitInterruptionException extends Exception{
    public ExitInterruptionException() {
    }

    public ExitInterruptionException(String message) {
        super(message);
    }

    public ExitInterruptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExitInterruptionException(Throwable cause) {
        super(cause);
    }

    public ExitInterruptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
