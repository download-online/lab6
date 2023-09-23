package ru.jefremov.prog.server.exceptions;

public class ResponseSendingException extends Exception{
    public ResponseSendingException() {
    }

    public ResponseSendingException(String message) {
        super(message);
    }

    public ResponseSendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseSendingException(Throwable cause) {
        super(cause);
    }

    public ResponseSendingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
