package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;

import java.util.Arrays;

/**
 * Команда для вывода содержимого коллекции.
 */
public class cShowCommand extends ClientAbstractCommand<CommandState, TicketsArrayResult> {
    public cShowCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, true);
    }

    public cShowCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, true);
    }

    @Override
    public CommandState formState() {
        return new CommandState();
//        boolean result = storage.show();
//        if (!result) Printer.println("Collection is empty");
    }

    @Override
    protected void runInterpretation(TicketsArrayResult result) {
        if (result.tickets==null || result.tickets.length == 0) {
            Printer.println("Collection is empty");
        } else {
            Arrays.stream(result.tickets).forEachOrdered(Printer::println);
        }
    }
}
