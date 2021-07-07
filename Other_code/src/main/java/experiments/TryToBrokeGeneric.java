package experiments;

import java.util.List;

public class TryToBrokeGeneric {

    public static void main(String[] args) {
        Class<String> type = String.class;


        List<Class> types = List.of(
                String.class
        );

        List<Object> objects = List.of(
                "aaa", "bbb", "ccc"
        );

        var test = get(objects,0, types.get(0));

        System.out.println(test.getClass().getName());
        System.out.println(test.getClass());
        System.out.println(test);

    }

    private static <R> R get(List<Object> objects,
            int index, Class<R> returnType) {

        return returnType.cast(objects.get(index));
    }
}
