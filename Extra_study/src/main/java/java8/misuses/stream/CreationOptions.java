package java8.misuses.stream;

import java8.structures.Annotations;
import java8.structures.Permission;
import java8.structures.Role;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreationOptions {

    @Annotations.Ugly
    public Stream<Permission> getStreamFromList() {
        return Arrays.asList(Permission.ADD, Permission.DELETE).stream();
    }
    @Annotations.Good
    public Stream<Permission> getStreamFromElements() {
        return Stream.of(Permission.ADD, Permission.DELETE);
    }
    
    
    /*
        Задача: создать N кол-во, объектов Role
     */
    
    @Annotations.Ugly
    public Stream<Role> generateStreamByMappingCopies(int n) {
        return Collections.nCopies(n, "ignored").stream()
                .map(s -> new Role());
    }
    
    @Annotations.Ugly
    public Stream<Role> generateStreamFromRange(int n) {
        return IntStream.range(0, n).mapToObj(i -> new Role());
    }
    
    /**
     * Можно заюзать endless stream + limit - получается Круто!
     */
    @Annotations.Good
    public Stream<Role> generateStreamFromSupplierWithLimit(int n) {
        return Stream.generate(Role::new).limit(n);
    }
    
    /*
        Задача: создать Stream из саб-массива(от index=0, до index={max})
     */
    
    /**
     * max может быть Больше или Меньше чем размер самого массива.
     * Поэтому берём минималку.
     */
    @Annotations.Ugly
    public Stream<Role> generateStreamFromArrayWithRange(Role[] roles, int max) {
        int to = Integer.min(roles.length, max);
        return IntStream.range(0, to).mapToObj(i -> roles[i]);
    }
    
    /**
     * limit(...) - в нашем случаи делает все проверки и предосторожности.
     */
    @Annotations.Good
    public Stream<Role> generateStreamFromArrayWithLimit(Role[] roles, int max) {
        return Stream.of(roles).limit(max);
    }
}
