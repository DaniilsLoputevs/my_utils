package java8.misuses.optional;

import java8.structures.Annotations.*;
import java8.structures.Role;
import lombok.val;

import java.util.Optional;

public class OptionalOverEngineering {
    @Ugly
    class NullProtectionOverEngineering {
        public Role copyRole(Role role) {
            Role copy = new Role();

            Optional.ofNullable(role.getName()).ifPresent(copy::setName);
            copy.setPermissions(role.getPermissions());
            return copy;
        }
    }
    @Good
    class SimpleConditionalCopying {
        public Role copyRole(Role role) {
            Role copy = new Role();

            if (role.getName() != null) copy.setName(role.getName());
            copy.setPermissions(role.getPermissions());
            return copy;
        }
    }
}
