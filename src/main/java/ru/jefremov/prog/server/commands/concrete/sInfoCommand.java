package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sInfoCommand extends ServerAbstractCommand<CommandState, CollectionInfoResult>{
    public sInfoCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected CollectionInfoResult execute(CommandState state) {
        return new CollectionInfoResult(storage.printInfo());
    }
}
