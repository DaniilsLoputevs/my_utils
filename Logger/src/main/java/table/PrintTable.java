package table;

import lombok.val;
import lombok.var;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.function.Function;

public class PrintTable<T> {
    public static void main(String[] args) {
        
        System.out.println(new Timestamp(System.currentTimeMillis()));
        val one = new User(1, "Anja", "Q1w2e3r4", 66.0);
        val two = new User(269, "Sveta", "Q1w2e3r4", 75.0);
        val three = new User(337, "Julja", "Q1w2e3r4", 59.5);
        
        val table = PrintTable
                .of(Arrays.asList(one, two, three))
                .addColumn("ID", "%-10s", User::getId)
                .addColumn("NAME", "%-2s", User::getName, PrintTable.Colour.CYAN)
                .addColumn("PASSWORD", "%-10s", User::getPassword, PrintTable.Colour.RED)
                .addColumn("PRICE", "%-6s", User::getBalance);
        System.out.println(table);
        
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
    
    private Iterable<T> content;
    private final List<Column> columns = new ArrayList<>();
    
    private static final String LS = System.lineSeparator();
    private static final String EL = "*EMPTY_LINE*";
    
    public static <T> PrintTable<T> of(Iterable<T> content) {
        var rsl = new PrintTable<T>();
        rsl.content = content;
        return rsl;
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
        var rsl = new StringBuilder();
        var formatter = new Formatter(rsl);
        
        var alignFormatTemp = new StringBuilder("| ");
        var headerTemp = new StringBuilder("| ");
        columns.forEach(column -> {
            alignFormatTemp.append(column.pattern).append(" | ");
            headerTemp.append(column.name).append(" | ");
        });
        var alignFormat = alignFormatTemp.append("%n").toString();
        var header = headerTemp.toString();
        
        
        rsl.append(EL).append(LS);
        rsl.append(header).append(LS);
        rsl.append(EL).append(LS);
        
        content.forEach(data -> {
            var args = new ArrayList<>(columns.size());
            
            columns.forEach(column -> args.add("" + column.getValue.apply(data)));
            formatter.format(alignFormat, args.toArray());
        });
        
        rsl.append(EL).append(LS);
        
        return rsl.toString().replace(EL, repeatChar('-', header.length()));
    }
    
    private String repeatChar(char c, int times) {
        var rsl = new StringBuilder();
        System.out.println(times + " ");
        for (int i = 0; i < times; i++) {
            System.out.println(i);
            rsl.append(c);
        }
        return rsl.toString();
    }
    
    
    class Column {
        private final String name;
        private final String pattern;
        private final Function<T, Object> getValue;
        private final Colour colour;
        
        public Column(String name, String pattern, Function<T, Object> getValue, Colour colour) {
            this.name = name;
//                this.pattern = "%" + pattern + "s";
            this.pattern = pattern;
            this.getValue = getValue;
            this.colour = colour;
            System.out.println("pattern = " + this.pattern);
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


class User {
    private final int id;
    private final String name;
    private final String password;
    private final Double balance;
    
    public User(int id, String name, String password, Double balance) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Double getBalance() {
        return balance;
    }
    
}
