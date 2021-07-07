package sounding;

import static sounding.Colour.TextColour.*;

public enum Colour {
    BLACK(TEXT_BLACK),
    RED(TEXT_RED),
    GREEN(TEXT_GREEN),
    YELLOW(TEXT_YELLOW),
    BLUE(TEXT_BLUE),
    PURPLE(TEXT_PURPLE),
    CYAN(TEXT_CYAN),
    WHITE(TEXT_WHITE);
    
    
    private final String txtColour;
    
    Colour(String txtColour) {
        this.txtColour = txtColour;
    }
    
    public static String colour(String txt, Colour colour) {
        return colour.txtColour + txt + TEXT_RESET;
    }
    
    static class TextColour {
        static final String TEXT_RESET = "\u001B[0m";
        static final String TEXT_BLACK = "\u001B[30m";
        static final String TEXT_RED = "\u001B[31m";
        static final String TEXT_GREEN = "\u001B[32m";
        static final String TEXT_YELLOW = "\u001B[33m";
        static final String TEXT_BLUE = "\u001B[34m";
        static final String TEXT_PURPLE = "\u001B[35m";
        static final String TEXT_CYAN = "\u001B[36m";
        static final String TEXT_WHITE = "\u001B[37m";
        
        static final String BACKGROUND_BLACK = "\u001B[40m";
        static final String BACKGROUND_RED = "\u001B[41m";
        static final String BACKGROUND_GREEN = "\u001B[42m";
        static final String BACKGROUND_YELLOW = "\u001B[43m";
        static final String BACKGROUND_BLUE = "\u001B[44m";
        static final String BACKGROUND_MAGENTA = "\u001B[45m";
        static final String BACKGROUND_CYAN = "\u001B[46m";
        static final String BACKGROUND_WHITE = "\u001B[47m";
    }
}


