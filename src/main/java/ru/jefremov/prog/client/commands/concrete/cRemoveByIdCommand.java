package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.concrete.TicketIdArgument;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;

/**
 * Команда, удаляющая элемент по Id.
 */
public class cRemoveByIdCommand extends ClientAbstractCommand<IntegerArgumentedState, IntegerResult> {
    private final TicketIdArgument arg1 = new TicketIdArgument("id", ArgumentPlacement.INLINE, this,null);
    public cRemoveByIdCommand(String word, ClientCommandManager manager) {
        super(word, manager, true,false);
    }

    public cRemoveByIdCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, false);
    }

    @Override
    public IntegerArgumentedState formState() {
        int id = arg1.getValue();
        return new IntegerArgumentedState(id);
//        boolean success = storage.removeById(id);
//        Printer.println(success? "Ticket removed." : "Failed to remove ticket");
    }

    @Override
    protected void runInterpretation(IntegerResult result) {
        if (result.code == 1) {
            Printer.println("Ticket removed");
        } else if (result.code==2) Printer.println("Ticket with specified id doesn't exist.");
        else Printer.println("Failed to remove ticket.");
    }
}
