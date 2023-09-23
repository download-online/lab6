package ru.jefremov.prog.server.exceptions;

public class RequestReadingException extends Exception{
    public RequestReadingException() {
    }

    public RequestReadingException(String message) {
        super(message);
    }

    public RequestReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestReadingException(Throwable cause) {
        super(cause);
    }

    public RequestReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
