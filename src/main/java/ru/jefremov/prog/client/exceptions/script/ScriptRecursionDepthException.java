package ru.jefremov.prog.client.exceptions.script;

public class ScriptRecursionDepthException extends ScriptLaunchException{
    public ScriptRecursionDepthException(String message) {
        super("Too many scripts are launching new scripts.\n"+message);
    }
}
