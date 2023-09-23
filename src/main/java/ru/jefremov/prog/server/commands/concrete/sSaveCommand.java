package ru.jefremov.prog.server.commands.concrete;

import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.*;
import ru.jefremov.prog.common.commands.states.*;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.exceptions.SavedCollectionInteractionException;
import ru.jefremov.prog.server.managers.ServerCommandManager;

public class sSaveCommand extends ServerAbstractCommand<CommandState, CommandResult>{
    public sSaveCommand(String word, String description, ServerCommandManager manager) {
        super(word, description, manager, true);
    }

    @Override
    protected CommandResult execute(CommandState state) {
        try {
            administrator.collectionFileInteraction.saveCollection(storage.getCollectionCopy());
            Printer.println("The collection was successfully saved.");
        } catch (SavedCollectionInteractionException e) {
            Printer.println(e.getMessage());
            Printer.println("Collection saving failed");
        }
        return new CommandResult();
    }
}
