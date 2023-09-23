package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;

/**
 * Команда, очищающая коллекцию.
 */
public class cClearCommand extends ClientAbstractCommand<CommandState, CommandResult> {
    public cClearCommand(String word, ClientCommandManager manager) {
        super(word, manager, true,false);
    }

    public cClearCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true,false);
    }

    @Override
    public CommandState formState() {
        return new CommandState();
    }

    @Override
    protected void runInterpretation(CommandResult result) {
        Printer.println("Collection successfully cleared.");
    }
}
