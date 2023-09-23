package ru.jefremov.prog.common.commands.states;


import ru.jefremov.prog.common.models.Event;

import java.io.Serializable;

public class EventArgumentedState extends CommandState implements Serializable {
    public final Event event;

    public EventArgumentedState(Event event) {
        this.event = event;
    }
}
