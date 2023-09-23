package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.common.exceptions.ExitInterruptionException;

/**
 * Команда, обеспечивающая завершение программы без сохранения.
 */
public class cExitCommand extends ClientAbstractCommand<CommandState, CommandResult> {
    public cExitCommand(String word, ClientCommandManager manager) {
        super(word, manager, false,false);
    }

    public cExitCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, false,false);
    }

    @Override
    public CommandState formState() throws ExitInterruptionException {
        throw new ExitInterruptionException();
    }
}
