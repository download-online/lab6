package ru.jefremov.prog.common.models.validation;

import ru.jefremov.prog.common.models.TicketType;

import java.time.LocalDate;

/**
 * Конкретная реализация валидатора билетов.
 * Позволяет валидировать как сами объекты билетов, так и их поля по-отдельности.
 * Помимо проверки валидности, позволяет получить комментарии, поясняющие, почему значение не прошло валидацию.
 */
public class TicketValidator extends AbstractTicketValidator {

    /**
     * Конструктор для конкретного валидатора билетов
     * @param eventValidator валидатор событий
     * @param coordinatesValidator валидатор координат
     */
    public TicketValidator(AbstractEventValidator eventValidator, AbstractCoordinatesValidator coordinatesValidator) {
        super(eventValidator, coordinatesValidator);
    }


    @Override
    public String reviewId(int id) {
        if (id<=0) {
            return "Id should be greater than 0";
        }
        return null;
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
    public String reviewCreationDate(LocalDate creationDate) {
        if (creationDate==null) {
            return "Creation date should represent at least some value";
        }
        return null;
    }

    @Override
    public String reviewPrice(Double price) {
        if (price==null) return null;
        if (price<=0) {
            return "Price should be greater than 0";
        }
        return null;
    }

    @Override
    public String reviewDiscount(double discount) {
        if (discount<=0) {
            return "Discount should be greater than 0";
        }
        if (discount>100) {
            return "Discount shouldn't be greater than 100";
        }
        return null;
    }

    @Override
    public String reviewComment(String comment) {
        if (comment==null) {
            return "Comment should represent at least some value";
        }
        return null;
    }

    @Override
    public String reviewType(TicketType type) {
        return null;
    }


    @Override
    public boolean checkId(int id) {
        return (id>0);
    }

    @Override
    public boolean checkName(String name) {
        return !((name==null) || (name.isBlank()));
    }

    @Override
    public boolean checkCreationDate(LocalDate creationDate) {
        return (creationDate!=null);
    }

    @Override
    public boolean checkPrice(Double price) {
        if (price==null) return true;
        return (price>0);
    }

    @Override
    public boolean checkDiscount(double discount) {
        return (discount>0 & discount<=100);
    }

    @Override
    public boolean checkComment(String comment) {
        return (comment!=null);
    }

    @Override
    public boolean checkType(TicketType type) {
        return true;
    }
}
