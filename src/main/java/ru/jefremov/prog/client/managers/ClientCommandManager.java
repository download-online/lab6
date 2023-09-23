package ru.jefremov.prog.client.managers;


import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.concrete.*;
import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.client.exceptions.RequestSendingException;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.states.CommandState;
import ru.jefremov.prog.common.exceptions.ExitInterruptionException;
import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;
import ru.jefremov.prog.common.managers.CommandManager;
import ru.jefremov.prog.common.network.Request;
import ru.jefremov.prog.common.network.Response;
import ru.jefremov.prog.common.network.Status;
import ru.jefremov.prog.server.exceptions.SerialisationException;

import java.io.IOException;

/**
 * Класс, ответственный за запуск команд.
 */
public class ClientCommandManager extends CommandManager<ClientAbstractCommand<?,?>> {

    public final ClientAdministrator administrator;

    /**
     * Конструктор для менеджера комманд
     */
    public ClientCommandManager(ClientAdministrator administrator) {
        super();
        this.administrator = administrator;
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

        new cAddCommand("add",this, addDescription);
        new cUpdateCommand("update",this, updateDescription);
        new cShowCommand("show",this,showDescription);
        new cExecuteScriptCommand("execute_script", this,executeScriptDescription);
        new cRemoveByIdCommand("remove_by_id", this,removeByIdDescription);
        new cHistoryCommand("history", this,historyDescription);
        new cClearCommand("clear",this,clearDescription);
        new cExitCommand("exit",this,exitDescription);
        new cHelpCommand("help",this,helpDescription);
        new cInfoCommand("info",this,infoDescription);
        new cAddIfMinCommand("add_if_min", this, addIfMinDescription);
        new cRemoveLowerCommand("remove_lower", this, removeLowerDescription);
        new cFilterStartsWithCommentCommand("filter_starts_with_comment", this, filterStartsWithCommentDescription);
        new cFilterLessThanEventCommand("filter_less_than_event", this, filterLessThanEventDescription);
        new cPrintDescendingCommand("print_descending",this,printDescendingDescription);
    }

    /**
     * Запуск команды
     * @param word ключевое слово команды
     * @param line строка с аргументами
     * @throws CommandLaunchException вызывается в случае проблем с запуском команды
     * @throws ExitInterruptionException вызывается в случае завершения программы без сохранения
     * @throws QuitInterruptionException вызывается в случае прерывания пользователем текущей процедуры
     */
    public void launchCommand(String word, String line) throws CommandLaunchException, ExitInterruptionException, QuitInterruptionException {
        ClientAbstractCommand<?,?> command = getCommand(word);
        if (command==null) {
            throw new CommandLaunchException("Command not found: "+word);
        }
        CommandState state = command.launch(line);
        Request request = new Request(word, state, command.requiresLargeArrays);
        try {
            administrator.client.sendRequest(request);
//            if (command.requiresLargeArrays) {
//                //Long bytesLen = administrator.client.getDataLen();
//            }
            Response r = administrator.client.getResponse();
            if (r==null || r.status == Status.ERROR || r.result==null) throw new CommandLaunchException("Broken request"+(r!=null?": "+r.text:""));
            else {
                if (command.reflexing) {
                    command.interpretResult(r.result);
                }
            }
        } catch (RequestSendingException e) {
            if (e.getCause() instanceof IOException) {
                administrator.client.stop();
                throw new CommandLaunchException(e.getMessage());
            } else {
                throw new CommandLaunchException("Broken request");
            }
        } catch (SerialisationException e) {
            throw new CommandLaunchException("Broken request");
        } catch (IOException e) {
            throw new CommandLaunchException("Connection closed");
        }
    }
}
