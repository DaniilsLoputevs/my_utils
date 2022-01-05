package job4j.streamtask;


import java.util.List;

public class RunStream {
    public static void main(String[] args) {
        List<Pupil> pupils = List.of(
                new Pupil("first", List.of(
                        new Subject("Math", 50),
                        new Subject("Rus", 75))
                ),
                new Pupil("second", List.of(
                        new Subject("Math", 70),
                        new Subject("Rus", 65))
                ),
                new Pupil("third", List.of(
                        new Subject("Math", 80),
                        new Subject("Rus", 40))
                )
        );
        
//        var t = Analyze.averageScore(pupils.stream());
//        System.out.println(t);
    
        System.out.println("stream rsl" + Analyze.averageScoreByPupil(pupils.stream()));
//        DevLogOld.print("stream rsl" + Analyze.averageScoreByPupil(pupils.stream()));
//        DevLogOld.print("stream rsl" + Analyze.bestPupil(pupils.stream()));
        System.out.println();
    }
}
