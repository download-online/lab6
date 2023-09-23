package ru.jefremov.prog.common.commands.results;

import java.io.Serializable;

public class IntegerResult extends CommandResult implements Serializable {
    public final int code;

    public IntegerResult(int code) {
        this.code = code;
    }
}
