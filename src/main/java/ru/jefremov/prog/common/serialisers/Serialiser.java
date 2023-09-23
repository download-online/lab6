package ru.jefremov.prog.common.serialisers;

import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.server.exceptions.SerialisationException;

import java.io.*;

public class Serialiser<T> {

    public byte[] serialize(T object) throws SerialisationException {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream os = new ObjectOutputStream(bos);) {
            os.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new SerialisationException("Serialisation failed", e);
        }

    }

    public T deserialise(byte[] bytes) throws SerialisationException, ClassCastException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream is = new ObjectInputStream(bis)) {
            T deserialised = (T) is.readObject();
            is.close();
            return deserialised;
        } catch (IOException | ClassNotFoundException e) {
            throw new SerialisationException("Serialisation failed", e);
        }
    }
}
