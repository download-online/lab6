package ru.jefremov.prog.server.managers;

import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.models.validation.CoordinatesValidator;
import ru.jefremov.prog.common.models.validation.EventValidator;
import ru.jefremov.prog.common.models.validation.TicketValidator;
import ru.jefremov.prog.server.interaction.CollectionFileInteraction;

public class ServerAdministrator {
    /**
     * Путь к файлу с коллекцией
     */
    public final String collectionFile;
    /**
     * Валидатор событий
     */
    public final EventValidator eventValidator = new EventValidator();
    /**
     * Валидатор координат
     */
    public final CoordinatesValidator coordinatesValidator = new CoordinatesValidator();
    /**
     * Валидатор билетов
     */
    public final TicketValidator ticketValidator = new TicketValidator(eventValidator,coordinatesValidator);
    /**
     * Хранилище
     */
    public final Storage storage = new Storage(this, ticketValidator);

    /**
     * Менеджер команд
     */
    public final ServerCommandManager commandManager;
    /**
     * Менеджер взаимодействия с файлом, содержащим коллекцию
     */
    public final CollectionFileInteraction collectionFileInteraction;

    /**
     * Конструктор для администратора
     * @param path путь к файлу, содержащему коллекцию
     */
    public ServerAdministrator(String path) {
        commandManager = new ServerCommandManager(storage);
        collectionFileInteraction = new CollectionFileInteraction(path);
        collectionFile = path;
    }

    public void save() {
        try {
            commandManager.launchCommand("save", ServerCommandManager.blankState);
            Printer.println("Collection saved.");
        } catch (Exception e) {
            Printer.println("Saving failed");
        }
    }
}
