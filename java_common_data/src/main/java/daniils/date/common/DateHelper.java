package daniils.date.common;

public interface DateHelper {
    
    Object localeDateTime();
    Object calendar();
    
    // todo - later
    Object localeDate();
    Object localeTime();
    Object date();
    Object sqlDate();
    Object sqlTime();
    Object sqlTimestamp();
}
