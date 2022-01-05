package job4j.streamtask;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Analyze {
    /**
     * Метод вычисляет общий средний балл ученика.
     *
     * @param stream принимает поток объектов типа Ученик.
     * @return возвращает общий средний балл ученика или 0 если оценка отсутствует.
     */
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0);
    }
    
    /**
     * Метод averageScoreBySubject вычисляет средний балл ученика по его предметам.
     *
     * @param stream принимает поток объектов типа Ученик.
     * @return возвращает объект list, список из объекта Tuple (имя ученика и средний балл).
     */
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(pupil -> new Tuple(pupil.getName(), pupil.getSubjects().stream()
                        .mapToInt(Subject::getScore)
                        .average().orElse(0)))
                .collect(toList());
    }
    
    /**
     * Метод averageScoreByPupil вычисляет средний балл по всем предметам для каждого ученика.
     *
     * @param stream принимает поток объектов типа Ученик.
     * @return Возвращает список из объекта Tuple (название предмета и средний балл).
     */
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                //.collect(Collectors.groupingBy(Subject::getName))
                //.collect(Collectors.groupingBy(subject -> subject.getName()), (Collectors.averagingDouble(getScore())))
                .collect(// collect ot map by grouping
                        Collectors.groupingBy(
                                Subject::getName,
                                Collectors.averagingDouble(Subject::getScore))
                )
                .entrySet().stream()
                .map(s -> new Tuple(s.getKey(), s.getValue()))
                .collect(toList());
    }
    
    /**
     * Метод bestStudent - возвращает лучшего ученика.
     * Лучшим считается ученик с наибольшим баллом по всем предметам.
     */
    public static Tuple bestPupil(Stream<Pupil> stream) {
//        return stream
//                .flatMap(pupil -> pupil.getSubjects().stream())
//                .mapToInt(Subject::getScore.sum())
//
//                        .sum()
        
        
        //.max(Integer::compare)
//                .max(Integer::compare).get() //возвращаем макс значение
//                .orElse(0);
        
        return stream
                .map(pupil -> new Tuple(pupil.getName(), pupil.getSubjects().stream()
                        .mapToDouble(Subject::getScore)  // Stream<Integer>
                        .sum()) // int
                )
                .max(Comparator.comparingDouble(Tuple::getScore)) // получаем Tuple только
                                                                // с высшим score, путём перебора их score
                .orElse(new Tuple("", 0)); // не стоит из Optional получать null
    }
    
    /**
     * Метод bestSubject - возвращает предмет с наибольшим баллом для всех студентов.
     * Возвращает объект Tuple (имя предмета, сумма баллов каждого ученика по этому предмету)
     */
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return null;
    }
}
