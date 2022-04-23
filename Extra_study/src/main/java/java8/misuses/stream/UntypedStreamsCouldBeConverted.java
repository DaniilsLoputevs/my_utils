package java8.misuses.stream;

import java8.structures.Annotations;

import java.util.List;

public class UntypedStreamsCouldBeConverted {
    @Annotations.Ugly
    class ProcessOnlyValuesOfSpecialType {
        public int countDoubleNaNs(List numbers) {
            int count = 0;
            for (Object e : numbers) {
                if (e instanceof Double) {
                    Double d = (Double) e;
                    if (d.isNaN()) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
    @Annotations.Good
    class MyClass {
        public int countDoubleNaNs(List numbers) {
            return (int) numbers.stream()
                    .filter(Double.class::isInstance) /** О как можно!!! */
                    .mapToDouble(Double.class::cast)  /** О как можно!!! x2 */
                    .filter(Double::isNaN)
                    .count();
        }
    }
}
