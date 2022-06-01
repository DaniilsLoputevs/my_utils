package java8.misuses.stream.collectors;

import java8.structures.Annotations;
import java8.structures.User;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

public class StreamMayBeConvertedToArray {
    @Annotations.Ugly
    class ConvertToArrayViaList {
        public String[] getUserNames(List<User> users) {
            List<String> names = users.stream()
                    .map(User::getName)
                    .collect(Collectors.toList());
            return names.toArray(new String[0]);
        }
    }
    
    class My {
        public String[] getUserNames(List<User> users) {
            val o = users.stream()
                    .map(User::getName)
                    .toArray(String[]::new);
            return o;
        }
    }
}
