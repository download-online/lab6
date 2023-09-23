package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;

import java.util.Arrays;

/**
 * Команда, выводящая историю выполненных команд.
 */
public class cHistoryCommand extends ClientAbstractCommand<CommandState, HistoryArrayResult> {
    public cHistoryCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, false);
    }

    public cHistoryCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, false);
    }

    @Override
    public CommandState formState() {
        return new CommandState();
    }

    @Override
    protected void runInterpretation(HistoryArrayResult result) {
        if (result.history==null || result.history.length == 0) {
            Printer.println("No records in history.");
        } else {
            Arrays.stream(result.history).forEachOrdered(Printer::println);
        }
    }
}
