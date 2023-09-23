package ru.jefremov.prog.server;

import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.models.Ticket;
import ru.jefremov.prog.server.exceptions.SavedCollectionInteractionException;
import ru.jefremov.prog.server.exceptions.ServerLaunchException;
import ru.jefremov.prog.server.managers.ServerAdministrator;
import ru.jefremov.prog.server.network.Server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.LinkedHashSet;

public class MainServer {
    public static int port = 6086;
    public static Server server;

    public static void main(String[] args) {
        Printer.println("Server "+port);
        String variableName = "LAB5";
        String path = System.getenv(variableName);
        String exitMessage = "Program execution finished";
        if (path == null) {
            Printer.println("Could not get the path to the file from the environment variable " + variableName);
            Printer.println(exitMessage);
            return;
        }

        ServerAdministrator administrator = new ServerAdministrator(path);

        try {
            LinkedHashSet<Ticket> tickets = administrator.collectionFileInteraction.loadCollection();
            boolean loaded = administrator.storage.loadCollection(tickets);
            if (!loaded) {
                Printer.println("The file contains an incorrect collection:");
                Printer.println(administrator.storage.reviewCollection(tickets));
                Printer.println(exitMessage);
                return;
            }
            Printer.println("The collection was successfully loaded.");
        } catch (SavedCollectionInteractionException e) {
            Printer.println(e.getMessage());
            Printer.println("Collection loading failed");
            Printer.println(exitMessage);
            return;
        }



        try {
            Printer.println("Server launched");
            server = new Server(port,administrator);
            server.run();
            administrator.save();
        } catch (ServerLaunchException e) {
            Printer.println("Failed to run server");
        } catch (Exception e) {
            Printer.println("Unexpected error");
        }
        administrator.save();
    }
}