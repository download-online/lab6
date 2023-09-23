package ru.jefremov.prog.client.commands;

import ru.jefremov.prog.client.commands.arguments.AbstractArgument;


/**
 * Интерфейс, означающий, что класс способен принимать аргументы и привязывать их к конкретной команде.
 */
public interface Argumentable {
    /**
     * Получить команду, с которой связан этот объект, чтобы привязать к ней новые аргументы
     * @return команда
     */
    ClientAbstractCommand<?,?> referToCommand();

    /**
     * Вложить в эту аргументируемую сущность новый аргумент
     * @param argument новый аргумент
     */
    default void acceptArgument(AbstractArgument<?> argument) {
        if (argument==null) {
            throw new IllegalArgumentException("Configuration problem: argument should exist");
        }
        if (argument.getCommand()!=referToCommand()) {
            throw new IllegalArgumentException("Argument doesn't refer to this command");
        }
        if (argument.isAttached()) {
            throw new IllegalArgumentException("Argument is already attached to this command");
        }
        processAccepting(argument);
    }

    /**
     * Абстрактный процесс добавления аргумента. Не следует переопределять при наследовании от имплементирующих этот интерфейс классов.
     * @param argument новый аргумент
     */
    void processAccepting(AbstractArgument<?> argument);
}
