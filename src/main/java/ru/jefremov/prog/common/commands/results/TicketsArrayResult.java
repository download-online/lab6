package ru.jefremov.prog.common.commands.results;


import ru.jefremov.prog.common.models.Ticket;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class TicketsArrayResult extends CommandResult implements Serializable {
    public final Ticket[] tickets;

    public TicketsArrayResult(Ticket[] tickets) {
        this.tickets = tickets;
    }
    public TicketsArrayResult(List<Ticket> tickets) {
        if (tickets!=null) {
            tickets.sort(null);
            this.tickets = tickets.toArray(new Ticket[0]);
        } else {
            this.tickets = new Ticket[0];
        }
    }
}
