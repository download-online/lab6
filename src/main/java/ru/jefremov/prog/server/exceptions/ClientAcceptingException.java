package ru.jefremov.prog.server.exceptions;

/**
 * Общий класс исключений, возникающих при взаимодействии с хранилищем коллекции.
 */
public class ClientAcceptingException extends Exception {
    public ClientAcceptingException() {
    }

    public ClientAcceptingException(String message) {
        super(message);
    }

    public ClientAcceptingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientAcceptingException(Throwable cause) {
        super(cause);
    }

    public ClientAcceptingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
