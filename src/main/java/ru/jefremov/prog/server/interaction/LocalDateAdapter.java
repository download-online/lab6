package ru.jefremov.prog.server.interaction;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс представляет собой адаптер типа даты для хранения в виде JSON
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    
    @Override
    public JsonElement serialize(LocalDate localDate, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsJsonPrimitive().getAsString(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
