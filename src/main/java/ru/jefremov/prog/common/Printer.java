package ru.jefremov.prog.common;

/**
 * Класс, обеспечивающий произвольный вывод сообщений.
 * По умолчанию ничем не отличается от стандартного вывода сообщений в стандартный поток.
 */
public class Printer {
    /**
     * Вывести объект с переводом строки
     * @param object объект
     */
    public static void println(Object object) {
        System.out.println(object);
    }

    /**
     * Вывести объект без перевода строки
     * @param object объект
     */
    public static void print(Object object) {
        System.out.print(object);
    }
}
