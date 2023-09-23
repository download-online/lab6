package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sShowCommand extends ServerAbstractCommand<CommandState, TicketsArrayResult>{
    public sShowCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected TicketsArrayResult execute(CommandState state) {
        return new TicketsArrayResult(storage.printDescending());
    }
}
