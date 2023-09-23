package ru.jefremov.prog.client.exceptions;

public class ClientLaunchException extends Exception{
    public ClientLaunchException() {
    }

    public ClientLaunchException(String message) {
        super(message);
    }

    public ClientLaunchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientLaunchException(Throwable cause) {
        super(cause);
    }

    public ClientLaunchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
