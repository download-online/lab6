package ru.jefremov.prog.client.commands.arguments.primitive;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.client.exceptions.command.InvalidCommandArgumentException;

/**
 * Строковый аргумент
 */
public class StringArgument extends PrimitiveArgument<String> {
    public StringArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex, ArgumentType.STRING);
    }


    @Override
    protected String parseValue(String textForm) throws InvalidCommandArgumentException, IllegalCommandArgumentException {
        return administrator.quotationHandler.handle(textForm);
    }
}
