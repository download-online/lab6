package ru.jefremov.prog.common.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс координат. Хранится в коллекции.
 */
public class Coordinates implements Comparable<Coordinates>, Serializable {
    /**
     * Конструктор для координат
     * @param x икс
     * @param y игрек
     */
    public Coordinates(int x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Expose
    private final int x; //Значение поля должно быть больше -915
    @Expose
    private final Double y; //Поле не может быть null

    /**
     * Геттер для X
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     * Геттер для Y
     * @return Y
     */
    public Double getY() {
        return y;
    }

    /**
     * Проверяет, что у двух координат одинаковые поля. По аналогии с билетами и событиями.
     * @param other
     * @return
     */
    public boolean identical(Coordinates other) {
        if (other==null) {
            throw new IllegalArgumentException("Cannot compare coordinates with null");
        }
        return (x==other.x) && (Objects.equals(y, other.y));
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (!Objects.equals(y, that.y)) {
            return false;
        }
        return (x == that.x);
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(y,x);
    }


    @Override
    public String toString() {
        return "[" + x + " ― "+y+"]";
    }


    @Override
    public int compareTo(Coordinates other) {
        if (other==null) {
            return 1;
        }
        if (Objects.equals(y, other.y)) {
            return x-other.x;
        }
        return Double.compare(y,other.y);
    }
}