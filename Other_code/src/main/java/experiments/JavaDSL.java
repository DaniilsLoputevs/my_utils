package experiments;

import satomi.User;

import java.util.function.Supplier;

public class JavaDSL {
    public static void main(String[] args) {
        
        var t = new User(
                DSL(() -> {
                    return "";
                }),
                1
        );
    }
    
    public static <T> T DSL(Supplier<T> dsl) {
        return dsl.get();
    }
}
