package ru.jefremov.prog.client.managers;

import ru.jefremov.prog.client.interaction.InteractiveSubmitter;
import ru.jefremov.prog.client.interaction.handlers.*;
import ru.jefremov.prog.client.modes.InteractiveMode;
import ru.jefremov.prog.client.modes.ScriptMode;
import ru.jefremov.prog.client.net.Client;
import ru.jefremov.prog.common.models.validation.CoordinatesValidator;
import ru.jefremov.prog.common.models.validation.EventValidator;
import ru.jefremov.prog.common.models.validation.TicketValidator;
import ru.jefremov.prog.server.managers.ServerAdministrator;

/**
 * Класс, основной задачей которого служит сборка остальных компонентов.
 */
public class ClientAdministrator {
    public final Client client;
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
     * Менеджер скриптов
     */
    public final ScriptManager scriptManager = new ScriptManager(5);
    /**
     * Интерактивный поставщик строк
     */
    public final InteractiveSubmitter interactiveSubmitter;
    /**
     * Интерактивный режим работы
     */
    public final InteractiveMode interactiveMode;
    /**
     * Скриптовой режим работы
     */
    public final ScriptMode scriptMode;
    /**
     * Менеджер режимов работы
     */
    public final ModeManager modeManager;
    /**
     * Цепь из обработчика пустых строк и обработчика кавычек
     */
    public final Handler<String> quotationHandler = new EmptinessHandler(new QuotationHandler());
    /**
     * Менеджер команд
     */
    public final ClientCommandManager commandManager;

    public final ServerAdministrator serverAdministrator = new ServerAdministrator("D:\\LIBS\\DOCS\\collection.txt");

    /**
     * Конструктор для администратора
     * @param interactiveSubmitter интерактивный поставщик строк
     */
    public ClientAdministrator(InteractiveSubmitter interactiveSubmitter, Client client) {
        this.interactiveSubmitter = interactiveSubmitter;
        interactiveMode = new InteractiveMode(null, interactiveSubmitter);
        scriptMode  = new ScriptMode(interactiveMode, scriptManager);
        modeManager = new ModeManager(interactiveMode,scriptMode);
        commandManager = new ClientCommandManager(this);
        this.client = client;
    }
}
