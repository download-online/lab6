package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Команда, выводящая содержимое коллекции в порядке убывания.
 */
public class cPrintDescendingCommand extends ClientAbstractCommand<CommandState, TicketsArrayResult> {
    public cPrintDescendingCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, true);
    }

    public cPrintDescendingCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, true);
    }

    @Override
    public CommandState formState() {
//        boolean result = storage.printDescending();
//        if (!result) Printer.println("Collection is empty");
        return new CommandState();
    }

    @Override
    protected void runInterpretation(TicketsArrayResult result) {
        if (result.tickets==null || result.tickets.length == 0) {
            Printer.println("Collection is empty.");
        } else {
            Arrays.stream(result.tickets).sorted(Comparator.reverseOrder()).forEachOrdered(Printer::println);
        }
    }
}
