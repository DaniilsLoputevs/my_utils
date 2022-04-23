package java8.misuses.lambda;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.My;
import java8.structures.Annotations.Ugly;
import java8.structures.Permission;
import java8.structures.Role;
import java8.structures.User;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;
import static java8.structures.Permission.*;

public class AvoidComplexLambdas {
    private static final Set<User> users = Set.of(
            new User(1L, Set.of(new Role("Admin", EnumSet.allOf(Permission.class)))),
            new User(2L, Set.of(new Role("Ops", Set.of(ADD, EDIT, SEARCH)))),
            new User(3L, Set.of(new Role("Client", Set.of(SEARCH)))),
            new User(4L, Set.of(new Role("Client Premium", Set.of(SEARCH))))
    );
    
    @Ugly
    static class UsingComplexLambdaInPlace {
        public static Set<User> findEditors() {
            return users.stream()
                    .filter(u -> u.getRoles().stream()
                            .anyMatch(r -> r.getPermissions().contains(Permission.EDIT)))
                    .collect(toSet());
        }
    }
    
    @My @Good
    static class MyClass {
        public static Set<User> findEditors() {
            return users.stream()
                    .filter(MyClass::hasEditPermission)
                    .filter(user -> hasEditPermission(user, EDIT))
                    .filter(hasPermission(EDIT))
                    .collect(toSet());
        }
        @My @Good
        public static boolean hasEditPermission(User u) {
            return u.getRoles().stream()
                    .anyMatch(r -> r.getPermissions().contains(Permission.EDIT));
        }
        @My
        public static boolean hasEditPermission(User u, Permission permission) {
            return u.getRoles().stream()
                    .anyMatch(r -> r.getPermissions().contains(permission));
        }
        @My @Good
        public static Predicate<User> hasPermission(Permission permission) {
            return u -> u.getRoles().stream()
                    .anyMatch(r -> r.getPermissions().contains(permission));
        }
    }
    public static void main(String[] args) {
        System.out.println(UsingComplexLambdaInPlace.findEditors());
        System.out.println(MyClass.findEditors());
    }
    
}
