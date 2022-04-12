package java8.misuses.optional.usage;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.My;
import java8.structures.Annotations.Ugly;
import java8.structures.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OptionalForCollections {
    private static final String ADMIN_ROLE = "admin";

    @Ugly
    static class TooVerbose {
        static public User findAnyAdmin(Function<String, Optional<List<User>>> findUserByRoleNameFunc) {
            Optional<List<User>> users = findUserByRoleNameFunc.apply(ADMIN_ROLE);
            if (users.isPresent() && !users.get().isEmpty()) {
                return users.get().get(0);
            }
            throw new IllegalStateException("No admins found");
        }
    }

    @Good
    static class NiceAndClean {
        public User findAnyAdmin() {
            return findUsersByRole(ADMIN_ROLE).stream()
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("No admins found"));
        }

        private List<User> findUsersByRole(String role) {
            //real search in DB
            return Collections.emptyList();
        }
    }

    @My
    static class MyClass {
        static public User findAnyAdmin(Function<String, Optional<List<User>>> findUserByRoleNameFunc) {
            return findUserByRoleNameFunc.apply(ADMIN_ROLE)
                    .filter(it -> !it.isEmpty())
                    .orElseThrow(() -> new IllegalStateException("No admins found"))
                    .get(0);
        }
    }

    static private Optional<List<User>> findUsersByRoleEmpty(String role) {
        return Optional.empty();
    }

    static private Optional<List<User>> findUsersByRoleMock(String role) {
        return Optional.of(List.of(new User(1, "Name", 17)));
    }

    public static void main(String[] args) {
        test(TooVerbose::findAnyAdmin);
        test(MyClass::findAnyAdmin);
    }

    private static void test(Function<Function<String, Optional<List<User>>>, User> func) {
        try {
            func.apply(OptionalForCollections::findUsersByRoleEmpty);
        } catch (IllegalStateException e) {
            System.out.println("1 -> " + e.getMessage());
        }
        System.out.println("2 -> " + func.apply(OptionalForCollections::findUsersByRoleMock));
    }
}
