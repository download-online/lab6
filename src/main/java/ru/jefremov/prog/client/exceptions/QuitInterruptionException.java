package ru.jefremov.prog.client.exceptions;

/**
 * Исключение, позволяющее пользователю отменить операцию на каком-то её этапе, например, заполнение составной формы.
 */
public class QuitInterruptionException extends Exception{
    public QuitInterruptionException() {
    }

    public QuitInterruptionException(String message) {
        super(message);
    }

    public QuitInterruptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuitInterruptionException(Throwable cause) {
        super(cause);
    }

    public QuitInterruptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
