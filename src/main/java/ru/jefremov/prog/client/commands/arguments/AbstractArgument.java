package ru.jefremov.prog.client.commands.arguments;


import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.client.exceptions.command.*;
import ru.jefremov.prog.client.managers.ClientAdministrator;
import ru.jefremov.prog.client.managers.ModeManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.exceptions.command.*;


import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

/**
 * Абстракция аргумента, вместе с которым команде передаётся значение какого-то типа.
 * @param <T> тип передаваемого значения.
 */
public abstract class AbstractArgument<T> {
    private T value;
    private final ClientAbstractCommand<?,?> command;
    /**
     * администратор
     */
    public final ClientAdministrator administrator;
    /**
     * менеджер режимов работы
     */
    public final ModeManager modeManager;
    private final boolean attached;
    /**
     * расположение аргумента
     */
    public final ArgumentPlacement placement;
    /**
     * регулярное выражение, определяющее формат аргумента
     */
    public final String regex;
    protected final Pattern pattern;
    /**
     * тип (характеристика) аргумента
     * Не путать с типом значения аргумента!
     */
    public final ArgumentType type;
    /**
     * наименование
     */
    public final String name;

    /**
     * Конструктор абстрактного аргумента
     * @param name наименование
     * @param placement расположение
     * @param argumentable объект, в который будет вложен аргумент
     * @param regex регулярное выражение для определения формата значений
     * @param type тип (характеристика) этого аргумента
     */
    public AbstractArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex, ArgumentType type) {
        this.name = ((name==null || name.isBlank()) ? "Argument" : name);
        if (placement==null) throw new IllegalArgumentException("Placement must not be null");
        if (argumentable==null) throw new IllegalArgumentException("Argumentable must not be null");
        this.placement = placement;
        this.regex = ((regex==null || regex.isBlank()) ? type.regex : regex);
        this.pattern = Pattern.compile(this.regex);
        if (type==null) throw new IllegalArgumentException("Argument type must not be null");
        this.type = type;
        this.command = argumentable.referToCommand();
        this.administrator = command.administrator;
        this.modeManager = administrator.modeManager;
        argumentable.acceptArgument(this);
        attached = true;
    }

    /**
     * Формирует значение аргумента из текстового представления
     * @param text текстовое представление
     * @throws InvalidCommandArgumentException в случае, если аргумент не подошёл по формату
     * @throws IllegalCommandArgumentException в случае, если аргумент не подошёл по значению
     * @throws CommandInterruptionException в случае, если команда прерывает свой запуск
     * @throws QuitInterruptionException в случае, если пользователь прервал процедуру
     */
    public final void form(String text) throws InvalidCommandArgumentException, IllegalCommandArgumentException, CommandInterruptionException, QuitInterruptionException {
        String invalidArgumentDescription = "Argument "+name+" must be represented as "+type.description;
        if (type.textual && text!=null && !text.equals("\\quit") && !pattern.matcher(text).matches()) {
            throw new InvalidCommandArgumentException(invalidArgumentDescription);
        }
        try {
            value = parseValue(text);
        } catch (CommandInterruptionException | QuitInterruptionException e) {
            throw e;
        } catch (Exception e) {
            String exceptionText = invalidArgumentDescription;
            if (e instanceof InvalidCommandArgumentException) exceptionText += "\n"+e.getMessage();
            throw new InvalidCommandArgumentException(exceptionText,e);
        }
        if (!checkValue(value)) throw new IllegalCommandArgumentException(name, text);
    }

    protected abstract T parseValue(String textForm) throws CommandLaunchException, QuitInterruptionException;
    protected boolean checkValue(T value) throws IllegalCommandArgumentException {
        return true;
    }

    /**
     * Получение готового значения аргумента
     * @return готовое значение аргумента
     */
    public T getValue() {
        T tempValue = value;
        restore();
        return tempValue;
    }

    protected void restore() {
        value = null;
    }

    /**
     * Геттер для команды
     * @return команда
     */
    public ClientAbstractCommand getCommand() {
        return command;
    }

    /**
     * Определяет, привязан ли аргумент уже к какому-либо аргументируемому объекту.
     * @return
     */
    public boolean isAttached() {
        return attached;
    }

    @Override
    public String toString() {
        return name+":"+type.name;
    }

    /**
     * Формирует строку с приглашением к вводу
     * @return приглашение к вводу
     */
    public String getInvitation() {
        return name+": ";
    }

    /**
     * Формирует значения всех аргументов из списка.
     * @param arguments список аргументов
     * @throws CommandInterruptionException в случае, если команда прерывает свой запуск
     * @throws QuitInterruptionException в случае, если пользователь прервал процедуру, например, заполнение полей формы.
     */
    public static void form(ArrayList<AbstractArgument<?>> arguments) throws CommandInterruptionException, QuitInterruptionException {
        if (arguments==null) throw new IllegalArgumentException("List of argument must not be null");
        for (AbstractArgument<?> argument : arguments) {
            while (argument!=null) {
                if (argument.type.complex) {
                    Printer.println("―― "+argument.name+" ――");
                } else {
                    String invitation = argument.getInvitation();
                    Printer.print(invitation);
                    if (invitation.endsWith("\n")) Printer.print("> ");
                }
                try {
                    String line = "";
                    if (!argument.type.complex) {
                        if (argument.modeManager.hasNext()) {
                            try {
                                line = argument.modeManager.next();
                            } catch (NoSuchElementException e) {
                                throw new CommandInterruptionException(argument.modeManager.getEndingMessage());
                            }
                            if (line!=null&&line.equals("\\quit")) throw new QuitInterruptionException("Interactive quit");
                        } else throw new CommandInterruptionException(argument.modeManager.getEndingMessage());
                    }
                    argument.form(line);
                    break;
                } catch (InvalidCommandArgumentException | IllegalCommandArgumentException e) {
                    if (!argument.modeManager.canRespond()) {
                        throw new CommandInterruptionException(e.getMessage());
                    }
                    Printer.println(e.getMessage());
                    argument.restore();
                }
            }
        }
    }
}
