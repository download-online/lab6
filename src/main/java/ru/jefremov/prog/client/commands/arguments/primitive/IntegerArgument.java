package ru.jefremov.prog.client.commands.arguments.primitive;


import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.client.exceptions.command.InvalidCommandArgumentException;

/**
 * Целочисленный аргумент типа int
 */
public class IntegerArgument extends PrimitiveArgument<Integer> {
    public IntegerArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex, ArgumentType.INTEGER);
    }

    @Override
    protected Integer parseValue(String text) throws InvalidCommandArgumentException, IllegalCommandArgumentException {
        return Integer.parseInt(text);
    }
}
