package job4j.streamtask;

import java.util.StringJoiner;

public class Subject {
    private String name;
    private int score;

    public Subject(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Subject.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("score=" + score)
                .toString();
    }
}
