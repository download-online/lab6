package ru.jefremov.prog.client.interaction;


import ru.jefremov.prog.client.exceptions.QuitInterruptionException;

/**
 * Абстракция поставщика значений определённого типа.
 * @param <T> тип поставляемых значений
 */
public interface Submitter<T> {
    /**
     * Проверка, можно ли получить следующее значение.
     */
    boolean hasNext() throws QuitInterruptionException;
    /**
     * Метод для получения следующего значения.
     */
    T next();
}
