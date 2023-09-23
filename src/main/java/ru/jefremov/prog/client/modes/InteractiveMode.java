package ru.jefremov.prog.client.modes;


import ru.jefremov.prog.client.interaction.InteractiveSubmitter;

/**
 * Класс отвечает за режим программы, когда команды считываются со стандартного ввода.
 * Проще говоря, интерактивный режим.
 */
public class InteractiveMode extends Mode{
    /**
     * Конструктор для интерактивного режима
     * @param next следующий режим
     * @param submitter интерактивный поставщик строк
     */
    public InteractiveMode(Mode next, InteractiveSubmitter submitter) {
        super("user input", next, submitter, true, false);
    }

    
    @Override
    public Mode finish() {
        return getNextMode();
    }
}
