package sounding;

import lombok.SneakyThrows;
import lombok.var;
import sounding.experement.data.Address;
import sounding.experement.data.User;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static sounding.Colour.PURPLE;
import static sounding.Colour.colour;
import static sounding.ReflectionTools.setReflectionAccess;

@Deprecated // V1 - можно выкинуть, но а сейчас тут чисто для "Подсмотреть что-то".
public class ReflectionSounding {
    public static void main(String[] args) {
        var user = new User(1, "my",
                new Address(44, "SPB", "Find this address in SPB! XD")
        );
        sounding("user", new BigDecimal(111));
    }
    
    private static final String LS = System.lineSeparator();
    
    @SneakyThrows
    public static void sounding(String objName, Object obj) {
        var rsl = new StringBuilder();
        var clazz = obj.getClass();
        
        rsl.append(objName).append(" => ").append(clazz.getSimpleName()).append(" {");
        
        collectFields(rsl, obj, setReflectionAccess(clazz.getDeclaredFields()), lvlOffset(1));
        
        rsl.append(LS).append("}");
        
        System.out.println(rsl.toString());
    }

//    private static StringBuilder collectClassInfo(Class<?> clazz) {
//        rsl.append(objName).append(" => ").append(clazz.getSimpleName()).append(" {");
//        return
//    }
    
    
    @SneakyThrows
    private static void collectFields(StringBuilder sb, Object obj, Field[] fields, String lvlOffset) {
        for (var f : fields) {
            System.out.println("DEV :: getDeclaringClass = " + f.getDeclaringClass());
            sb.append(LS).append(lvlOffset);
            // access modifier
//            sb.append(findAccessModifier(f)).append(" ");
//            // static modifier
//            sb.append(findStaticModifier(f)).append(" ");
//            // final modifier
//            sb.append(findFinalModifier(f)).append(" ");
            // type modifier
            sb.append(f.getType().getSimpleName()).append(" ");
            sb.append(colour(f.getName(), PURPLE)).append(" = ").append(warpValue(f.get(obj)));
        }
        for (var f : fields) {
//           collectField(sb, obj, f, lvlOffset)
        }
    }
    
    @SneakyThrows
    private static String collectField(StringBuilder sb, Object obj, Field f, String lvlOffset, boolean isHierarchical) {
        sb.append(LS).append(lvlOffset);
//        f.getDeclaringClass()
//        if (isHierarchical) sb.append()
        // access modifier
//        sb.append(findAccessModifier(f)).append(" ");
//        // static modifier
//        sb.append(findStaticModifier(f)).append(" ");
//        // final modifier
//        sb.append(findFinalModifier(f)).append(" ");
        // type modifier
        sb.append(f.getType().getSimpleName()).append(" ");
        // field value
        sb.append(colour(f.getName(), PURPLE)).append(" = ").append(warpValue(f.get(obj)));
        return null;
    }
    
    
    private static String lvlOffset(int lvl) {
        var rsl = new StringBuilder();
        for (int i = 0; i < lvl; i++) {
            rsl.append("  ");
        }
        return rsl.toString();
    }

//    private static String findOwnModifier(Field field) {
//        // TODO : IMPL
////        var access = field.getModifiers();
////        if (isPublic(access)) return Colour.colour("pub", GREEN);
////        if (isPrivate(access)) return Colour.colour("pri", RED);
////        if (isProtected(access)) return Colour.colour("pro", BLUE);
////        return Colour.colour("pac", YELLOW);
//        return null;
//    }
//
//    private static String findAccessModifier(Field field) {
//        var access = field.getModifiers();
//        if (isPublic(access)) return Colour.colour("pub", GREEN);
//        if (isPrivate(access)) return Colour.colour("pri", RED);
//        if (isProtected(access)) return Colour.colour("pro", BLUE);
//        return Colour.colour("pac", YELLOW);
//    }
//
//    private static String findStaticModifier(Field field) {
//        if (isStatic(field.getModifiers())) return Colour.colour("stat", RED);
//        return Colour.colour("obj", PURPLE);
//    }
//
//    private static String findFinalModifier(Field field) {
//        if (Modifier.isFinal(field.getModifiers())) return Colour.colour("val", YELLOW);
//        return Colour.colour("var", CYAN);
//    }
    
    private static String warpValue(Object fieldValue) {
        if (fieldValue == null) return "null";
//        if (isPrimitive(fieldValue)) return wrapPrimitive(fieldValue);
//        if (isCollection(fieldValue)) return wrapCollection(fieldValue);
//        if (isObject(fieldValue)) return wrapObject(fieldValue);
        
        return fieldValue.toString(); // TODO : TEMP fix
//        throw new IllegalStateException("Fail to warp ${fieldValue}="+ fieldValue.toString());
    }
    
    private static String wrapPrimitive(Object fieldValue) {
        return null; // TODO : IMPL
    }
    
    private static String wrapCollection(Object fieldValue) {
        return null; // TODO : IMPL
    }
    
    private static String wrapObject(Object fieldValue) {
        return null; // TODO : IMPL
    }

//    private static boolean isPrimitive(Object fieldValue) {
//        return false; // TODO : IMPL
//    }
//
//    private static boolean isCollection(Object fieldValue) {
//        return false; // TODO : IMPL
//    }
//
//    private static boolean isObject(Object fieldValue) {
//        return false; // TODO : IMPL
//    }
}


