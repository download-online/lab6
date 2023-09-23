package ru.jefremov.prog.client.commands.arguments.complex;

import ru.jefremov.prog.client.commands.Argumentable;
import ru.jefremov.prog.client.commands.arguments.ArgumentPlacement;
import ru.jefremov.prog.client.commands.arguments.ComplexArgument;
import ru.jefremov.prog.client.commands.arguments.concrete.*;
import ru.jefremov.prog.client.commands.arguments.primitive.*;
import ru.jefremov.prog.client.exceptions.command.IllegalCommandArgumentException;
import ru.jefremov.prog.common.models.Coordinates;

/**
 * Комплексный аргумент для объекта координат, собирающий его из аргументов, соответствующих его полям.
 */
public class CoordinatesArgument extends ComplexArgument<Coordinates> {
    private final IntegerArgument arg1 = new CoordinatesXArgument("X", ArgumentPlacement.NEWLINE,this,null);
    private final DoubleArgument arg2 = new CoordinatesYArgument("Y",ArgumentPlacement.NEWLINE,this,null);

    public CoordinatesArgument(String name, Argumentable argumentable) {
        super(name, argumentable);
    }

    @Override
    protected boolean checkValue(Coordinates value) throws IllegalCommandArgumentException {
        String comment = administrator.coordinatesValidator.reviewCoordinates(value);
        if (comment!=null) throw new IllegalCommandArgumentException(name, comment);
        return true;
    }

    @Override
    public Coordinates formComplexValue() {
        return new Coordinates(arg1.getValue(),arg2.getValue());
    }
}
