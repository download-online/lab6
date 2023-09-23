package ru.jefremov.prog.client.commands.arguments.primitive;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.client.exceptions.command.InvalidCommandArgumentException;

/**
 * Целочисленный аргумент типа long
 */
public class LongArgument extends PrimitiveArgument<Long> {
    public LongArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex, ArgumentType.LONG);
    }

    @Override
    protected Long parseValue(String text) throws InvalidCommandArgumentException, IllegalCommandArgumentException {
        return Long.parseLong(text);
    }
}
