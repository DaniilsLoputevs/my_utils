package dto.models;

import java.util.StringJoiner;

/**
 * Part model just need fields.
 */
public class UserDTO {
    private int id;
    private String name;
    private String age;

    public UserDTO() {
    }

    public UserDTO(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("age='" + age + "'")
                .toString();
    }
}
