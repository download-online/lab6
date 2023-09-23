package ru.jefremov.prog.client.commands.arguments;

/**
 * Содержит информацию о типе аргумента: его регулярное выражение, составной ли он или нет, и т. д.
 */
public enum ArgumentType {
    INTEGER("-?\\d+", "Number", true, "an integer number from "+Integer.MIN_VALUE+" to "+Integer.MAX_VALUE, false),
    BYTE("-?\\d+", "Number", true, "an integer number from "+Byte.MIN_VALUE+" to "+Byte.MAX_VALUE, false),
    SHORT("-?\\d+", "Number", true, "an integer number from "+Short.MIN_VALUE+" to "+Short.MAX_VALUE, false),
    LONG("-?\\d+", "Number", true, "an integer number from "+Long.MIN_VALUE+" to "+Long.MAX_VALUE, false),
    FLOAT("-?\\d+([.]\\d+)?", "Fractional", true, "a decimal fraction", false),
    DOUBLE("-?\\d+([.]\\d+)?", "Fractional", true, "a decimal fraction", false),
    STRING("('(([^']|\\s)*)')|(\\\"(([^\\\"]|\\s)*)\\\")|\\S+", "Text", true, "a text (to use spaces, frame the text in quotation marks)", false),
    CHAR("('(([^']|\\s))')|(\\\"(([^\\\"]|\\s))\\\")|\\S+", "Text", true, "a single character", false),
    ENUM("\\S+", "OptionName", true, "the name of the option", false),
    OBJECT("", "Object", false, "composite object", true);

    /**
     * регулярное выражение
     */
    public final String regex;
    /**
     * наименование
     */
    public final String name;
    /**
     * возможно ли представить в текстовой форме
     */
    public final boolean textual;
    /**
     * описание
     */
    public final String description;
    /**
     * является ли составным
     */
    public final boolean complex;
    ArgumentType(String regex, String name, boolean textual, String description, boolean complicity) {
        this.regex = regex;
        this.name = name;
        this.textual = textual;
        this.description = description;
        this.complex = complicity;
    }
}
