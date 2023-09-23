package ru.jefremov.prog.client.net;

import ru.jefremov.prog.client.exceptions.ClientLaunchException;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.network.Request;
import ru.jefremov.prog.common.network.Response;
import ru.jefremov.prog.common.serialisers.Serialisers;
import ru.jefremov.prog.client.exceptions.RequestSendingException;
import ru.jefremov.prog.server.exceptions.SerialisationException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Client {
    private static final int BLOCK_SIZE = 10000;
    private static InetAddress host;
    private static Socket connection;
    private InputStream is;
    private OutputStream os;
    private InetSocketAddress address;
    private int port;
    private boolean running;

    public Client(int port) throws ClientLaunchException {
        this.port = port;
        running = true;
        try {
            host = InetAddress.getLocalHost();
            connection = new Socket();
            address = new InetSocketAddress("localhost", port);
            connection.connect(address, 10000);
            os = connection.getOutputStream();
            is = connection.getInputStream();
        } catch (UnknownHostException e) {
            throw new ClientLaunchException("Failed to launch client: Unknown host");
        } catch (SocketTimeoutException e) {
            throw new ClientLaunchException("Client launch timeout");
        } catch (IOException e) {
            throw new ClientLaunchException("Failed to launch client");
        }
    }

    public void sendRequest(Request request) throws RequestSendingException  {
        try {
            byte[] requestBytes = Serialisers.requestSerialiser.serialize(request);
            os.write(requestBytes);
        } catch (IOException e) {
            throw new RequestSendingException("Failed to send request. Connection lost.", e);
        } catch (SerialisationException e) {
            throw new RequestSendingException("Failed to send request",e);
        }
    }

    public Response getResponse() throws IOException, SerialisationException {
        var bytes = new ArrayList<Byte>();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(is, BLOCK_SIZE * 2);
        byte[] buffer = new byte[BLOCK_SIZE + 1];
        int l = 0;
        do {
            int read = bufferedInputStream.read(buffer);
            if (read == -1){
                throw new IOException("Connection lost");

            }
            for (int i = 0; i < BLOCK_SIZE; i++){
                bytes.add(buffer[i]);
            }
        } while (buffer[BLOCK_SIZE] != 1);
        byte[] responseBytes = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++){
            responseBytes[i] = bytes.get(i);
        }
        return Serialisers.responseSerialiser.deserialise(responseBytes);
    }

    public void stop() {
        running = false;
        try {
            is.close();
            os.close();
            connection.close();
        } catch (IOException e) {
            Printer.println("Failed to stop client");
        }
    }

    public boolean isRunning() {
        return running;
    }
}
