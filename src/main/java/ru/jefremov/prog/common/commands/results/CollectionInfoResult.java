package ru.jefremov.prog.common.commands.results;

import java.io.Serializable;

public class CollectionInfoResult extends CommandResult implements Serializable {
    public final String info;

    public CollectionInfoResult(String info) {
        this.info = info;
    }
}
