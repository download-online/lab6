package ru.jefremov.prog.client;

import ru.jefremov.prog.client.exceptions.ClientLaunchException;
import ru.jefremov.prog.client.exceptions.QuitInterruptionException;
import ru.jefremov.prog.client.interaction.InteractiveSubmitter;
import ru.jefremov.prog.client.managers.ClientAdministrator;
import ru.jefremov.prog.client.managers.ClientCommandManager;
import ru.jefremov.prog.client.managers.ModeManager;
import ru.jefremov.prog.client.net.Client;
import ru.jefremov.prog.common.Printer;
import ru.jefremov.prog.common.exceptions.ExitInterruptionException;
import ru.jefremov.prog.common.exceptions.command.CommandInterruptionException;
import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class MainClient {
    public static Client client;
    public static int port = 6086;
    public static void main(String[] args) throws IOException, UnknownHostException{
        String exitMessage = "Program execution finished";

        Client client;
        try {
            client = new Client(port);
        } catch (ClientLaunchException e) {
            Printer.println(e.getMessage());
            Printer.println(exitMessage);
            return;
        }
        ClientAdministrator administrator = new ClientAdministrator(new InteractiveSubmitter(), client);
        administrator.ticketValidator.reviewPrice(null);
        ModeManager modeManager = administrator.modeManager;
        ClientCommandManager commandManager = administrator.commandManager;



        boolean running = true;

        Printer.println("");
        Printer.print("> ");
        while (running && client.isRunning()) {
            try {
                if (!modeManager.hasNext()) {
                    running = false;
                    Printer.println("The input has ended.");
                    break;
                }

            } catch (QuitInterruptionException ignored) {

            }
            String line = modeManager.next();
            if (line==null || line.isBlank()) {
                continue;
            }
            String[] arg = line.split(" ",2);
            String word = arg[0];

            try {
                commandManager.launchCommand(word,line);
                Printer.print("> ");
            } catch (CommandInterruptionException e) {
                Printer.println(e.getMessage());
                running = modeManager.interrupt();
                Printer.print("> ");
            } catch (CommandLaunchException e) {
                Printer.println(e.getMessage());
                Printer.print("> ");
            } catch (ExitInterruptionException e) {
                running = false;
            } catch (QuitInterruptionException e) {
                Printer.print("> ");
            } catch (Exception e){
                Printer.println("UNEXPECTED: \n"+e.getMessage());
                Printer.print("> ");
            }
        }

        Printer.println(exitMessage);
        client.stop();
    }
}