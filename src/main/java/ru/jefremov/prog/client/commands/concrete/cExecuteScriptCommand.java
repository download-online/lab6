package ru.jefremov.prog.client.commands.concrete;


import ru.jefremov.prog.client.commands.ClientAbstractCommand;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.concrete.ScriptArgument;
import ru.jefremov.prog.client.commands.arguments.primitive.StringArgument;
import ru.jefremov.prog.client.exceptions.script.ScriptLaunchException;
import ru.jefremov.prog.client.interaction.Script;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.CommandResult;
import ru.jefremov.prog.common.commands.states.CommandState;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Команда, отвечающая за запуск скриптов из файлов.
 */
public class cExecuteScriptCommand extends ClientAbstractCommand<CommandState, CommandResult> {
    private final StringArgument arg1 = new ScriptArgument("file_name", ArgumentPlacement.INLINE,this,null);
    public cExecuteScriptCommand(String word, ClientCommandManager manager) {
        super(word, manager, false,false);
    }

    public cExecuteScriptCommand(String word, ClientCommandManager manager, String description) {
        super(word, manager, description, false,false);
    }

    @Override
    public CommandState formState() {
        String path = arg1.getValue();
        try {
            File file = new File(path);
            if (!file.exists() || !file.isFile()) {
                Printer.println("Specified script file doesn't exist.");
            }
            else if (!file.canRead()) {
                Printer.println("There is no access to reading specified script file.");
            }
            else {
                Script script = new Script(file);
                administrator.modeManager.startScript(script);
            }
        } catch (FileNotFoundException e) {
            Printer.println("File not found!");
        } catch (ScriptLaunchException e) {
            Printer.println(e.getMessage());
        }
        return new CommandState();
    }
}
