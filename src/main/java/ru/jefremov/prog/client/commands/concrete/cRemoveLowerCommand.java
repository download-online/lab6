package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.arguments.complex.TicketArgument;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.common.models.Ticket;

/**
 * Команда, удаляющая все элементы, меньшие, чем заданный.
 */
public class cRemoveLowerCommand extends ClientAbstractCommand<TicketArgumentedState, IntegerResult> {
    private final TicketArgument arg1 = new TicketArgument("Ticket", this);
    public cRemoveLowerCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, false);
    }

    public cRemoveLowerCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, false);
    }

    @Override
    public TicketArgumentedState formState() {
        Ticket ticket = arg1.getValue();
        return new TicketArgumentedState(ticket);
    }

    @Override
    protected void runInterpretation(IntegerResult result) {
        if (result.code==-1) Printer.println("Ticket must be validated to go through comparison.");
        else if (result.code==0) {
            Printer.println("Not a single ticket has been removed");
        } else {
            Printer.println("Removed "+result.code+" tickets.");
        }
    }
}
