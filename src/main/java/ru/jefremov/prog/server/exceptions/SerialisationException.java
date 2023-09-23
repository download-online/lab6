package ru.jefremov.prog.server.exceptions;

public class SerialisationException extends Exception{
    public SerialisationException() {
    }

    public SerialisationException(String message) {
        super(message);
    }

    public SerialisationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerialisationException(Throwable cause) {
        super(cause);
    }

    public SerialisationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
