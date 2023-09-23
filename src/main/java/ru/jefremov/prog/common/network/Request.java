package ru.jefremov.prog.common.network;

import ru.jefremov.prog.common.commands.states.CommandState;

import java.io.Serializable;

public class Request implements Serializable {
    public final String word;
    public final CommandState state;
    public final boolean requiresLargeArrays;

    public Request(String word, CommandState state, boolean requiresLargeArrays) {
        this.word = word;
        this.state = state;
        this.requiresLargeArrays = requiresLargeArrays;
    }
}
