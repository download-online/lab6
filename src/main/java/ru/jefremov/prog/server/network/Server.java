package ru.jefremov.prog.server.network;

import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.network.Request;
import ru.jefremov.prog.common.network.Response;
import ru.jefremov.prog.server.exceptions.*;
import ru.jefremov.prog.server.managers.ServerAdministrator;
import ru.jefremov.prog.server.managers.ServerCommandManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.*;
import java.util.Set;

public class Server {
    public static final int BLOCK_SIZE = 10000;
    private final Selector selector;
    private ServerSocketChannel server;
    private SocketAddress address;
    private boolean running;
    private Request request;
    private Response response;
    public final ServerAdministrator administrator;
    public final ServerCommandManager manager;
    public Server(int port, ServerAdministrator administrator) throws ServerLaunchException {
        Runtime.getRuntime().addShutdownHook(new Thread(administrator::save));
        this.administrator = administrator;
        this.manager = administrator.commandManager;
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (ClosedChannelException e) {
            throw new ServerLaunchException("Channel closed");
        } catch (IOException e) {
            throw new ServerLaunchException("Server launch problem: "+e.getMessage());
        }

        try {
            address = new InetSocketAddress(port);
            server.bind(address);
        } catch (IOException e) {
            throw new ServerLaunchException("Occupied port");
        }
        running = true;
    }

    public void run() {
        try {
            while (running) {
                int selected = selector.selectNow();
                if (selected==0) continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                for (var iter = keys.iterator(); iter.hasNext(); ) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            doAccept(key);
                        }
                        if (key.isValid() && key.isReadable()) {
                            try {
                                doRead(key);
                            } catch (SerialisationException e) {
                                request = null;
                                Printer.println("Failed to read request.");
                                ((SocketChannel)key.channel()).socket().close();
                                key.cancel();
                            } finally {
                                Printer.println("Respond formed");
                                response = ResponseForming.formResponse(request,manager);
                            }
                        }
                        if (key.isValid() && key.isWritable()) {
                            if (response!=null) {
                                doWrite(key);
                            }
                        }
                    } else {
                        key.cancel();
                    }
                }
            }

        } catch (IOException e) {
            Printer.println("Client disconnected");
        } catch (ClientAcceptingException e) {
            Printer.println(e.getMessage());
        } catch (Exception e) {
            Printer.println(e.getMessage());
            stopServer();
        }
        try {
            selector.close();
        } catch (IOException e) {
            Printer.println("Failed to close selector correctly.");
        }
    }

    private void doAccept(SelectionKey key) throws ClientAcceptingException {
        try {
            var ssc = (ServerSocketChannel) key.channel();
            var sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ);
            Printer.println("Client connected");
        } catch (IOException e) {
            throw new ClientAcceptingException("Failed to accept client: "+e.getMessage());
        }
    }
    private void doRead(SelectionKey key) throws IOException, SerialisationException {
        var socketChannel = (SocketChannel) key.channel();
        request = RequestReader.readRequest(socketChannel);
        socketChannel.register(key.selector(), SelectionKey.OP_WRITE);
    }

    private void doWrite(SelectionKey key) throws ResponseSendingException {
        var socketChannel = (SocketChannel) key.channel();
        try {
            ResponseForming.sendResponse(socketChannel, response);
            socketChannel.register(key.selector(), SelectionKey.OP_READ);
        } catch (IOException | SerialisationException e) {
            throw new ResponseSendingException("Failed to send response", e);
        } finally {
            response = null;
        }
    }

    public void stopServer() {
        running = false;
        administrator.save();
        try {
            server.close();
        } catch (IOException e) {
            Printer.println("Failed to close server correctly");
        }
        System.exit(0);
    }
}
