package smallthings.exam.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Analyze {
    /**
     * вычисляет средний балл ученика по его предметам.
     * Точнее: Средний балл всех переданных Учеников. (считая каждый Предмет у этих учеников).
     */
    public static double averageScore(Stream<Pupil> stream) {
//        return stream
//                .flatMap(pupil -> pupil.getSubjects().stream())
//                .mapToInt(Subject::getScore)
//                .average()
//                .orElse(-1);

//        return stream
//                .map(Pupil::getSubjects)
//                .mapToDouble(Analyze::averageOfAllSubjects)
//                .average().orElse(-1);
        
//        return stream
//                .flatMap(Pupil::getSubjectsAsStream)
//                .collect(averagingInt(Subject::getScore));
        return stream
                .map(Pupil::getSubjects)
                .flatMap(List::stream)
                .collect(averagingInt(Subject::getScore));
    }
    
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .flatMap(Pupil::getSubjectsAsStream)
                .collect(collectingAndThen(groupingBy(Subject::getName, averagingInt(Subject::getScore)), Analyze::mapToTuples));

//        return stream
//                .flatMap(Pupil::getSubjectsAsStream)
//                .collect(groupingBy(Subject::getName, averagingInt(Subject::getScore)))
//                .entrySet()
//                .stream().map(Tuple::new).collect(toList());
    }
    
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.getName(), averageOfAllSubjects(pupil.getSubjects()))).collect(toList());
    }
    
    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(pupil -> new Tuple(pupil.getName(), sumOffAllSubjectsScores(pupil.getSubjects())))
                .max(Comparator.comparing(Tuple::getScore)).orElse(Tuple.EMPTY);
    }
    
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .flatMap(Pupil::getSubjectsAsStream)
                .collect(collectingAndThen(groupingBy(Subject::getName, summingDouble(Subject::getScore)),
                        map -> map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Tuple::new).orElse(Tuple.EMPTY)
//                        map ->  map.entrySet().stream().collect(maxBy(Map.Entry.comparingByValue())).map(Tuple::new).orElse(Tuple.EMPTY)
                ));
    }
    
    
    private static double averageOfAllSubjects(List<Subject> subjects) {
        return subjects.stream().mapToInt(Subject::getScore).average().orElse(0);
    }
    
    public static double sumOffAllSubjectsScores(List<Subject> subjects) {
        return subjects.stream().mapToInt(Subject::getScore).sum();
//        return subjects.stream().map(Subject::getScore).reduce(Integer::sum).orElse(0);
    }
    
    private static List<Tuple> mapToTuples(Map<String, Double> map) {
        return map.entrySet().stream().map(Tuple::new).collect(toList());
    }
}
