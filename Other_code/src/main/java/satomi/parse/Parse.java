package satomi.parse;

import java.util.function.Predicate;

public interface Parse {

   public String content(Predicate<Character> filter);
}
