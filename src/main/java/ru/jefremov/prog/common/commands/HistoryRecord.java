package ru.jefremov.prog.common.commands;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HistoryRecord implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String word;
    private final LocalTime launched;
    public HistoryRecord(String word) {
        this.word = word;
        this.launched = LocalTime.now();
    }

    @Override
    public String toString() {
        return launched.format(formatter)+" "+word;
    }
}
