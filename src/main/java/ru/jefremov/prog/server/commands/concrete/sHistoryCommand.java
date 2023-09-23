package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sHistoryCommand extends ServerAbstractCommand<CommandState, HistoryArrayResult>{
    public sHistoryCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected HistoryArrayResult execute(CommandState state) {
        return new HistoryArrayResult(manager.getHistory());
    }
}
