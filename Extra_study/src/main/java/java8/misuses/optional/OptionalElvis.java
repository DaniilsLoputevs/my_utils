package java8.misuses.optional;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.My;
import java8.structures.Annotations.Ugly;
import java8.structures.User;
import lombok.val;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class OptionalElvis {
    @Ugly
    static class BeforeJava8 {
        public static String getUserName(User user) {
            return (user != null && user.getName() != null) ? user.getName() : "default";
        }
    }

    @Ugly
    static class UsingOptionalIsPresent {
        static public String getUserName(User user) {
            if (Optional.ofNullable(user).isPresent()) {
                if (Optional.ofNullable(user.getName()).isPresent()) {
                    return user.getName();
                }
            }
            return "default";
        }
    }

    @My
    @Good
    static class MyClass {
        static public String getUserName(User user) {
            return Optional.ofNullable(user)
                    .map(User::getName)
                    .orElse("default");
        }
    }

    public static void main(String[] args) {
        test(BeforeJava8::getUserName);
        test(UsingOptionalIsPresent::getUserName);
        test(MyClass::getUserName);
    }

    private static void test(Function<User, String> func) {
        System.out.println("1 => " + func.apply(null));
        System.out.println("2 => " + func.apply(new User(1, "Name", 17)));
        System.out.println();
    }
}
