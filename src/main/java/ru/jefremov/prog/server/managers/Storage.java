package ru.jefremov.prog.server.managers;


import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.models.Event;
import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.common.models.validation.AbstractTicketValidator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для хранилища, обеспечивающего ограниченный доступ к коллекции.
 * Обеспечивает корректность хранящихся данных и предоставляет удобные средства для управления коллекцией.
 */
public class Storage {
    private LinkedHashSet<Ticket> collection;
    private final AbstractTicketValidator ticketValidator;
    private final ServerAdministrator administrator;
    private LocalDate initDate;

    /**
     * Конструктор для хранилища
     * @param administrator администратор
     * @param ticketValidator валидатор билетов
     */
    public Storage(ServerAdministrator administrator, AbstractTicketValidator ticketValidator) {
        if (administrator==null) throw new IllegalArgumentException("Administrator must not be null");
        this.administrator = administrator;
        if (ticketValidator==null) throw new IllegalArgumentException("Ticket validator must not be null");
        this.ticketValidator = ticketValidator;
        this.collection = new LinkedHashSet<>();
        initDate = LocalDate.now();
    }

    /**
     * Геттер для копии хранящейся коллекции.
     * @return копия коллекции
     */
    public LinkedHashSet<Ticket> getCollectionCopy() {
        return new LinkedHashSet<>(collection);
    }

    /**
     * Геттер для администратора
     * @return администратор
     */
    public ServerAdministrator getAdministrator() {
        return administrator;
    }

    /**
     * Загрузить коллекцию в хранилище. Отвергает неподходящие коллекции.
     * @param collection коллекция
     * @return подошла ли она для загрузки
     */
    public boolean loadCollection(LinkedHashSet<Ticket> collection) {
        if (collection==null) {
            this.collection = new LinkedHashSet<>();
            initDate = LocalDate.now();
            return true;
        }
        if (checkCollection(collection)) {
            this.collection = new LinkedHashSet<>(collection);
            initDate = LocalDate.now();
            return true;
        }
        return false;
    }

    /**
     * Проверяет коллекцию на соответствие требованиям валидатора элементов.
     * @param collection коллекция
     * @return прошла ли коллекция проверку, или нет
     */
    public boolean checkCollection(LinkedHashSet<Ticket> collection) {
        if (collection==null) return false;
        return collection.stream().allMatch(ticketValidator::checkTicket);
    }

    /**
     * Выявляет проблемы в коллекции при её проверке
     * @param collection коллекция
     * @return Комментарий, если есть проблемы, или null, если коллекция успешно прошла проверку.
     */
    public String reviewCollection(LinkedHashSet<Ticket> collection) {
        if (collection==null) {
            return null;
        }
        Optional<String> verdict = collection.stream().map(ticketValidator::reviewTicket).filter(comment->comment!=null).findAny();
        return verdict.orElse(null);
    }

    /**
     * Добавление билета. Отвергает неподходящие элементы.
     * @param ticket билет
     * @return Подошёл ли он для добавления, или нет.
     */
    public boolean addTicket(Ticket ticket) {
        Optional<Ticket> checked = Stream.of(ticket).filter(ticket1->ticketValidator.checkTicket(ticket1))
                .findFirst();
        return (checked.isPresent() && collection.add(checked.get()));
    }

    /**
     * Получение билета по id
     * @param id id
     * @return билет с соответствующим идентификатором
     */
    private Ticket getById(int id) {
        Optional<Ticket> ticket = collection.stream().filter(ticket1 -> ticket1.getId()==id).findFirst();
        return ticket.orElse(null);
    }

    /**
     * Проверка, присутствует ли в коллекции билет с нужным id
     * @param id id
     * @return результат проверки
     */
    public boolean hasId(int id) {
        return (getById(id)!=null);
    }

    /**
     * Обновляет билет с нужным id, копируя поля у другого билета. Другой билет может быть отвергнут.
     * @param id id
     * @param other другой билет
     * @return Состоялось ли обновление (подошёл ли другой билет, или нет)
     */
    public boolean updateById(int id, Ticket other) {
        Ticket ticket = getById(id);
        //if (Stream.of(id).map(id2->getById(id2)).allMatch(ticket1 -> ticket1!=null)) return false;
        if (ticket==null) return false;
        if (Stream.of(other).anyMatch(other2 -> ticketValidator.checkTicket(other2))) {
            collection.remove(ticket);
            ticket.update(other);
            return collection.add(ticket);
        }
        return false;
    }

    /**
     * Удаляет билет по id
     * @param id id
     * @return изменилась ли коллекция
     */
    public boolean removeById(int id) {
        Optional<Ticket> matching = Stream.of(id).map(id1 -> getById(id1)).filter(ticket -> ticket!=null).findFirst();
        return (matching.isPresent()&&collection.remove(matching.get()));
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Получение размера коллекции
     * @return размер коллекции
     */
    public int size() {
        return collection.size();
    }

    /**
     * Выводит информацию о коллекции.
     */
    public String printInfo() {
        return (collection.getClass().getName()+" collection with "+size()+" elements.\n"+
                "Initialisation date: "+ initDate+"\nThe collection is linked to a file: "+ administrator.collectionFile);
    }

    /**
     * Добавляет билет, если он меньше всех элементов в коллекции и проходит проверку.
     * @param ticket билет
     * @return добавлен ли билет (прошёл ли он проверку)
     */
    public boolean addIfMin(Ticket ticket) {
        Optional<Ticket> checked = Stream.of(ticket).filter(ticket1 -> collection.isEmpty() || Collections.min(collection).compareTo(ticket1)>0).findFirst();
        return (checked.isPresent()&&addTicket(checked.get()));
    }

    /**
     * Удаляет все элементы в коллекции, меньшие, чем заданный
     * @param ticket заданный билет
     * @return количество удалённых элементов
     */
    public int removeLower(Ticket ticket) {
        int sizeBefore = collection.size();
        Collection<Ticket> collected = collection.stream().filter(ticket1 -> ticket1.compareTo(ticket)<0).toList();
        collected.stream().forEach(collection::remove); //collection.removeAll(collected);
        int sizeAfter = collection.size();
        return sizeAfter-sizeBefore;
    }

    /**
     * Выводит элементы коллекции, чей комментарий начинается с заданной подстроки
     * @param comment заданная подстрока
     */
    public List<Ticket> printFilterStartsWithComment(String comment) {
        if (!ticketValidator.checkComment(comment)) return null;
        return collection.stream().filter(ticket->ticket.getComment()!=null && ticket.getComment().startsWith(comment)).collect(Collectors.toList());
    }

    /**
     * Выводит все элементы коллекции, чьё событие меньше, чем заданное
     * @param event заданное событие
     */
    public List<Ticket> printFilterLessThanEvent(Event event) {
        if (!ticketValidator.checkEvent(event)) return null;
        return collection.stream().filter(ticket->ticket.getEvent().compareTo(event)<0).collect(Collectors.toList());
    }

    /**
     * Выводит коллекцию в порядке убывания
     * @return есть ли в коллекции элементы.
     */
    public List<Ticket> printDescending() {
        if (collection.isEmpty()) return null;
                //.sorted((ticket1,ticket2)->ticket2.compareTo(ticket1));
        return collection.stream().collect(Collectors.toList());

        //Если нет требований к лямбда-выражениям, то заменить на Comparator.reverseOrder

    }
}
