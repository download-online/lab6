package ru.jefremov.prog.client.modes;


import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.client.interaction.Submitter;

import java.util.NoSuchElementException;

/**
 * Абстракция режима работы программы
 */
public abstract class Mode implements Submitter<String> {
    private final Mode next;
    /**
     * Название режима
     */
    public final String name;
    private final Submitter<String> submitter;
    private final boolean responsive;
    private final boolean autoSwitching;

    /**
     * Конструктор режима
     * @param name название режима
     * @param next следующий режим
     * @param submitter источник строк
     * @param responsive может ли гибко реагировать на ошибки для их исправления
     * @param autoSwitching переключится ли автоматически на следующий режим при завершении
     */
    public Mode(String name, Mode next, Submitter<String> submitter, boolean responsive, boolean autoSwitching) {
        this.name = ((name==null||name.isBlank())? "mode" : name);
        this.next = next;
        if (submitter==null) {
            throw new IllegalArgumentException("Submitter must not be null");
        }
        this.submitter = submitter;
        this.responsive = responsive;
        this.autoSwitching = autoSwitching;
        if ((next==null) & autoSwitching) {
            throw new IllegalArgumentException("No next mode specified, but auto-switching turned on");
        }
    }

    /**
     * Геттер для следующего режима
     * @return следующий режим
     */
    public Mode getNextMode() {
        return next;
    }

    
    public boolean hasNext() throws QuitInterruptionException {
        return submitter.hasNext();
    }

    
    public String next() {
        try {
            return submitter.next();
        } catch (NoSuchElementException e) {
            finish();
            return null;
        }
    }
    /**
     * Геттер для определения отзывчивости режима
     * @return отзывчивость режима
     */
    public boolean isResponsive() {
        return responsive;
    }

    /**
     * Геттер для определения автопереключаемости режима
     * @return автопереключаемость режима
     */
    public boolean isAutoSwitching() {
        return autoSwitching;
    }

    /**
     * Завершает режим
     */
    public abstract Mode finish();

    /**
     * Геттер для поставщика строк
     * @return поставщик строк.
     */
    public Submitter<String> getSubmitter() {
        return submitter;
    }
}
