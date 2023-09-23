package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;

/**
 * Команда, выводящая информацию о коллекции.
 */
public class cInfoCommand extends ClientAbstractCommand<CommandState, CollectionInfoResult> {
    public cInfoCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, false);
    }

    public cInfoCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, false);
    }

    @Override
    public CommandState formState() {
        return new CommandState();
    }

    @Override
    protected void runInterpretation(CollectionInfoResult result) {
        Printer.println(result.info);
    }
}
