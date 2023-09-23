package ru.jefremov.prog.client.commands.arguments.concrete;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.ArgumentType;
import ru.jefremov.prog.client.commands.arguments.PrimitiveArgument;
import ru.jefremov.prog.client.commands.arguments.primitive.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.client.exceptions.command.InvalidCommandArgumentException;
import ru.jefremov.prog.common.models.TicketType;

/**
 * Аргумент, отвечающий за тип билета.
 */
public class TicketTypeArgument extends PrimitiveArgument<TicketType> {
    public TicketTypeArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex, ArgumentType.ENUM);
    }

    @Override
    protected TicketType parseValue(String textForm) throws InvalidCommandArgumentException, IllegalCommandArgumentException {
        if (textForm==null || textForm.isBlank()) return null;
        return TicketType.valueOf(textForm.toUpperCase());
    }

    @Override
    protected boolean checkValue(TicketType value) throws IllegalCommandArgumentException {
        return administrator.ticketValidator.checkType(value);
    }

    @Override
    public String getInvitation() {
        StringBuilder invitation = new StringBuilder("Choose " + name + " from the list: \n");
        for (TicketType value :
                TicketType.values()) {
            invitation.append(value.ordinal() + 1).append(". ").append(value.toString().toLowerCase()).append('\n');
        }
        return invitation.toString();
    }
}
