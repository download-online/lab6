package ru.jefremov.prog.client.commands.arguments;


import ru.jefremov.prog.client.commands.Argumentable;

/**
 * Аргумент произвольного типа, отвечающий за не-составные поля.
 * @param <T> тип значения аргумента.
 */
public abstract class PrimitiveArgument<T> extends AbstractArgument<T> {
    public PrimitiveArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex, ArgumentType type) {
        super(name, placement, argumentable, regex, type);
    }
}
