package ru.jefremov.prog.common.models.validation;

import ru.jefremov.prog.common.models.EventType;

/**
 * Конкретная реализация валидатора событий.
 * Позволяет валидировать как сами объекты событий, так и их поля по-отдельности.
 * Помимо проверки валидности, позволяет получить комментарии, поясняющие, почему значение не прошло валидацию.
 */
public class EventValidator extends AbstractEventValidator{


    @Override
    public String reviewId(long id) {
        if (id>0) {
            return null;
        }
        return "Id should be greater than 0";
    }

    @Override
    public String reviewName(String name) {
        if (name==null) {
            return "Name should represent at least some value";
        }
        if (name.isBlank()) {
            return "Name should not be empty";
        }
        return null;
    }

    @Override
    public String reviewTicketsCount(Long ticketsCount) {
        if (ticketsCount==null) {
            return "Tickets count should represent at least some value";
        }
        if (ticketsCount<=0) {
            return "Tickets count should be greater than 0";
        }
        return null;
    }

    @Override
    public String reviewEventType(EventType eventType) {
        if (eventType==null) {
            return "Event type should represent at least some value";
        }
        return null;
    }

    @Override
    public boolean checkId(long id) {
        return (id>0);
    }

    @Override
    public boolean checkName(String name) {
        return !((name==null) || (name.isBlank()));
    }

    @Override
    public boolean checkTicketsCount(Long ticketsCount) {
        return !((ticketsCount==null) || (ticketsCount<=0));
    }

    @Override
    public boolean checkEventType(EventType eventType) {
        return (eventType!=null);
    }
}
