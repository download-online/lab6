package ru.jefremov.prog.client.exceptions.command;

/**
 * Исключение, возникающее, когда в аргумент команды передаётся некорректное значение, не прошедшее проверку.
 * Означает, что значение подошло по формату, но не подходит для использования.
 */
public class IllegalCommandArgumentException extends CommandArgumentException {
    public IllegalCommandArgumentException() {
    }

    /**
     * Конструктор исключения о некорректном значении аргумента
     * @param argumentName название аргумента
     * @param enteredText текст, содержащий некорректное значение
     */
    public IllegalCommandArgumentException(String argumentName, String enteredText) {
        super("Illegal value for "+argumentName+" entered: "+enteredText);
    }

    public IllegalCommandArgumentException(String message) {
        super(message);
    }

    public IllegalCommandArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCommandArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalCommandArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
