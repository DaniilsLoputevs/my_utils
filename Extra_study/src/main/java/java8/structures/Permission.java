package java8.structures;

import java.util.List;

public enum Permission {ADD, EDIT, SEARCH, DELETE;

    public static final List<Permission> VALUES = List.of(values());
}
