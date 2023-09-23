package ru.jefremov.prog.common.commands;

import ru.jefremov.prog.common.commands.results.CommandResult;
import ru.jefremov.prog.common.commands.states.CommandState;
import ru.jefremov.prog.common.exceptions.command.CommandLaunchException;

public abstract class AbstractCommand <T extends CommandState, U extends CommandResult> {
    /**
     * ключевое слово
     */
    public final String word;
    /**
     * описание
     */
    public final String description;

    public AbstractCommand(String word, String description) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException("Command should have a command word");
        }
        this.word = word;
        this.description = (description==null? "" : description);
    }

    /**
     * Геттер для ключевого слова
     * @return ключевое слово
     */
    public final String getWord() {
        return word;
    }

    public abstract U launch(CommandState state) throws CommandLaunchException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCommand<?, ?> that = (AbstractCommand<?, ?>) o;

        return word.equals(that.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
