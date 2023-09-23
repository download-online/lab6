package ru.jefremov.prog.server.interaction;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Класс представляет собой адаптер дробных чисел для хранения в виде JSON.
 */
public class DoubleAdapter implements JsonDeserializer<Double> {
    
    @Override
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String textForm = json.getAsJsonPrimitive().getAsString();
        int digitCount = textForm.length();
        if (textForm.contains(".")) digitCount -= 1;
        if (textForm.contains("-")) digitCount -= 1;
        if (digitCount>15) throw new NumberFormatException("Some double value contains too many digits.");
        return Double.parseDouble(textForm);
    }
}
