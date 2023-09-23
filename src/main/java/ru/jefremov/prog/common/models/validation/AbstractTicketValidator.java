package ru.jefremov.prog.common.models.validation;

import ru.jefremov.prog.common.models.Coordinates;
import ru.jefremov.prog.common.models.Event;
import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.common.models.TicketType;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

/**
 * Абстракция валидатора билетов.
 * Позволяет валидировать как сами объекты билетов, так и их поля по-отдельности.
 * Помимо проверки валидности, позволяет получить комментарии, поясняющие, почему значение не прошло валидацию.
 */
public abstract class AbstractTicketValidator {
    private final AbstractEventValidator eventValidator;
    private final AbstractCoordinatesValidator coordinatesValidator;

    /**
     * Конструктор для валидатора билетов
     * @param eventValidator валидатор событий
     * @param coordinatesValidator валидатор координат
     */
    public AbstractTicketValidator(AbstractEventValidator eventValidator, AbstractCoordinatesValidator coordinatesValidator) {
        this.eventValidator = requireNonNull(eventValidator, "event validator must not be null");
        this.coordinatesValidator = requireNonNull(coordinatesValidator,"coordinates validator must not be null");
    }

    /**
     * Проанализировать id
     * @param id id
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewId(int id);
    /**
     * Проанализировать имя
     * @param name имя
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewName(String name);
    /**
     * Проанализировать координаты
     * @param coordinates id
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public String reviewCoordinates(Coordinates coordinates) {
        return coordinatesValidator.reviewCoordinates(coordinates);
    }

    /**
     * Проанализировать дату создания
     * @param creationDate дата создания
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewCreationDate(LocalDate creationDate);

    /**
     * Проанализировать цену
     * @param price цена
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewPrice(Double price);

    /**
     * Проанализировать скидку
     * @param discount скидка
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewDiscount(double discount);

    /**
     * Проанализировать комментарий
     * @param comment комментарий
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewComment(String comment);

    /**
     * Проанализировать тип
     * @param type тип
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewType(TicketType type);

    /**
     * Проанализировать событие
     * @param event событие
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public String reviewEvent(Event event) {
        return eventValidator.reviewEvent(event);
    }

    /**
     * Проанализировать билет целиком
     * @param ticket билет
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public String reviewTicket(Ticket ticket) {
        if (ticket==null) {
            return "Null ticket";
        }
        String review1 = reviewId(ticket.getId());
        if (review1!=null) {
            return review1;
        }
        String review2 = reviewName(ticket.getName());
        if (review2!=null) {
            return review2;
        }
        String review3 = reviewCoordinates(ticket.getCoordinates());
        if (review3!=null) {
            return review3;
        }
        String review4 = reviewCreationDate(ticket.getCreationDate());
        if (review4!=null) {
            return review4;
        }
        String review5 = reviewPrice(ticket.getPrice());
        if (review5!=null) {
            return review5;
        }
        String review6 = reviewDiscount(ticket.getDiscount());
        if (review6!=null) {
            return review6;
        }
        String review7 = reviewComment(ticket.getComment());
        if (review7!=null) {
            return review7;
        }
        String review8 = reviewType(ticket.getType());
        if (review8!=null) {
            return review8;
        }
        return reviewEvent(ticket.getEvent());
    }

    /**
     * Проверить id
     * @param id id
     * @return результат проверки
     */
    public abstract boolean checkId(int id);
    /**
     * Проверить имя
     * @param name имя
     * @return результат проверки
     */
    public abstract boolean checkName(String name);
    /**
     * Проверить координаты
     * @param coordinates координаты
     * @return результат проверки
     */
    public boolean checkCoordinates(Coordinates coordinates) {
        return coordinatesValidator.checkCoordinates(coordinates);
    }
    /**
     * Проверить дату создания
     * @param creationDate дата создания
     * @return результат проверки
     */
    public abstract boolean checkCreationDate(LocalDate creationDate);
    /**
     * Проверить цену
     * @param price цена
     * @return результат проверки
     */
    public abstract boolean checkPrice(Double price);
    /**
     * Проверить скидку
     * @param discount скидка
     * @return результат проверки
     */
    public abstract boolean checkDiscount(double discount);
    /**
     * Проверить комментарий
     * @param comment комментарий
     * @return результат проверки
     */
    public abstract boolean checkComment(String comment);
    /**
     * Проверить
     * @param type тип
     * @return результат проверки
     */
    public abstract boolean checkType(TicketType type);
    /**
     * Проверить событие
     * @param event событие
     * @return результат проверки
     */
    public boolean checkEvent(Event event) {
        return eventValidator.checkEvent(event);
    }
    /**
     * Проверить билет целиком
     * @param ticket билет
     * @return результат проверки
     */
    public boolean checkTicket(Ticket ticket) {
        if (ticket==null) {
            return false;
        }
        return checkId(ticket.getId()) &
                checkName(ticket.getName()) &
                checkCoordinates(ticket.getCoordinates()) &
                checkCreationDate(ticket.getCreationDate()) &
                checkPrice(ticket.getPrice()) &
                checkDiscount(ticket.getDiscount()) &
                checkComment(ticket.getComment()) &
                checkType(ticket.getType()) &
                checkEvent(ticket.getEvent());
    }
}
