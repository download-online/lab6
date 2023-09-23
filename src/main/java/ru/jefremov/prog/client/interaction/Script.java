package ru.jefremov.prog.client.interaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Скрипт - класс, который позволяет получать команды из текстовых файлов.
 */
public class Script implements Submitter<String> {
    private final File file;
    private final Scanner scanner;
    private String previousLine = "";
    private int previousLineNumber = 0;

    /**
     * Конструктор скрипта
     * @param file файл, в котором содержатся команды
     * @throws FileNotFoundException вызывается, если файл не был обнаружен.
     */
    public Script(File file) throws FileNotFoundException {
        if (file==null) {
            throw new IllegalArgumentException("Script file must not be null");
        }
        this.file = file;
        this.scanner = new Scanner(file);
    }

    /**
     * Вызывается для указания на последнюю запущенную команду.
     * @return строка, содержащая название скрипта, номер последней строки и саму строку.
     */
    public String getTrace() {
        return "("+file.getName()+":"+previousLineNumber+") "+ previousLine;
    }

    
    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    
    @Override
    public String next() {
        String line = scanner.nextLine();
        previousLine = line;
        previousLineNumber++;
        return line;
    }

    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Script script = (Script) other;

        return Objects.equals(file, script.file);
    }

    
    @Override
    public int hashCode() {
        return file.hashCode();
    }
}
