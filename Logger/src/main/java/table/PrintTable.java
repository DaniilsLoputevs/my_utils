package table;

import lombok.val;
import lombok.var;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;

/**
 * TODO : сделать цвет по возможности - проблема line size + formatter
 *
 * @version 2.0
 */
public class PrintTable<T> {
    public static void main(String[] args) {
        
        System.out.println(new Timestamp(System.currentTimeMillis()));
        val one = new User(1, "Anja", "1-5", 214.0);
        val two = new User(269, "Sveta", "1-9", 197.0);
        val three = new User(337, "Julja", "4-11", 205.5);
        
        PrintTable
                .of(Arrays.asList(one, two, three))
                .name("HR")
                .addColumn("ID", "-4", User::getId)
                .addColumn("NAME", "-5", User::getName)
                .addColumn("YEARS", "-80", User::getYears)
                .addColumn("PRICE", "-4", User::getPrice)
                .print();
        
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
    
    private String name = "PrintTable<Object>";
    private Iterable<T> content;
    private Function<T, Comparator<T>> sort;
    private final List<Column> columns = new ArrayList<>();
    
    private static final String LS = System.lineSeparator();
    private static final String SL = "*SL*";
    
    public static <T> PrintTable<T> of(Iterable<T> content) {
        var rsl = new PrintTable<T>();
        rsl.content = content;
        return rsl;
    }
    
    public PrintTable<T> name(String tableName) {
        this.name = tableName;
        return this;
    }
   
    
    public PrintTable<T> addColumn(String name, String pattern, Function<T, Object> getValue) {
        columns.add(new Column(name, pattern, getValue, Colour.DEFAULT));
        return this;
    }
    
    public PrintTable<T> addColumn(String name, String pattern, Function<T, Object> getValue, Colour colour) {
        columns.add(new Column(name, pattern, getValue, colour));
        return this;
    }
    
    @Override
    public String toString() {
        val rsl = new StringBuilder();
        val formatter = new Formatter(rsl);
        
        val alignFormatSB = new StringBuilder("| ");
        val columnNames = new ArrayList<String>(columns.size());
        
        columns.forEach(column -> {
            alignFormatSB.append(column.pattern).append(" | ");
            columnNames.add(column.name);
        });
        
        val alignLength = alignFormatSB.length();
        alignFormatSB.delete(alignLength - 1, alignLength);
        var alignFormat = alignFormatSB.append("%n").toString();
        
        val headerSB = new StringBuilder();
        new Formatter(headerSB).format(alignFormat, columnNames.toArray());
        val header = headerSB.toString();
        
        rsl.append(SL).append(LS);
        rsl.append(header);
        rsl.append(SL).append(LS);
        
        content.forEach(data -> {
            var args = new ArrayList<>(columns.size());
            
            columns.forEach(column -> args.add("" + column.getValue.apply(data)));
            formatter.format(alignFormat, args.toArray());
        });
        
        rsl.append(SL).append(LS);
        
        return rsl.toString().replace(SL, repeatChar('-', header.length() - 2));
    }
    
    public void print() {
        val r = new StringJoiner(System.lineSeparator())
                .add(name)
                .add(this.toString());
        System.out.println(r);
    }
    
    private String repeatChar(char c, int times) {
        var rsl = new StringBuilder();
        rsl.append('+');
//        System.out.println(times + " ");
        for (int i = 1; i < times - 1; i++) {
//            System.out.println(i);
            rsl.append(c);
        }
        rsl.append('+');
        return rsl.toString();
    }
    
    
    class Column {
        private final String name;
        private final String pattern;
        private final Function<T, Object> getValue;
        private final Colour colour;
        
        public Column(String name, String pattern, Function<T, Object> getValue, Colour colour) {
            this.name = name;
            this.pattern = "%" + pattern + "s";
//            this.pattern = pattern;
            this.getValue = getValue;
            this.colour = colour;
//            System.out.println("pattern = " + this.pattern);
        }
    }
    
    enum Colour {
        BLACK(TEXT_BLACK),
        RED(TEXT_RED),
        GREEN(TEXT_GREEN),
        YELLOW(TEXT_YELLOW),
        BLUE(TEXT_BLUE),
        PURPLE(TEXT_PURPLE),
        CYAN(TEXT_CYAN),
        WHITE(TEXT_WHITE),
        DEFAULT(TEXT_RESET);
        
        
        private final String txtColour;
        
        Colour(String txtColour) {
            this.txtColour = txtColour;
        }
        
        public static String colour(Object txt, Colour colour) {
            return colour.txtColour + txt + TEXT_RESET;
        }
    }
    
    
    /* Colours for text */
    private static final String TEXT_RESET = "\u001B[0m";
    private static final String TEXT_BLACK = "\u001B[30m";
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_GREEN = "\u001B[32m";
    private static final String TEXT_YELLOW = "\u001B[33m";
    private static final String TEXT_BLUE = "\u001B[34m";
    private static final String TEXT_PURPLE = "\u001B[35m";
    private static final String TEXT_CYAN = "\u001B[36m";
    private static final String TEXT_WHITE = "\u001B[37m";
    
    private static final String BACKGROUND_BLACK = "\u001B[40m";
    private static final String BACKGROUND_RED = "\u001B[41m";
    private static final String BACKGROUND_GREEN = "\u001B[42m";
    private static final String BACKGROUND_YELLOW = "\u001B[43m";
    private static final String BACKGROUND_BLUE = "\u001B[44m";
    private static final String BACKGROUND_MAGENTA = "\u001B[45m";
    private static final String BACKGROUND_CYAN = "\u001B[46m";
    private static final String BACKGROUND_WHITE = "\u001B[47m";
}


