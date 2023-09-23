package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.IntegerResult;
import ru.jefremov.prog.common.commands.states.TicketArgumentedState;
import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sAddCommand extends ServerAbstractCommand<TicketArgumentedState, IntegerResult> {
    public sAddCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected IntegerResult execute(TicketArgumentedState state) {
        Ticket ticket = state.ticket;
        boolean success = storage.addTicket(ticket);
        return new IntegerResult(success ?1:0);
    }


}
