package ru.jefremov.prog.common.models;

import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Класс события. Хранится в коллекции.
 */
public class Event implements Comparable<Event>, Serializable {
    private static long nextId = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Expose
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Expose
    private Long ticketsCount; //Поле не может быть null, Значение поля должно быть больше 0
    @Expose
    private EventType eventType; //Поле не может быть null
    private boolean initialised;

    /**
     * Конструктор для события
     * @param name название
     * @param ticketsCount количество билетов
     * @param eventType тип события
     */
    public Event(String name, Long ticketsCount, EventType eventType) {
        this.id = nextId++;
        this.name = name;
        this.ticketsCount = ticketsCount;
        this.eventType = eventType;
    }

    private Event() {
        this.id = nextId++;
    }

    /**
     * Геттер для идентификатора
     * @return идентификатор
     */
    public long getId() {
        return id;
    }

    /**
     * Геттер для названия
     * @return название
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер для количества билетов
     * @return количество билетов
     */
    public Long getTicketsCount() {
        return ticketsCount;
    }

    /**
     * Геттер для типа
     * @return тип
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Проверка, что у двух событий совпадают все поля, кроме Id
     * @param other другое событие
     * @return результат проверки
     */
    public boolean identical(Event other) {
        if (other==null) {
            throw new IllegalArgumentException("Cannot compare event with null");
        }
        return (Objects.equals(name, other.name)) && (Objects.equals(ticketsCount, other.ticketsCount)) && (eventType==other.eventType);
    }

    
    @Override
    public String toString() {
        return eventType.toString().toLowerCase()+" event #" + id +
                " <" + name + '>' +
                " with " + ticketsCount+" tickets";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event that = (Event) o;

        if (eventType!=that.eventType) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        if (!ticketsCount.equals(that.ticketsCount)) {
            return false;
        }
        return (id==that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(eventType, name, ticketsCount, id);
    }
    
    @Override
    public int compareTo(Event other) {
        if (other==null) {
            return 1;
        }
        if (eventType == other.eventType) {
            if (name.equals(other.name)) {
                if (ticketsCount.equals(other.ticketsCount)) {
                    return Long.compare(id,other.id);
                }
                return Long.compare(ticketsCount,other.ticketsCount);
            }
            return name.compareTo(other.name);
        }
        return eventType.compareTo(other.eventType);
    }


    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        if (!initialised) {
            id = nextId++;
            initialised=true;
        }
    }
}