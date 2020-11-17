package dto.models;

import java.util.StringJoiner;

/**
 * Full model with ALL fields.
 */
public class User {
    private int id;
    private String name;
    private String age;
    private String info;
    private String extra;
    private String email;
    private String phone;

    public User() {
    }

    public User(int id, String name, String age, String info, String extra, String email, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.info = info;
        this.extra = extra;
        this.email = email;
        this.phone = phone;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("age='" + age + "'")
                .add("info='" + info + "'")
                .add("extra='" + extra + "'")
                .add("email='" + email + "'")
                .add("phone='" + phone + "'")
                .toString();
    }
}
