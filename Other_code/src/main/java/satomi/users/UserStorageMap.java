package satomi.users;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserStorageMap implements UserStorage {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    
    @Override
    public boolean add(User user) {
        if (users.contains(user.getId())) return false;
        else users.put(user.getId(), user);
        return true;
    }
    
    @Override
    public boolean update(User user) {
        users.put(user.getId(), user);
        return true;
    }
    
    @Override
    public boolean delete(User user) {
        return users.remove(user.getId(), user);
    }
    
    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var from = Optional.ofNullable(users.get(fromId)).orElseThrow(IllegalArgumentException::new);
        var to = Optional.ofNullable(users.get(toId)).orElseThrow(IllegalArgumentException::new);
        
        if (from.getAmount() < amount) throw new IllegalArgumentException("user hasn't enough amount for transfer");
    
        users.replace(fromId, new User(fromId, from.getAmount() - amount));
        users.replace(toId, new User(toId, to.getAmount() + amount));
        
        return true;
    }
    
    @Override
    public int getSize() {
        return users.size();
    }
    
    public static void main(String[] args) {
//        ConcurrentHashMap<Integer, User> userList = new ConcurrentHashMap<>();
//        AtomicInteger id = new AtomicInteger();
//        UserStorage userStorage = new UserStorageMap();
//        userStorage.add(new User(1, 10500));
//        userStorage.add(new User(2, 500));
//        userStorage.add(new User(3, 1000));
//        userList.put(id.incrementAndGet(), new User(1, 10500));
//        userList.put(id.incrementAndGet(), new User(2, 1500));
//        userList.put(id.incrementAndGet(), new User(3, 10500));
    
    
    }
}
