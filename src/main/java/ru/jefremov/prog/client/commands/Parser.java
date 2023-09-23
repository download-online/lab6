package ru.jefremov.prog.client.commands;


import ru.jefremov.prog.client.commands.arguments.AbstractArgument;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.ArgumentType;
import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.client.exceptions.command.InvalidCommandArgumentException;
import ru.jefremov.prog.client.exceptions.command.WrongCommandFormatException;
import ru.jefremov.prog.common.exceptions.command.CommandInterruptionException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, отвечающий за преобразование строки с аргументами команды в набор значений необходимых типов.
 * Реалзиация на основе регулярных выражений.
 */
public class Parser {
    protected final ArrayList<AbstractArgument<?>> newlineArguments = new ArrayList<>();
    protected final ArrayList<AbstractArgument<?>> inlineArguments = new ArrayList<>();
    protected final ClientAbstractCommand<?,?> command;
    private boolean initialised;
    private int currentArgumentIndex = 0;
    private Pattern pattern;
    private String regex;

    /**
     * Конструктор парсера
     * @param command команда, к которой он привязан
     */
    public Parser(ClientAbstractCommand<?,?> command) {
        if (command==null) throw new IllegalArgumentException("Command must not be null");
        this.command = command;
        regex = "^"+command.getWord();
    }

    /**
     * Метод для привязки аргументов к команде и регистрации их в парсере.
     * @param argument аргумент
     * @return подошёл ли аргумент для добавления
     */
    protected boolean acceptArgument(AbstractArgument<?> argument) {
        if (initialised) throw new IllegalStateException("Trying to add argument to initialised parser");
        if (argument==null) throw new IllegalArgumentException("Argument must not be null");
        if (argument.placement == ArgumentPlacement.INLINE) {
            if (argument.type == ArgumentType.OBJECT) throw new IllegalArgumentException("Inline object argument");
            String groupName = "arg"+(currentArgumentIndex++);
            regex += " (?<" + groupName + ">" + argument.regex + ")";
            return inlineArguments.add(argument);
        }
        return newlineArguments.add(argument);
    }

    /**
     * Разбиение строки с аргументами на набор значений с необходимыми для запуска команды типами.
     * @param line строка с аргументами
     * @throws InvalidCommandArgumentException в случае, если аргумент не подходит по формату
     * @throws IllegalCommandArgumentException в случае, если аргумент не подходит по значению
     * @throws WrongCommandFormatException в случае, если нарушен общий формат команды с аргументами
     * @throws CommandInterruptionException в случае, если команда прерывает свой запуск
     * @throws QuitInterruptionException в случае, если пользователь прервал процедуру
     */
    protected void parse(String line) throws InvalidCommandArgumentException, IllegalCommandArgumentException, WrongCommandFormatException, CommandInterruptionException, QuitInterruptionException {
        if (!initialised) {
            regex+="$";
            pattern = Pattern.compile(regex);
            initialised = true;
        }
        runParsingProcess(line);
    }

    protected void runParsingProcess(String line) throws InvalidCommandArgumentException, IllegalCommandArgumentException, CommandInterruptionException, WrongCommandFormatException, QuitInterruptionException {
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            int argumentIndex = 0;
            for (AbstractArgument<?> argument : inlineArguments) {
                String groupName = "arg"+(argumentIndex++);
                String parsedValue = matcher.group(groupName);
                argument.form(parsedValue);
            }
        } else {
            throw new WrongCommandFormatException("Wrong command format. Try:\n"+getCommandFormat());
        }
        AbstractArgument.form(newlineArguments);
    }

    /**
     * Формирует строку, описывающую нужный для команды формат вместе с её аргументами
     * @return строка с форматом
     */
    public String getCommandFormat() {
        StringBuilder commandFormat = new StringBuilder(command.word);
        for (AbstractArgument<?> argument:
             inlineArguments) {
            commandFormat.append(" ").append(argument.toString());
        }
        return commandFormat.toString();
    }
}
