package java8.misuses.lambda.collections;

import java8.structures.Annotations;
import java8.structures.Annotations.My;
import java8.structures.Annotations.Ugly;
import java8.structures.User;

import java.util.*;

public class EmulateMultiMap {
    private static final Map<String, Set<User>> usersByRole = new HashMap<>();
    
    @Ugly
    static class ManuallyInsertSetOnFirstValueForTheKey {
        static public void addUser(User user) {
            user.getRoles().forEach(r -> {
                Set<User> usersInRole = usersByRole.get(r.getName());
                if (usersInRole == null) {
                    usersInRole = new HashSet<>();
                    usersByRole.put(r.getName(), usersInRole);
                }
                usersInRole.add(user);
            });
        }
        
        static public Set<User> getUsersInRole(String role) {
            Set<User> users = usersByRole.get(role);
            return users == null ? Collections.emptySet() : users;
        }
    }
    
    @My
    static class MyClass {
        static public void addUser(User user) {
            user.getRoles().forEach(r ->
                usersByRole.computeIfAbsent(r.getName(), __ -> new HashSet<>())
                        .add(user)
            );
        }
        
        static public Set<User> getUsersInRole(String role) {
            return usersByRole.getOrDefault(role, Collections.emptySet());
        }
    }
    
    @Annotations.Good
    static class ComputeEmptySetIfKeyIsAbsent {
        static public void addUser(User user) {
            user.getRoles().forEach(r -> usersByRole
                    .computeIfAbsent(r.getName(), k -> new HashSet<>())
                    .add(user));
        }
    
        static public Set<User> getUsersInRole(String role) {
            return usersByRole.getOrDefault(role, Collections.emptySet());
        }
    }
}