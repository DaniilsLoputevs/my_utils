package java8.misuses.lambda.collections;

import java8.structures.Annotations;
import java8.structures.Permission;
import java8.structures.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RemoveElementWithIterator {
    private final Set<User> users = new HashSet<>();
    
    @Annotations.Ugly
    class ManuallyRemoveElementWithIteratorRemove {
        public void removeUsersWithPermission(Permission permission) {
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.getRoles().stream()
                        .anyMatch(r -> r.getPermissions().contains(permission))) {
                    iterator.remove();
                }
            }
        }
    }
    @Annotations.Good
    class RemoveWithPredicate {
        public void removeUsersWithPermission(Permission permission) {
            users.removeIf(user -> user.getRoles().stream()
                    .anyMatch(r -> r.getPermissions().contains(permission)));
        }
    }
}
