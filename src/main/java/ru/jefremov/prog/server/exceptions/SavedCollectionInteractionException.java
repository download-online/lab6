package ru.jefremov.prog.server.exceptions;

/**
 * Общий класс исключений, возникающих при взаимодействии с хранилищем коллекции.
 */
public class SavedCollectionInteractionException extends Exception {
    public SavedCollectionInteractionException() {
    }

    public SavedCollectionInteractionException(String message) {
        super(message);
    }

    public SavedCollectionInteractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SavedCollectionInteractionException(Throwable cause) {
        super(cause);
    }

    public SavedCollectionInteractionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
