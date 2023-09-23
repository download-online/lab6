package ru.jefremov.prog.client.commands.concrete;


import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.CommandsArrayResult;
import ru.jefremov.prog.common.commands.states.CommandState;

import java.util.Arrays;
import java.util.Collection;

/**
 * Команда, выводящая справку по доступным командам.
 */
public class cHelpCommand extends ClientAbstractCommand<CommandState, CommandsArrayResult> {
    public cHelpCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, false);
    }

    public cHelpCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, false);
    }

    @Override
    public CommandState formState() {
        return new CommandState();
    }

    @Override
    protected void runInterpretation(CommandsArrayResult result) {
        if (result.words==null || result.words.length == 0) {
            Printer.println("No commands are available.");
        } else {
            Collection<String> clientCommands = manager.getCommandWords();
            Arrays.stream(result.words).filter(word -> clientCommands.contains(word)).map(manager::getCommand).forEachOrdered(Printer::println);
        }
    }
}
