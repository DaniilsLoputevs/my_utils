package sounding;

import lombok.var;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static java.lang.reflect.Modifier.*;
import static sounding.Colour.*;

public class Modifiers {
    /* Fields Modifiers */
    
//    public static transient final String name = "";
//    public static transient final double name = 1.0d;
    
    
    /**
     * @implNote - show is it Extended or Own field
     */
    public static String findOwnModifier(Field field) {
        // TODO : IMPL
        return "";
    }
    
    public static String findAccessModifier(Field field) {
        var access = field.getModifiers();
        if (isPublic(access)) return colour("pub", GREEN);
        if (isPrivate(access)) return colour("pri", RED);
        if (isProtected(access)) return colour("pro", BLUE);
        return colour("pac", YELLOW);
    }
    
    public static String findStaticModifier(Field field) {
        if (isStatic(field.getModifiers())) return colour("stat", RED);
        return colour("obj", PURPLE);
    }
    
    public static String findFinalOrVolatileModifier(Field field) {
        if (Modifier.isFinal(field.getModifiers())) return colour("val", YELLOW);
        if (Modifier.isVolatile(field.getModifiers())) return colour("vol", BLUE) + colour(" var", CYAN);
        return colour("var", CYAN);
    }
}
