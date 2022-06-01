package smallthings.exam.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class Pupil {
    private String name;
    private List<Subject> subjects;
    
    public Stream<Subject> getSubjectsAsStream() {
        return subjects.stream();
    }
}
