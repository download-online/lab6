package ru.jefremov.prog.client.commands.arguments.complex;


import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.ComplexArgument;
import ru.jefremov.prog.client.commands.arguments.concrete.*;
import ru.jefremov.prog.client.commands.arguments.primitive.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.common.models.Ticket;

/**
 * Комплексный аргумент для билета, собирающий его из аргументов, соответствующих его полям.
 */
public class TicketArgument extends ComplexArgument<Ticket> {
    private final TicketTypeArgument arg6 = new TicketTypeArgument("Ticket type", ArgumentPlacement.NEWLINE,this,null);
    private final StringArgument arg1 = new TicketNameArgument("Ticket name", ArgumentPlacement.NEWLINE, this, null);
    private final StringArgument arg5 = new TicketCommentArgument("Ticket comment", ArgumentPlacement.NEWLINE, this, null);
    private final DoubleArgument arg3 = new TicketPriceArgument("Ticket price",ArgumentPlacement.NEWLINE,this,null);
    private final DoubleArgument arg4 = new TicketDiscountArgument("Ticket discount", ArgumentPlacement.NEWLINE, this, null);
    private final CoordinatesArgument arg2 = new CoordinatesArgument("Ticket coordinates", this);
    private final EventArgument arg7 = new EventArgument("Event", this);

    public TicketArgument(String name, Argumentable argumentable) {
        super(name, argumentable);
    }

    @Override
    protected boolean checkValue(Ticket value) throws IllegalCommandArgumentException {
        String comment = administrator.ticketValidator.reviewTicket(value);
        if (comment!=null) throw new IllegalCommandArgumentException(name, comment);
        return true;
    }

    @Override
    public Ticket formComplexValue() {
        return new Ticket(arg1.getValue(),arg2.getValue(),arg3.getValue(),arg4.getValue(),arg5.getValue(),arg6.getValue(),arg7.getValue());
    }
}
