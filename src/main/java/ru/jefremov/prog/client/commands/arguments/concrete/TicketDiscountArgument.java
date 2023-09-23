package ru.jefremov.prog.client.commands.arguments.concrete;


import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.primitive.DoubleArgument;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;

/**
 * Аргумент, отвечающий за скидку билета.
 */
public class TicketDiscountArgument extends DoubleArgument {
    public TicketDiscountArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex);
    }

    @Override
    protected boolean checkValue(Double value) throws IllegalCommandArgumentException {
        String comment = administrator.ticketValidator.reviewDiscount(value);
        if (comment!=null) throw new IllegalCommandArgumentException(name, comment);
        return true;
    }
}
