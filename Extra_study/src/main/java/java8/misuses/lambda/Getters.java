package java8.misuses.lambda;

import java8.structures.User;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Getters {
    public static void main(String[] args) {
        var u =  new User();
        
        Supplier<String> supplierGetter = u::toString;
        Function<User, String> functionGetter = User::toString;
    
        Consumer<String> consumerSetter = u::setName;
        BiConsumer<User, String> biConsumerSetter = User::setName;
    }
}
