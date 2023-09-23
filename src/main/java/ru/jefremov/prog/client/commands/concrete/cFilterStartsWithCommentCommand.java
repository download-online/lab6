package ru.jefremov.prog.client.commands.concrete;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.concrete.TicketCommentArgument;
import ru.jefremov.prog.client.commands.arguments.primitive.StringArgument;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;

import java.util.Arrays;

/**
 * Команда, выводящая билеты, чей комментарий начинается с заданной подстроки.
 */
public class cFilterStartsWithCommentCommand extends ClientAbstractCommand<StringArgumentedState, TicketsArrayResult> {
    private final StringArgument arg1 = new TicketCommentArgument("comment", ArgumentPlacement.INLINE,this,null);
    public cFilterStartsWithCommentCommand(String word, ClientCommandManager manager) {
        super(word, manager, true, true);
    }

    public cFilterStartsWithCommentCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, true, true);
    }

    @Override
    public StringArgumentedState formState() {
        String comment = arg1.getValue();
        return new StringArgumentedState(comment);
//        boolean success = storage.printFilterStartsWithComment(comment);
//        if (!success) Printer.println("No ticket has a comment starting from the specified line.");
    }

    @Override
    protected void runInterpretation(TicketsArrayResult result) {
        if (result.tickets==null || result.tickets.length == 0) {
            Printer.println("No ticket has a comment starting from the specified line.");
        } else {
            Arrays.stream(result.tickets).forEachOrdered(Printer::println);
        }
    }
}
