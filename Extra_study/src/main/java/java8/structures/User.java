package java8.structures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private int age;
    private Set<Role> roles = new HashSet<>();
    
    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    public User(Long id, Set<Role> roles) {
        this.id = id;
        this.roles = roles;
    }
}
