package job4j.streamtask;

import java.util.Objects;
import java.util.StringJoiner;

public class Tuple {
    private String name;
    private double score;

    public Tuple(String name, double score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return name;
    }
    
    public double getScore() {
        return score;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tuple tuple = (Tuple) o;
        return Double.compare(tuple.score, score) == 0
                && Objects.equals(name, tuple.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Tuple.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("score=" + score)
                .toString();
    }
    
}
