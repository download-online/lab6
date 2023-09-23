package ru.jefremov.prog.client.exceptions;

public class RequestSendingException extends Exception {
    public RequestSendingException() {
    }

    public RequestSendingException(String message) {
        super(message);
    }

    public RequestSendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestSendingException(Throwable cause) {
        super(cause);
    }

    public RequestSendingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
