package ru.jefremov.prog.client.commands.arguments.concrete;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.primitive.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;

/**
 * Аргумент, отвечающий за название билета.
 */
public class TicketNameArgument extends StringArgument {
    public TicketNameArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex);
    }

    @Override
    protected boolean checkValue(String value) throws IllegalCommandArgumentException {
        String comment = administrator.ticketValidator.reviewName(value);
        if (comment!=null) throw new IllegalCommandArgumentException(name, comment);
        return true;
    }
}
