package java8.misuses.lambda;

import java8.structures.User;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Getters {
    public static void main(String[] args) {
        var u =  new User();
        
        Supplier<String> supplierGetter = u::getName;
        Function<User, String> functionGetterLong = it -> it.getName();
        Function<User, String> functionGetter = User::getName;
        Runnable runnableGetter = u::getName;
        
        Consumer<String> consumerSetter = u::setName;
        BiConsumer<User, String> biConsumerSetterLong = (it, name) -> it.setName(name);
        BiConsumer<User, String> biConsumerSetter = User::setName;
    }
}
