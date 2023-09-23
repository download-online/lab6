package ru.jefremov.prog.client.modes;

import ru.jefremov.prog.client.exceptions.script.ScriptRecursionDepthException;
import ru.jefremov.prog.client.exceptions.script.ScriptRecursionException;
import ru.jefremov.prog.client.interaction.Script;
import ru.jefremov.prog.client.managers.ScriptManager;
import ru.jefremov.prog.common.Printer;

/**
 * Класс для режима работы, при котором команды поставляются из скрипта.
 */
public class ScriptMode extends Mode{
    /**
     * Конструктор для скриптового режима
     * @param next следующий режим
     * @param submitter поставщик комманд из скриптов
     */
    public ScriptMode(Mode next, ScriptManager submitter) {
        super("script", next, submitter, false, true);
    }

    @Override
    public Mode finish() {
        getSubmitter().stopScriptRunning();
        Printer.println("Script execution finished.");
        return getNextMode();
    }

    @Override
    public ScriptManager getSubmitter() {
        return (ScriptManager) super.getSubmitter();
    }

    /**
     * Запуск скрипта
     * @param script объект скрипта
     * @throws ScriptRecursionException вызывается, если скрипт вызывает сам себя (косвенно или напрямую).
     * @throws ScriptRecursionDepthException вызывается, если скрипт рекурсивно запускает слишком много скриптов.
     */
    public void startScript(Script script) throws ScriptRecursionException, ScriptRecursionDepthException {
        getSubmitter().startScript(script);
    }
}
