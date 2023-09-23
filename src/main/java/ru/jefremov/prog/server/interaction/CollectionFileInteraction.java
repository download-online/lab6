package ru.jefremov.prog.server.interaction;



import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.server.exceptions.SavedCollectionInteractionException;

import java.io.*;
import java.util.LinkedHashSet;

/**
 * Конкретная реализация взаимодействия с файлом, предназначенным для хранения коллекцией.
 */
public class CollectionFileInteraction implements SavedCollectionInteracting{
    private final JsonParser jsonParser = new JsonParser();
    private final String path;
    private final File file;

    /**
     * Конструктор менеджера взаимодействия с файлом коллекции
     * @param path путь к файлу
     */
    public CollectionFileInteraction(String path) {
        if (path==null) throw new IllegalArgumentException("Path should not be null");
        this.path = path;
        file = new File(path);
    }

    @Override
    public LinkedHashSet<Ticket> loadCollection() throws SavedCollectionInteractionException {
        if (!file.exists() || !file.isFile()) throw new SavedCollectionInteractionException("Collection file doesn't exists.");
        if (!file.canRead()) throw new SavedCollectionInteractionException("There is no access to reading the collection file");
        StringBuilder data = new StringBuilder();
        try (FileInputStream in = new FileInputStream(path);
                InputStreamReader reader = new InputStreamReader(in)) {
            int c;
            while ((c = reader.read())!= -1) {
                data.append((char) c);
            }
        } catch (FileNotFoundException e) {
            throw new SavedCollectionInteractionException("Collection file doesn't exists.");
        } catch (IOException e) {
            throw new SavedCollectionInteractionException("Couldn't access collection file.");
        }
        LinkedHashSet<Ticket> collection;
        try {
            collection = jsonParser.parseText(data.toString());
        } catch (NumberFormatException e) {
            throw new SavedCollectionInteractionException("Incorrect JSON is stored in the file.\n"+e.getMessage());
        } catch (Exception e) {
            throw new SavedCollectionInteractionException("Incorrect JSON is stored in the file.");
        }
        return collection;
    }

    @Override
    public void saveCollection(LinkedHashSet<Ticket> collection) throws SavedCollectionInteractionException {
        if (!file.isFile()) throw new SavedCollectionInteractionException("Collection file doesn't exists.");
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) throw new SavedCollectionInteractionException("Collection file doesn't exists. Failed to create a new file.");
            } catch (IOException e) {
                throw new SavedCollectionInteractionException("Collection file doesn't exists. Failed to create a new file.");
            }
        }
        if (!file.canWrite()) throw new SavedCollectionInteractionException("There is no access to writing the collection file");
        String json = jsonParser.encodeCollection(collection);
        try(FileOutputStream out=new FileOutputStream(path, false);
            BufferedOutputStream bos = new BufferedOutputStream(out)) {
            byte[] buffer = json.getBytes();
            bos.write(buffer);
        } catch (FileNotFoundException e) {
            throw new SavedCollectionInteractionException("Collection file doesn't exists.");
        } catch (IOException e) {
            throw new SavedCollectionInteractionException("Couldn't access collection file.");
        }
    }
}
