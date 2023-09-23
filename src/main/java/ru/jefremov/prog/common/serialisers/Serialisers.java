package ru.jefremov.prog.common.serialisers;

import ru.jefremov.prog.common.commands.HistoryRecord;
import ru.jefremov.prog.common.commands.results.CommandResult;
import ru.jefremov.prog.common.commands.states.CommandState;
import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.common.network.Request;
import ru.jefremov.prog.common.network.Response;

public class Serialisers {
    public static Serialiser<Ticket> ticketSerialiser = new Serialiser<>();
    public static Serialiser<CommandState> stateSerialiser = new Serialiser<>();
    public static Serialiser<CommandResult> resultSerialiser = new Serialiser<>();
    public static Serialiser<HistoryRecord> historyRecordSerialiser = new Serialiser<>();
    public static Serialiser<Request> requestSerialiser = new Serialiser<>();
    public static Serialiser<Response> responseSerialiser = new Serialiser<>();
}
