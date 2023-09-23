package ru.jefremov.prog.server.managers;


import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.HistoryRecord;
import ru.jefremov.prog.common.commands.results.CommandResult;
import ru.jefremov.prog.common.commands.states.CommandState;
import ru.jefremov.prog.common.exceptions.ExitInterruptionException;
import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;
import ru.jefremov.prog.common.managers.CommandManager;
import ru.jefremov.prog.server.commands.ServerAbstractCommand;
import ru.jefremov.prog.server.commands.concrete.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс, ответственный за запуск команд.
 */
public class ServerCommandManager extends CommandManager<ServerAbstractCommand<?,?>> {
    private final Storage storage;
    public final ServerAdministrator administrator;
    private final ArrayList<HistoryRecord> history = new ArrayList<>();
    public static final CommandState blankState = new CommandState();
    /**
     * Конструктор для менеджера комманд
     * @param storage хранилище с коллекцией
     */
    public ServerCommandManager(Storage storage) {
        if (storage==null) throw new IllegalArgumentException("Storage must not be null");
        this.storage = storage;
        this.administrator = storage.getAdministrator();

        String addDescription = "Добавить новый элемент в коллекцию";
        String updateDescription = "Обновить значение элемента коллекции, id которого равен заданному";
        String showDescription = "Вывести все элементы коллекции";
        String executeScriptDescription = "Исполнить скрипт из указанного файла.";
        String removeByIdDescription = "Удалить элемент из коллекции по его id";
        String historyDescription = "Вывести последние 5 команд";
        String clearDescription = "Очистить коллекцию";
        String exitDescription = "Завершить программу (без сохранения в файл)";
        String helpDescription = "Вывести справку по доступным командам";
        String infoDescription = "Вывести информацию о коллекции";
        String addIfMinDescription = "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
        String removeLowerDescription = "Удалить из коллекции все элементы, меньшие, чем заданный";
        String filterStartsWithCommentDescription = "Вывести элементы, комментарии которых начинаются с заданной подстроки";
        String filterLessThanEventDescription = "Вывести элементы, значение поля event которых меньше заданного";
        String printDescendingDescription = "Вывести элементы коллекции в порядке убывания";
        String saveDescription = "Сохранить коллекцию в файл";

        new sAddCommand("add",addDescription,this);
        new sUpdateCommand("update",updateDescription,this);
        new sShowCommand("show",showDescription,this);
        new sExecuteScriptCommand("execute_script", executeScriptDescription,this);
        new sRemoveByIdCommand("remove_by_id", removeByIdDescription,this);
        new sHistoryCommand("history", historyDescription,this);
        new sClearCommand("clear",clearDescription,this);
        new sExitCommand("exit",exitDescription,this);
        new sHelpCommand("help",helpDescription,this);
        new sInfoCommand("info",infoDescription,this);
        new sAddIfMinCommand("add_if_min", addIfMinDescription,this);
        new sRemoveLowerCommand("remove_lower", removeLowerDescription,this);
        new sFilterStartsWithCommentCommand("filter_starts_with_comment", filterStartsWithCommentDescription,this);
        new sFilterLessThanEventCommand("filter_less_than_event", filterLessThanEventDescription,this);
        new sPrintDescendingCommand("print_descending",printDescendingDescription,this);
        new sSaveCommand("save",saveDescription, this);
        }

    /**
     * Геттер для хранилища
     * @return хранилище
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Запуск команды
     * @param word ключевое слово команды
     * @param line строка с аргументами
     * @throws CommandLaunchException вызывается в случае проблем с запуском команды
     * @throws ExitInterruptionException вызывается в случае завершения программы без сохранения
     */
    public void launchCommand(String word, String line) throws CommandLaunchException, ExitInterruptionException{
        ServerAbstractCommand<?,?> command = getCommand(word);
        if (command==null) {
            throw new CommandLaunchException("Command not found: "+word);
        }
        history.add(new HistoryRecord(word));
        if (history.size()%5==0) launchCommand("save",blankState);
    }

    public CommandResult launchCommand(String word, CommandState state) throws CommandLaunchException {
        ServerAbstractCommand<?,?> command = getCommand(word);
        if (command==null) {
            throw new CommandLaunchException("Command not found: "+word);
        }
        CommandResult result = command.launch(state);
        history.add(new HistoryRecord(word));
        if (history.size()%5==0) launchCommand("save",blankState);
        return result;
    }


    public HistoryRecord[] getHistory() {
        if (history.size()==0) {
            Printer.println("The history does not contain any successfully executed commands");
            return null;
        } else {
            var lastRecords = history.subList(Math.max(history.size() - 5, 0), history.size());
            lastRecords.forEach(Printer::println);
            return lastRecords.toArray(HistoryRecord[]::new);
        }
    }


    public String[] getHelp() {
        return getCommandWords().toArray(String[]::new);
    }
}
