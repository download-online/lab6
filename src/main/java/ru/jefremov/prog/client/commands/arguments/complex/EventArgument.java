package ru.jefremov.prog.client.commands.arguments.complex;


import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.ComplexArgument;
import ru.jefremov.prog.client.commands.arguments.concrete.*;
import ru.jefremov.prog.client.commands.arguments.primitive.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.common.models.Event;


/**
 * Комплексный аргумент для события, собирающий его из аргументов, соответствующих его полям.
 */
public class EventArgument extends ComplexArgument<Event> {
    private final EventTypeArgument arg3 = new EventTypeArgument("Event type", ArgumentPlacement.NEWLINE, this, null);
    private final StringArgument arg1 = new EventNameArgument("Event name", ArgumentPlacement.NEWLINE,this,null);
    private final LongArgument arg2 = new EventTicketsCountArgument("Event tickets count", ArgumentPlacement.NEWLINE,this,null);

    public EventArgument(String name, Argumentable argumentable) {
        super(name, argumentable);
    }

    @Override
    protected boolean checkValue(Event value) throws IllegalCommandArgumentException {
        String comment = administrator.eventValidator.reviewEvent(value);
        if (comment!=null) throw new IllegalCommandArgumentException(name, comment);
        return true;
    }

    @Override
    public Event formComplexValue() {
        return new Event(arg1.getValue(), arg2.getValue(), arg3.getValue());
    }
}
