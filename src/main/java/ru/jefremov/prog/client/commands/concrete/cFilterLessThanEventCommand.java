package ru.jefremov.prog.client.commands.concrete;


import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.arguments.complex.EventArgument;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.TicketsArrayResult;
import ru.jefremov.prog.common.commands.states.EventArgumentedState;
import ru.jefremov.prog.common.models.Event;

import java.util.Arrays;

/**
 * Команда, выводящая все билеты, чьё событие меньше заданного.
 */
public class cFilterLessThanEventCommand extends ClientAbstractCommand<EventArgumentedState, TicketsArrayResult> {
    private final EventArgument arg1 = new EventArgument("event", this);
    public cFilterLessThanEventCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, true);
    }

    public cFilterLessThanEventCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, true);
    }

    @Override
    public EventArgumentedState formState() {
        Event event = arg1.getValue();
        return new EventArgumentedState(event);
//        boolean success = storage.printFilterLessThanEvent(event);
//        if (!success) Printer.println("No ticket has an event less than the specified one.");
    }

    @Override
    protected void runInterpretation(TicketsArrayResult result) {
        if (result.tickets==null || result.tickets.length == 0) {
            Printer.println("No ticket has an event less than the specified one.");
        } else {
            Arrays.stream(result.tickets).forEachOrdered(Printer::println);
        }
    }
}
