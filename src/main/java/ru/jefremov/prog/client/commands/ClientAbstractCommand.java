package ru.jefremov.prog.client.commands;


import ru.jefremov.prog.client.commands.arguments.AbstractArgument;
import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.client.exceptions.command.*;
import ru.jefremov.prog.client.managers.ClientAdministrator;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.commands.AbstractCommand;
import ru.jefremov.prog.common.commands.results.CommandResult;
import ru.jefremov.prog.common.commands.states.CommandState;
import ru.jefremov.prog.common.exceptions.ExitInterruptionException;
import ru.jefremov.prog.common.exceptions.command.*;

/**
 * Абстракция команды.
 */
public abstract class ClientAbstractCommand<T extends CommandState, U extends CommandResult> extends AbstractCommand<T, U> implements Argumentable {

    /**
     * парсер аргументов
     */
    public final Parser parser;
    /**
     * администратор
     */
    public final ClientAdministrator administrator;
    /**
     * менеджер команд
     */
    public final ClientCommandManager manager;

    public final boolean reflexing;
    public final boolean requiresLargeArrays;

    /**
     * Конструктор команды с указанием описания
     *
     * @param word        ключевое слово
     * @param manager     менеджер команд, к которому она привязана
     * @param description описание
     * @param reflexing интерпретирует ли результат
     */
    public ClientAbstractCommand(String word, ClientCommandManager manager, String description, boolean reflexing, boolean requiresLargeArrays) {
        super(word,description);
        this.reflexing = reflexing;
        this.requiresLargeArrays = requiresLargeArrays;
        this.parser = new Parser(this);
        if (manager==null) throw new IllegalArgumentException("Command manager must not be null");
        manager.addCommand(this);
        this.manager = manager;
        this.administrator = manager.administrator;
    }

    /**
     * Конструктор команды без описания
     *
     * @param word      ключевое слово
     * @param manager   менеджер команд, к которому она привязана
     * @param reflexing интерпретирует ли результат
     */
    public ClientAbstractCommand(String word, ClientCommandManager manager, boolean reflexing, boolean requiresLargeArrays) {
        this(word,manager,"", reflexing, requiresLargeArrays);
    }

    /**
     * Метод для запуска команды.
     * @param line строка с аргументами
     * @throws InvalidCommandArgumentException в случае, если аргумент не подходит по формату
     * @throws IllegalCommandArgumentException в случае, если аргумент не подходит по значению
     * @throws WrongCommandFormatException в случае, если нарушен общий формат команды с аргументами
     * @throws CommandInterruptionException в случае, если команда прерывает свой запуск
     * @throws ExitInterruptionException в случае, если программа завершается без сохранения
     * @throws QuitInterruptionException в случае, если пользователь прервал процедуру
     */
    public final T launch(String line) throws InvalidCommandArgumentException, IllegalCommandArgumentException, WrongCommandFormatException, CommandInterruptionException, ExitInterruptionException, QuitInterruptionException {
        parser.parse(line);
        return formState();
    }

    protected abstract T formState() throws ExitInterruptionException;


    public final U launch(CommandState state) throws CommandLaunchException {
        try {
            T castedState = (T) state;
            return execute(castedState);
        } catch (ClassCastException e) {
            throw new CommandLaunchException("Attempted to launch command from wrong state");
        }
    }

    protected U execute(T state) {
        return null;
    }

    public final void interpretResult(CommandResult result) throws CommandLaunchException {
        try {
            U castedResult = (U) result;
            runInterpretation(castedResult);
        } catch (ClassCastException e) {
            throw new CommandLaunchException("Attempted to launch command from wrong state");
        }
    }

    protected void runInterpretation(U result) {

    }

    @Override
    public final ClientAbstractCommand<?,?> referToCommand() {
        return this;
    }

    @Override
    public final void processAccepting(AbstractArgument<?> argument) {
        parser.acceptArgument(argument);
    }

    @Override
    public String toString() {
        return parser.getCommandFormat()+(description.equals("")? "" : "\n--- "+description);
    }
}
