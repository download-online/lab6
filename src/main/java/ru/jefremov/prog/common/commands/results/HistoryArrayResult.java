package ru.jefremov.prog.common.commands.results;

import ru.jefremov.prog.common.commands.HistoryRecord;

import java.io.Serializable;

public class HistoryArrayResult extends CommandResult implements Serializable {
    public final HistoryRecord[] history;

    public HistoryArrayResult(HistoryRecord[] history) {
        this.history = history;
    }
}
