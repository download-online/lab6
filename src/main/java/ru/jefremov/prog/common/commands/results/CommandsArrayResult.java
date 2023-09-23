package ru.jefremov.prog.common.commands.results;

import java.io.Serializable;

public class CommandsArrayResult extends CommandResult implements Serializable {
    public final String[] words;

    public CommandsArrayResult(String[] words) {
        this.words = words;
    }
}
