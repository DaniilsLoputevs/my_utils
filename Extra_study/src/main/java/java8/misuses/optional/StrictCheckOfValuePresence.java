package java8.misuses.optional;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.Ugly;
import java8.structures.User;

import java.util.Optional;

public class StrictCheckOfValuePresence {
    @Ugly
    class ManualCheckForPresenceToThrowException {
        public String getUserName(Long userId) {
            Optional<User> user = findById(userId);
            if (user.isPresent()) {
                return user.get().getName();
            }
            throw new IllegalStateException("User not found");
        }
        
        public void deleteUser(Long userId) {
            Optional<User> user = findById(userId);
            if (user.isPresent()) {
                delete(user.get());
            }
        }
    }
    
    @Good
    class OrElseThrowUsage {
        public String getUserNameMy1(Long userId) {
            return findById(userId).orElseThrow().getName();
        }
        
        public String getUserNameMy2(Long userId) {
            return findById(userId).map(User::getName).orElseThrow(() -> new IllegalStateException("User not found"));
//            return findById(userId).orElseThrow(() -> new IllegalStateException("User not found")).getName();
        }
        
        public void deleteUserMy1(Long userId) {
            findById(userId).ifPresent(StrictCheckOfValuePresence.this::delete);
        }
    }
    
    private Optional<User> findById(Long userId) {
        //search in DB
        return Optional.of(new User(5L, "Mikalai", 33));
    }
    
    private void delete(User user) {
        //delete from DB
    }
}
