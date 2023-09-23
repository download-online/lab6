package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sHelpCommand extends ServerAbstractCommand<CommandState, CommandsArrayResult>{
    public sHelpCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected CommandsArrayResult execute(CommandState state) {
        return new CommandsArrayResult(manager.getHelp());
    }
}
