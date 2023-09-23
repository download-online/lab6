package ru.jefremov.prog.common.commands.states;

import java.io.Serializable;

public class IntegerArgumentedState extends CommandState implements Serializable {
    public final int number;

    public IntegerArgumentedState(int number) {
        this.number = number;
    }
}
