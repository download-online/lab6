package ru.jefremov.prog.common.managers;

import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.AbstractCommand;
import ru.jefremov.prog.common.commands.states.CommandState;
import ru.jefremov.prog.common.exceptions.ExitInterruptionException;
import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

public abstract class CommandManager<T extends AbstractCommand<?,?>> {
    private final HashMap<String, T> commandMap = new HashMap<>();
    private final ArrayList<T> commandList = new ArrayList<>();
    public void addCommand(T command) {
        if (command==null) {
            throw new IllegalArgumentException("Command must not be null");
        }
        commandMap.put(command.getWord(),command);
        commandList.add(command);
        commandList.sort(Comparator.comparing(c -> c.word));
    }
    public abstract void launchCommand(String word, String line) throws CommandLaunchException, ExitInterruptionException, QuitInterruptionException;
    public Collection<String> getCommandWords() {
        return commandMap.keySet();
    }

    public T getCommand(String word) {
        return commandMap.get(word);
    }
}
