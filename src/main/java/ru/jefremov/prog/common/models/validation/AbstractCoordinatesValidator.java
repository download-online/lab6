package ru.jefremov.prog.common.models.validation;


import ru.jefremov.prog.common.models.Coordinates;

/**
 * Абстракция валидатора координат.
 * Позволяет валидировать как сами объекты координат, так и их поля по-отдельности.
 * Помимо проверки валидности, позволяет получить комментарии, поясняющие, почему значение не прошло валидацию.
 */
public abstract class AbstractCoordinatesValidator {
    /**
     * Проанализировать X
     * @param x X
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewX(int x);
    /**
     * Проанализировать Y
     * @param y Y
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public abstract String reviewY(Double y);
    /**
     * Проанализировать координаты целиком.
     * @param coordinates координаты
     * @return Комментарий, если значение некорректно. Иначе - null
     */
    public String reviewCoordinates(Coordinates coordinates) {
        if (coordinates==null) {
            return "Null coordinates";
        }
        String review1 = reviewX(coordinates.getX());
        if (review1!=null) {
            return review1;
        }
        return reviewY(coordinates.getY());
    }

    /**
     * Проверить x
     * @param x X
     * @return Результат проверки
     */
    public abstract boolean checkX(int x);
    /**
     * Проверить y
     * @param y Y
     * @return Результат проверки
     */
    public abstract boolean checkY(Double y);
    /**
     * Проверить координаты целиком
     * @param coordinates координаты
     * @return Результат проверки
     */
    public boolean checkCoordinates(Coordinates coordinates) {
        if (coordinates==null) {
            return false;
        }
        return checkX(coordinates.getX()) & checkY(coordinates.getY());
    }
}
