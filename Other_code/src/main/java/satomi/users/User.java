package satomi.users;

import java.util.Objects;

/**
 * 3. Класс хранилища пользователей UserStorage [#1104]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация ресурсов
 * В этом задании нужно сделать блокирующий кеш UserStorage для модели User.
 */
//@Immutable
public class User {
    private final Integer id;
    private final int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount; // сумма денег на счете пользоваля
    }

    public Integer getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }
}
