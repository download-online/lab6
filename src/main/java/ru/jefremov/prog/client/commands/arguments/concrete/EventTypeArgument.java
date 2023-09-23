package ru.jefremov.prog.client.commands.arguments.concrete;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.ArgumentType;
import ru.jefremov.prog.client.commands.arguments.PrimitiveArgument;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.client.exceptions.command.InvalidCommandArgumentException;
import ru.jefremov.prog.common.models.EventType;

/**
 * Аргумент, отвечающий за тип события.
 */
public class EventTypeArgument extends PrimitiveArgument<EventType> {
    public EventTypeArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex, ArgumentType.ENUM);
    }

    @Override
    protected EventType parseValue(String textForm) throws InvalidCommandArgumentException, IllegalCommandArgumentException {
        if (textForm==null || textForm.isBlank()) return null;
        return EventType.valueOf(textForm.toUpperCase());
    }

    @Override
    protected boolean checkValue(EventType value) throws IllegalCommandArgumentException {
        return administrator.eventValidator.checkEventType(value);
    }

    @Override
    public String getInvitation() {
        StringBuilder invitation = new StringBuilder("Choose " + name + " from the list: \n");
        for (EventType value :
                EventType.values()) {
            invitation.append(value.ordinal() + 1).append(". ").append(value.toString().toLowerCase()).append('\n');
        }
        return invitation.toString();
    }
}
