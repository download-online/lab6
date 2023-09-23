package ru.jefremov.prog.server.interaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.jefremov.prog.common.models.Ticket;

import java.lang.reflect.Type;
import java.util.LinkedHashSet;

/**
 * Класс, отвечающий за перевод коллекции в JSON и обратно.
 */
public class JsonParser {
    private final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(Double.class, new DoubleAdapter())
            .setPrettyPrinting()
            .serializeNulls()
            .create();
    private final Type type = new TypeToken<LinkedHashSet<Ticket>>() {}.getType();

    /**
     * Преобразовать JSON в коллекцию
     * @param text JSON
     * @return коллекция
     */
    public LinkedHashSet<Ticket> parseText(String text){
        return gson.fromJson(text, type);
    }

    /**
     * Преобразовать коллекцию в JSON
     * @param collection коллекция
     * @return JSON
     */
    public String encodeCollection(LinkedHashSet<Ticket> collection) {
        return gson.toJson(collection, type);
    }
}
