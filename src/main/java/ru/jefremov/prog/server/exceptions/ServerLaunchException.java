package ru.jefremov.prog.server.exceptions;

public class ServerLaunchException extends Exception{
    public ServerLaunchException() {
    }

    public ServerLaunchException(String message) {
        super(message);
    }

    public ServerLaunchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerLaunchException(Throwable cause) {
        super(cause);
    }

    public ServerLaunchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
