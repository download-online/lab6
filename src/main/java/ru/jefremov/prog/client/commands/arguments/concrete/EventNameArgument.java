package ru.jefremov.prog.client.commands.arguments.concrete;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.primitive.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;

/**
 * Аргумент, отвечающий за название события.
 */
public class EventNameArgument extends StringArgument {
    public EventNameArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex);
    }

    @Override
    protected boolean checkValue(String value) throws IllegalCommandArgumentException {
        String comment = administrator.eventValidator.reviewName(value);
        if (comment!=null) throw new IllegalCommandArgumentException(name, comment);
        return true;
    }
}
