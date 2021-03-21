package entity;

import java.util.StringJoiner;

public class Machine extends PrototypeEntity {
    private int hp;
    private int guns;

    public Machine(String name, int hp, int guns) {
        super(name);
        this.hp = hp;
        this.guns = guns;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGuns() {
        return guns;
    }

    public void setGuns(int guns) {
        this.guns = guns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Machine.class.getSimpleName() + "[", "]")
                .add("hp=" + hp)
                .add("guns=" + guns)
                .toString();
    }
}
