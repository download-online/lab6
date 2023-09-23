package ru.jefremov.prog.client.interaction.handlers;

/**
 * Обработчик строк, отвечающий за преобразование строки в null
 */
public class EmptinessHandler extends Handler<String>{

    /**
     * Конструктор обработчика пустых строк без указания следующего обработчика.
     */
    public EmptinessHandler() {
        super(null);
    }

    /**
     * Конструктор обработчика пустых строк.
     * @param next следующий обработчик
     */
    public EmptinessHandler(Handler<String> next) {
        super(next);
    }

    @Override
    protected String apply(String s) {
        if (s==null || s.isEmpty()) {
            return null;
        }

        return s.trim();
    }
}
