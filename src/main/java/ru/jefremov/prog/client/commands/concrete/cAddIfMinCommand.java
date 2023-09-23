package ru.jefremov.prog.client.commands.concrete;


import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.arguments.complex.TicketArgument;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.IntegerResult;
import ru.jefremov.prog.common.commands.states.TicketArgumentedState;
import ru.jefremov.prog.common.models.Ticket;

/**
 * Команда, добавляющая билет в коллекцию, если он меньше всех её элементов.
 */
public class cAddIfMinCommand extends ClientAbstractCommand<TicketArgumentedState, IntegerResult> {
    private final TicketArgument arg1 = new TicketArgument("Ticket", this);
    public cAddIfMinCommand(String word, ClientCommandManager manager) {
        super(word, manager, true,false);
    }

    public cAddIfMinCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, false);
    }

    @Override
    public TicketArgumentedState formState() {
        Ticket ticket = arg1.getValue();
        return new TicketArgumentedState(ticket);
    }

    @Override
    protected void runInterpretation(IntegerResult result) {
        if (result.code == 1) {
            Printer.println("Ticket successfully added.");
        } else Printer.println("Failed to add ticket: the specified ticket is not minimal.");
    }
}
