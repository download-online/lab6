package ru.jefremov.prog.server.network;

import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.commands.results.CommandResult;
import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;
import ru.jefremov.prog.common.network.Request;
import ru.jefremov.prog.common.network.Response;
import ru.jefremov.prog.common.network.Status;
import ru.jefremov.prog.common.serialisers.Serialisers;
import ru.jefremov.prog.server.exceptions.SerialisationException;
import ru.jefremov.prog.server.managers.ServerCommandManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ResponseForming {
    public static Response formResponse(Request request, ServerCommandManager manager) {
        Response response;
        if (request==null || request.word==null || request.state==null) {
            response = new Response("Blank request", Status.ERROR,null);
        }
        else {
            try {
                CommandResult result = manager.launchCommand(request.word, request.state);
                response = new Response("SUCCESS", Status.OK,result);
            } catch (CommandLaunchException | ClassCastException e) {
                response = new Response("Invalid request", Status.ERROR,null);
            }
        }
        return response;
    }

    public static void sendResponse(SocketChannel socketChannel, Response response) throws SerialisationException, IOException {
        byte[] bytes = Serialisers.responseSerialiser.serialize(response);
        int blocks_count = bytes.length/Server.BLOCK_SIZE +(bytes.length % Server.BLOCK_SIZE == 0?0:1);
        for (int i = 0; i < blocks_count; i++){
            byte[] buffer = Arrays.copyOfRange(bytes, i * Server.BLOCK_SIZE, (i + 1) * Server.BLOCK_SIZE + 1);
            buffer[buffer.length-1] = (byte)((i==blocks_count-1)?1:0);
            socketChannel.write(ByteBuffer.wrap(buffer));
            if (blocks_count>10) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
