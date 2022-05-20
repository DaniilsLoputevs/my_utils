package java8.misuses.stream;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.My;
import java8.structures.Annotations.Ugly;
import java8.structures.Permission;
import java8.structures.User;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class MatchElementInFunctionalStyle {
    private final Set<User> users = new HashSet<>();
    
    @Ugly
    class UseOldSchoolIterationsWithForEachAndExternalBoolean {
        public boolean checkPermission(Permission permission) {
            AtomicBoolean found = new AtomicBoolean();
            users.forEach(
                    u -> u.getRoles().forEach(
                            r -> {
                                if (r.getPermissions().contains(permission)) {
                                    found.set(true);
                                }
                            }
                    )
            );
            return found.get();
        }
    }
    
    @Ugly
    class TryToUseFunctionalStyleWithStreamFilter {
        public boolean checkPermission(Permission permission) {
            return users.stream().filter(
                    u -> u.getRoles().stream()
                            .filter(r -> r.getPermissions().contains(permission))
                            .count() > 0)
                    .findFirst().isPresent();
        }
    }
    
    @Ugly
    class TryToUseStreamMatching {
        public boolean checkPermission(Permission permission) {
            return users.stream()
                    .anyMatch(u -> u.getRoles().stream()
                            .anyMatch(r -> r.getPermissions().contains(permission)));
        }
    }
    
    @My
    class MyClass {
        public boolean checkPermission(Permission permission) {
            return users.stream()
                    .flatMap(u -> u.getRoles().stream())
                    .flatMap(r -> r.getPermissions().stream())
                    .anyMatch(p -> p.equals(permission));
        }
    }
    
    @Good
    class UseFlatMapForSubCollections {
        public boolean checkPermission(Permission permission) {
            
            return users.stream()
                    .flatMap(u -> u.getRoles().stream())
                    .anyMatch(r -> r.getPermissions().contains(permission));
        }
    }
    
    @Good
    class UseFlatMapWithMethodReferencesForSubCollections {
        public boolean checkPermission(Permission permission) {
            return users.stream()
                    .map(User::getRoles)  /** О как можно!!! */
                    .flatMap(Set::stream) /** О как можно!!! x2 */
                    .anyMatch(r -> r.getPermissions().contains(permission));
        }
    }
}
