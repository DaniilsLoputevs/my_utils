package java8.misuses.optional;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.My;
import java8.structures.Annotations.Ugly;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * cases:
 * X    - X
 * X    - null
 * null - X
 * null - null
 */
public class IfStatementIsNotAlwaysBadThing {
    @Ugly
    static class CombineSomeOptionalsInCleverWay {
        static public Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            return Stream.of(first, second)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(Integer::sum);
        }
    }

    @Ugly
    static class PlayMapGameInEvenMoreCleverWay {
        public static Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            return first.map(b -> second.map(a -> b + a).orElse(b))
                    .map(Optional::of)
                    .orElse(second);
        }
    }

    @Ugly // @VeryFuckingBadCode - ппц, а не вариант
    static class PlayMapGameInEvenMoreCleverWay2 {
        public static Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            return first.map(b -> second.map(a -> b + a).orElse(b)).or(() -> second);
        }
    }

    @Good
    static class OldSchoolButTotallyClearCode {
        static public Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            if (first.isEmpty() && second.isEmpty()) return Optional.empty();
            return Optional.of(first.orElse(0) + second.orElse(0));
        }
    }

    @My
    static class MyClass {
        public static Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            if (first.isPresent() && second.isPresent()) return Optional.of(first.get() + second.get());
            return first.isPresent() ? first : second;
        }
    }

    public static void main(String[] args) {
        test(CombineSomeOptionalsInCleverWay::sum);
        test(PlayMapGameInEvenMoreCleverWay::sum);
        test(PlayMapGameInEvenMoreCleverWay2::sum);
        test(OldSchoolButTotallyClearCode::sum);
        test(MyClass::sum);
    }

    private static void test(BiFunction<Optional<Integer>, Optional<Integer>, Optional<Integer>> func) {
        System.out.println("1 => " + func.apply(Optional.ofNullable(10), Optional.ofNullable(7)));
        System.out.println("2 => " + func.apply(Optional.ofNullable(10), Optional.ofNullable(null)));
        System.out.println("3 => " + func.apply(Optional.ofNullable(null), Optional.ofNullable(7)));
        System.out.println("4 => " + func.apply(Optional.ofNullable(null), Optional.ofNullable(null)));
        System.out.println();
    }
}
