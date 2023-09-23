package ru.jefremov.prog.client.commands.arguments.concrete;


import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.primitive.StringArgument;

/**
 * Аргумент, отвечающий за путь к исполняемому скрипту.
 */
public class ScriptArgument extends StringArgument {
    public ScriptArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex);
    }
}
