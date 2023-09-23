package ru.jefremov.prog.client.managers;


import ru.jefremov.prog.client.exceptions.script.ScriptRecursionDepthException;
import ru.jefremov.prog.client.exceptions.script.ScriptRecursionException;
import ru.jefremov.prog.client.interaction.Script;
import ru.jefremov.prog.client.interaction.Submitter;
import ru.jefremov.prog.common.Printer;

import java.util.ArrayDeque;

/**
 * Класс, обеспечивающий контроль за скриптами.
 */
public class ScriptManager implements Submitter<String> {
    private final ArrayDeque<Script> scriptQueue = new ArrayDeque<>();
    private final int capacity;

    /**
     * Конструктор менеджера скриптов
     * @param capacity максимальное количество параллельно работающих скриптов
     */
    public ScriptManager(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Запустить скрипт
     * @param script объект скрипта
     * @throws ScriptRecursionException вызывается, если скрипт вызывает сам себя (косвенно или напрямую).
     * @throws ScriptRecursionDepthException вызывается, если скрипт рекурсивно запускает слишком много скриптов.
     */
    public void startScript(Script script) throws ScriptRecursionException, ScriptRecursionDepthException {
        if (script==null) {
            throw new IllegalArgumentException("Script must not be null");
        }
        if (scriptQueue.size()==capacity) {
            String tracing = traceLine();
            stopScriptRunning();
            throw new ScriptRecursionDepthException(tracing);
        } else if (scriptQueue.contains(script)) {
            String tracing = traceLine();
            stopScriptRunning();
            throw new ScriptRecursionException(tracing);
        }
        scriptQueue.add(script);
    }

    /**
     * Останавливает текущий скрипт
     */
    public void stopCurrentScript() {
        scriptQueue.removeLast();
    }

    /**
     * Завершает выполнение всех скриптов.
     */
    public void stopScriptRunning() {
        scriptQueue.clear();
    }

    @Override
    public boolean hasNext() {
        if (!scriptQueue.isEmpty()) {
            if (!scriptQueue.getLast().hasNext()) {
                stopCurrentScript();
                return hasNext();
            }
            return true;
        }
        return false;
    }

    @Override
    public String next() {
        String line = scriptQueue.getLast().next();
        if (!line.contains("\n") && !line.isEmpty()) Printer.println(line);
        return line;
    }

    /**
     * Получение информации о последней выполненной команде, с учётом рекурсии скриптов.
     * @return строка с трассировкой
     */
    public String traceLine() {
        StringBuilder tracing = new StringBuilder();
        for (Script script : scriptQueue) {
            tracing.append("at ").append(script.getTrace()).append("\n");
        }
        return tracing.toString();
    }
}
