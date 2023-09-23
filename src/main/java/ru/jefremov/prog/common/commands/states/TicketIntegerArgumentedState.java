package ru.jefremov.prog.common.commands.states;


import ru.jefremov.prog.common.models.Ticket;

import java.io.Serializable;

public class TicketIntegerArgumentedState extends CommandState implements Serializable {
    public final Ticket ticket;
    public final int number;

    public TicketIntegerArgumentedState(Ticket ticket,int number) {
        this.ticket = ticket;
        this.number = number;
    }
}
