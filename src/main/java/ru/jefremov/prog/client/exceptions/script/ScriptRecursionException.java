package ru.jefremov.prog.client.exceptions.script;

public class ScriptRecursionException extends ScriptLaunchException{
    public ScriptRecursionException(String message) {
        super("The script runs itself.\n"+message);
    }
}
