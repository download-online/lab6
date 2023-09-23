package ru.jefremov.prog.common.network;

import ru.jefremov.prog.common.commands.results.CommandResult;

import java.io.Serializable;

public class Response implements Serializable {
    public final String text;
    public final Status status;
    public final CommandResult result;

    public Response(String text, Status status, CommandResult result) {
        this.text = text;
        this.status = status;
        this.result = result;
    }
}
