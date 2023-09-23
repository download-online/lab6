package ru.jefremov.prog.server.network;

import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.network.Request;
import ru.jefremov.prog.common.serialisers.Serialisers;
import ru.jefremov.prog.server.exceptions.SerialisationException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestReader {
    protected static Request readRequest(SocketChannel socketChannel) throws SerialisationException, IOException {
        ByteBuffer buffer = ByteBuffer.allocate(Server.BLOCK_SIZE);
        socketChannel.read(buffer);
        return Serialisers.requestSerialiser.deserialise(buffer.array());
    }
}
