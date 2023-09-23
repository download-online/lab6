package ru.jefremov.prog.client.interaction.handlers;

/**
 * Класс, отвечающий за создание цепочки обработчиков значения какого-либо типа, действующих по принципу конвеера.
 * @param <T> тип значений, обрабатываемых обработчиком.
 */
public abstract class Handler<T> {
    private Handler<T> next;

    public Handler(Handler<T> next) {
        this.next = next;
    }

    /**
     * Получить обработанное значение
     * @param t исходное значение
     * @return обработанное значение
     */
    protected abstract T apply(T t);

    /**
     * Обработать значение и передать его следующему обработчику, если он есть.
     * @param t исходное значение
     * @return значение, которое будет получено в конце цепочки обработчиков
     */
    public final T handle(T t) {
        T newValue = apply(t);
        if (next==null) {
            return newValue;
        }
        return next.handle(newValue);
    }
}
