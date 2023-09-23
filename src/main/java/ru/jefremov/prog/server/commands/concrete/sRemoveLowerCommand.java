package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sRemoveLowerCommand extends ServerAbstractCommand<TicketArgumentedState, IntegerResult>{
    public sRemoveLowerCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected IntegerResult execute(TicketArgumentedState state) {
        int code = -1;
        if (storage.getAdministrator().ticketValidator.checkTicket(state.ticket)) code = storage.removeLower(state.ticket);
        return new IntegerResult(code);
    }
}
