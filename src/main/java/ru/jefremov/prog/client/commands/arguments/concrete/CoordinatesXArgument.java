package ru.jefremov.prog.client.commands.arguments.concrete;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.primitive.IntegerArgument;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;

/**
 * Аргумент, отвечающий за координату Y.
 */
public class CoordinatesXArgument extends IntegerArgument {
    public CoordinatesXArgument(String name, ArgumentPlacement placement, Argumentable argumentable, String regex) {
        super(name, placement, argumentable, regex);
    }

    @Override
    protected boolean checkValue(Integer value) throws IllegalCommandArgumentException {
        String comment = administrator.coordinatesValidator.reviewX(value);
        if (comment!=null) throw new IllegalCommandArgumentException(name, comment);
        return true;
    }
}
