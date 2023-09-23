package ru.jefremov.prog.server.interaction;

import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.server.exceptions.SavedCollectionInteractionException;

import java.util.LinkedHashSet;

/**
 * Интерфейс взаимодействия с местом хранения коллекции для её загрузки или сохранения.
 */
public interface SavedCollectionInteracting {
    /**
     * Загрузить коллекцию из места её хранения
     * @return загруженная коллекция
     * @throws SavedCollectionInteractionException в случае, если с загрузкой возникают проблемы
     */
    LinkedHashSet<Ticket> loadCollection() throws SavedCollectionInteractionException;

    /**
     * Сохранить коллекцию в место её хранения
     * @param collection коллекия
     * @throws SavedCollectionInteractionException в случае, если с сохранением возникают проблемы
     */
    void saveCollection(LinkedHashSet<Ticket> collection) throws SavedCollectionInteractionException;
}
