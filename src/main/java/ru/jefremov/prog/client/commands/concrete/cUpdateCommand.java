package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.complex.TicketArgument;
import ru.jefremov.prog.client.commands.arguments.concrete.TicketIdArgument;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.common.models.Ticket;

/**
 * Команда для обновления билетов.
 */
public class cUpdateCommand extends ClientAbstractCommand<TicketIntegerArgumentedState, IntegerResult> {
    private final TicketIdArgument arg1 = new TicketIdArgument("id", ArgumentPlacement.INLINE,this,null);
    private final TicketArgument arg2 = new TicketArgument("Ticket", this);
    public cUpdateCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, true);
    }

    public cUpdateCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, true);
    }

    @Override
    public TicketIntegerArgumentedState formState() {
        int id = arg1.getValue();
        Ticket ticket = arg2.getValue();
        return new TicketIntegerArgumentedState(ticket,id);
    }

    @Override
    protected void runInterpretation(IntegerResult result) {
        if (result.code == 1) {
            Printer.println("Ticket successfully updated.");
        } else if (result.code==2) Printer.println("Ticket with specified id doesn't exist.");
        else Printer.println("Failed to update ticket.");
    }
}
