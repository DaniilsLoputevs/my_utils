package daniils.date.common;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;

@RequiredArgsConstructor
public class ChronoCharacteristics {
    
    public static ChronoCharacteristics of(LocalDateTime localDateTime) {
        return null;
    }
    public static ChronoCharacteristics of(Calendar calendar) {
        return null;
    }
    
    private final Object timeZone;
    private final Object epoch;
}
