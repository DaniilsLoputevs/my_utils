package java8.structures;

import lombok.Data;

import java.util.EnumSet;
import java.util.Set;

@Data
public class Role {
    private String name;
    private Set<Permission> permissions = EnumSet.noneOf(Permission.class);
}
