package smallthings.exam.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Tuple {
    private String name;
    private double score;
    
    public Tuple(Map.Entry<String, Double> entry) {
        this(entry.getKey(), entry.getValue());
    }
    
    public static final Tuple EMPTY = new Tuple("", 0);
}
