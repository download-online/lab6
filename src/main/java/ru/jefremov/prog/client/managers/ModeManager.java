package ru.jefremov.prog.client.managers;

import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.client.exceptions.script.ScriptRecursionDepthException;
import ru.jefremov.prog.client.exceptions.script.ScriptRecursionException;
import ru.jefremov.prog.client.interaction.Script;
import ru.jefremov.prog.client.interaction.Submitter;
import ru.jefremov.prog.client.interaction.handlers.*;
import ru.jefremov.prog.client.modes.InteractiveMode;
import ru.jefremov.prog.client.modes.*;

/**
 * Класс, ответственный за управление режимами программы.
 */
public class ModeManager  implements Submitter<String> {
    private Mode currentMode;
    private final InteractiveMode interactiveMode;
    private final ScriptMode scriptMode;
    private final Handler<String> handlerChain = new EmptinessHandler();

    /**
     * Конструктор менеджера режимов работы.
     * @param interactiveMode интерактивный режим
     * @param scriptMode скриптовой режим
     */
    public ModeManager(InteractiveMode interactiveMode, ScriptMode scriptMode) {
        this.interactiveMode = interactiveMode;
        this.scriptMode = scriptMode;
        this.currentMode = interactiveMode;
    }

    @Override
    public boolean hasNext() throws QuitInterruptionException {
        boolean currentHas = currentMode.hasNext();
        if (!currentHas) {
            if (currentMode.isAutoSwitching()) {
                String endingMessage = getEndingMessage();
                currentMode = currentMode.getNextMode();
                throw new QuitInterruptionException(endingMessage);
            }
            return false;
        }
        return true;
    }

    @Override
    public String next() {
        String line = currentMode.next();
        if (line==null) return null;
        if (line.contains("\n") || line.isEmpty()) line = currentMode.next();
        return handlerChain.handle(line);
    }

    /**
     * Запуск скрипта
     * @param script объект скрипта
     * @throws ScriptRecursionException вызывается, если скрипт вызывает сам себя (косвенно или напрямую).
     * @throws ScriptRecursionDepthException вызывается, если скрипт рекурсивно запускает слишком много скриптов.
     */
    public void startScript(Script script) throws ScriptRecursionException, ScriptRecursionDepthException {
        this.currentMode = scriptMode;
        scriptMode.startScript(script);
    }

    /**
     * Геттер для определения отзывчивости текущего режима.
     * @return отзывчивость режима
     */
    public boolean canRespond() {
        return currentMode.isResponsive();
    }

    /**
     * Завершить работу текущего режима
     * @return сменился ли завершённый режим на следующий автоматически.
     */
    public boolean interrupt() {
        if (currentMode.isAutoSwitching()) {
            currentMode = currentMode.finish();
            return true;
        }
        return false;
    }

    /**
     * Формирует сообщение о завершении работы текущего режима.
     * @return сообщение
     */
    public String getEndingMessage() {
        return "..."+currentMode.name+" ended.";
    }
}
