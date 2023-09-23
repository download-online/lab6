package ru.jefremov.prog.client.interaction.handlers;


/**
 * Обработчик строк, вынимающий выражение из парных кавычек.
 */
public class QuotationHandler extends Handler<String>{
    /**
     * Конструктор обработчика кавычек без указания следующего обработчика.
     */
    public QuotationHandler() {
        super(null);
    }

    /**
     * Конструктор обработчика кавычек.
     * @param next следующий обработчик
     */
    public QuotationHandler(Handler<String> next) {
        super(next);
    }

    @Override
    protected String apply(String material) {
        if (material==null) return null;
        String s = material.trim();
        if ((s.startsWith("\"") && s.endsWith("\""))||(s.startsWith("'") && s.endsWith("'"))) {
            String quotationMark = String.valueOf(s.charAt(0));
            while (s.startsWith(quotationMark) && s.endsWith(quotationMark)) {
                s = s.substring(1,s.length()-1);
                if (!s.isBlank()) {
                    s = s.trim();
                }
                if (s.isEmpty()) return null;
            }
        }
        return s;
    }
}
