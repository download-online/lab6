package ru.jefremov.prog.common.models.validation;

import ru.jefremov.prog.common.models.Event;
import ru.jefremov.prog.common.models.EventType;

/**
 * Абстракция валидатора событий.
 * Позволяет валидировать как сами объекты событий, так и их поля по-отдельности.
 * Помимо проверки валидности, позволяет получить комментарии, поясняющие, почему значение не прошло валидацию.
 */
public abstract class AbstractEventValidator {
    /**
     * Проанализировать id
     * @param id id
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewId(long id);
    /**
     * Проанализировать название
     * @param name название
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewName(String name);
    /**
     * Проанализировать количество билетов
     * @param ticketsCount количество билетов
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewTicketsCount(Long ticketsCount);
    /**
     * Проанализировать тип
     * @param eventType тип
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewEventType(EventType eventType);
    /**
     * Проанализировать событие целиком
     * @param event событие
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public String reviewEvent(Event event) {
        if (event==null) {
            return "Null event";
        }
        String review1 = reviewId(event.getId());
        if (review1!=null) {
            return review1;
        }
        String review2 = reviewName(event.getName());
        if (review2!=null) {
            return review2;
        }
        String review3 = reviewTicketsCount(event.getTicketsCount());
        if (review3!=null) {
            return review3;
        }
        return reviewEventType(event.getEventType());
    }

    /**
     * Проверить id
     * @param id id
     * @return результат проверки
     */
    public abstract boolean checkId(long id);
    /**
     * Проверить название
     * @param name название
     * @return результат проверки
     */
    public abstract boolean checkName(String name);
    /**
     * Проверить количество билетов
     * @param ticketsCount количество билетов
     * @return результат проверки
     */
    public abstract boolean checkTicketsCount(Long ticketsCount);
    /**
     * Проверить тип
     * @param eventType тип
     * @return результат проверки
     */
    public abstract boolean checkEventType(EventType eventType);
    /**
     * Проверить событие целиком
     * @param event событие
     * @return результат проверки
     */
    public boolean checkEvent(Event event) {
        if (event==null) {
            return false;
        }
        return checkId(event.getId()) & checkName(event.getName()) &
                checkTicketsCount(event.getTicketsCount()) &
                checkEventType(event.getEventType());
    }
}
