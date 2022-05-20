package smallthings;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static smallthings.Const.*;

@RequiredArgsConstructor
public class ExceptionMapper<E> {
    private final Map<Predicate<?>, Function<E, E>> resolver;
    
    public ExceptionMapper<E> add(Predicate<E> predicate, Function<E, E> mapFunc) {
        this.resolver.put(predicate, mapFunc);
        return this;
    }
    
    public static void main(String[] args) {
        val errorFromSap = "111";
        val evtFromNri = "EVT";
        val error = "null";
//        val mapper = new ExceptionMapper<String>(Map.of(
//                (Pair<String> pair) -> pair.getLeft().contains("Данные не ведены") && pair.getRight().contains("EVT"),
//
//                ))
        
        String finishErrorMessage;
//        if (errorFromSap.toLowerCase(Locale.ROOT).contains("данные не выбраны") && evtFromNri.toUpperCase(Locale.ROOT).contains("EVT")) {
//            finishErrorMessage = WHEN_EVT_NOT_FOUND.replaceFirst(":evtFromNri:", evtFromNri);
//        } else if (errorFromSap.toLowerCase(Locale.ROOT).contains("данные не выбраны") && !evtFromNri.toUpperCase(Locale.ROOT).contains("EVT")) {
//            finishErrorMessage = WHEN_ID_PM_NOT_FOUND.replaceFirst(":evtFromNri:", evtFromNri);
//        } else if (errorFromSap.toLowerCase(Locale.ROOT).contains("невозможно изменить")) {
//            finishErrorMessage = WHEN_STATUS_INCORRECT.replaceFirst(":evtFromNri:", evtFromNri);
//        } else if (error != null) {
//            finishErrorMessage = error;
//        } else {
//            finishErrorMessage = "Ошибка произошла во время обновления EVT с типом ZDPO00000000010 - Последняя миля "
//                    .concat(evtFromNri).concat(" ").concat(errorFromSap);
//        }
        finishErrorMessage = resolveFinishErrorMessage(
                errorFromSap.toLowerCase(Locale.ROOT),
                evtFromNri.toUpperCase(Locale.ROOT), error);
        
    }
    
    public static String resolveFinishErrorMessage(String errorFromSap, String evtFromNri, String error) {
        val dataNorEnter = errorFromSap.contains("данные не выбраны");
        if (dataNorEnter && evtFromNri.contains("EVT")) return WHEN_EVT_NOT_FOUND.replaceFirst(":evtFromNri:", evtFromNri);
        if (dataNorEnter && !evtFromNri.contains("EVT")) return WHEN_ID_PM_NOT_FOUND.replaceFirst(":evtFromNri:", evtFromNri);
        if (errorFromSap.contains("невозможно изменить")) return WHEN_STATUS_INCORRECT.replaceFirst(":evtFromNri:", evtFromNri);
        if (error != null) return error;
        return String.format("%s %s %s", "Ошибка произошла во время обновления EVT с типом ZDPO00000000010 - Последняя миля",
                evtFromNri, errorFromSap);
    }
    
    @Data
    static class Pair<T> {
        private final T left;
        private final T right;
    }
}