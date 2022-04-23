package java8.misuses.lambda.collections;

import java8.structures.Annotations;
import java8.structures.User;

import java.util.Comparator;
import java.util.List;


public class ListSorting {
    @Annotations.Ugly
    class UsingCustomComparator {
        public void sortUsersById(List<User> users) {
            users.sort((x, y) -> Long.compare(x.getId(), y.getId()));
        }
    }
    @Annotations.Bad
    class UsingCustomComparatorIDEA {
        public void sortUsersById(List<User> users) {
            users.sort(Comparator.comparingLong(User::getId));
        }
    }
    @Annotations.Good
    class UsingExistingPredefinedComparator {
        public void sortUsersById(List<User> users) {
            users.sort(Comparator.comparing(User::getId));
        }
    }
}
