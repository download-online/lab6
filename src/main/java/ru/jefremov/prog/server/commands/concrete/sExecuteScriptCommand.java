package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.commands.results.CommandResult;
import ru.jefremov.prog.common.commands.results.IntegerResult;
import ru.jefremov.prog.common.commands.states.CommandState;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sExecuteScriptCommand extends ServerAbstractCommand<CommandState, CommandResult>
{
    public sExecuteScriptCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager);
    }

    @Override
    protected CommandResult execute(CommandState state) {
        return new CommandResult();
    }
}
