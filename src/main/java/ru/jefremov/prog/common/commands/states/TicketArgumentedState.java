package ru.jefremov.prog.common.commands.states;


import ru.jefremov.prog.common.models.Ticket;

public class TicketArgumentedState extends CommandState {
    public final Ticket ticket;

    public TicketArgumentedState(Ticket ticket) {
        this.ticket = ticket;
    }
}
