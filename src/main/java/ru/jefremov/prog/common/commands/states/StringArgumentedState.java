package ru.jefremov.prog.common.commands.states;

import java.io.Serializable;

public class StringArgumentedState extends CommandState implements Serializable {
    public final String line;

    public StringArgumentedState(String line) {
        this.line = line;
    }
}
