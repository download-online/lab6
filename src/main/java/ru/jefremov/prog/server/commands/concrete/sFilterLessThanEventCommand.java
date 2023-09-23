package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

import java.util.Collection;
import java.util.List;

public class sFilterLessThanEventCommand extends ServerAbstractCommand<EventArgumentedState, TicketsArrayResult>{
    public sFilterLessThanEventCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected TicketsArrayResult execute(EventArgumentedState state) {
        List<Ticket> tickets = storage.printFilterLessThanEvent(state.event);
        return new TicketsArrayResult(tickets);
    }
}
