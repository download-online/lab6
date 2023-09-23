package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

import java.util.List;

public class sFilterStartsWithCommentCommand extends ServerAbstractCommand<StringArgumentedState, TicketsArrayResult>{

    public sFilterStartsWithCommentCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected TicketsArrayResult execute(StringArgumentedState state) {
        List<Ticket> tickets = storage.printFilterStartsWithComment(state.line);
        return new TicketsArrayResult(tickets);
    }
}
