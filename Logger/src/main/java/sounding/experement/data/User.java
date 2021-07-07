package sounding.experement.data;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User extends AbstractUser {
    public static final long SERIALIZE_ID = 1111;
    
    private final int id;
    private String name;
    public Address address;
    
}
