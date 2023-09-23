package ru.jefremov.prog.client.commands.arguments;


import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.common.exceptions.command.*;

import java.util.ArrayList;

/**
 * Аргумент, позволяющий передавать команде составные значения какого-то типа.
 * @param <T>
 */
public abstract class ComplexArgument<T> extends AbstractArgument<T> implements Argumentable {
    private final ArrayList<AbstractArgument<?>> subArguments = new ArrayList<>();

    /**
     * Конструктор комплексного аргумента
     * @param name наименование
     * @param argumentable объект, в который будет вложен комплексный аргумент
     */
    public ComplexArgument(String name, Argumentable argumentable) {
        super(name, ArgumentPlacement.NEWLINE, argumentable, "", ArgumentType.OBJECT);
    }

    @Override
    protected final T parseValue(String text) throws CommandLaunchException, QuitInterruptionException {
        form(subArguments);
        return formComplexValue();
    }

    protected abstract T formComplexValue();

    @Override
    public ClientAbstractCommand<?,?> referToCommand() {
        return getCommand();
    }

    @Override
    public final void processAccepting(AbstractArgument<?> argument) {
        if (argument.placement==ArgumentPlacement.INLINE) {
            throw new IllegalArgumentException("Cannot attach inline argument to complex argument");
        }
        subArguments.add(argument);
    }

    @Override
    public String getInvitation() {
        return "To specify "+name+", enter the following fields:\n";
    }
}
