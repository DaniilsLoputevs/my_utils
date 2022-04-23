package smallthings;

import lombok.val;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumDuplicates {
    enum UserRole {ADMIN, USER, OPS}
    
    private static final List<UserRole> ORIGINAL_LIST = List.of(
            UserRole.ADMIN, UserRole.ADMIN,
            UserRole.USER,
            UserRole.OPS, UserRole.OPS
    );
    
    
    //    private void roleCheck(UserRole userRole) {
////        if (!checkNotDuplicates(rolePermissionsService.getRolePermissionsList(userRole))) {
////            System.out.println(userRole.name() + " has duplicates! duplicates:" );
////        }
//        val rsl = new StringJoiner(System.lineSeparator());
//        rsl.add(userRole.name() + " has duplicates! duplicates:");
//
////        val origPermissions = rolePermissionsService.getRolePermissionsList(userRole);
//        val origPermissions = ORIGINAL_LIST;
//        val duplicates = findDuplicates(origPermissions);
//        duplicates.forEach(d -> rsl.add(d.name()));
//        if (!duplicates.isEmpty()) {
//            System.out.println(rsl);
//        }
//    }
    
    public static void main(String[] args) {
        System.out.println(findDuplicates(ORIGINAL_LIST));
    }
    
    
//    private boolean duplicates(List<UserRole> permissions) {
//        return new HashSet<>(permissions).size() == permissions.size();
//    }
    
    //    private <T> List<T> findDuplicates(List<T> permissions) {
//        val unique = new HashSet<UserRole>();
//        Predicate<UserRole> mapIf = p -> {
//            if (unique.contains(p)) return true;
//            else {
//                unique.add(p);
//                return false;
//            }
//        };
//        AppUtil.filter(permissions, mapIf);
//        return permissions.stream().filter(it -> !unique.contains(it)).collect(toList());
//    }
    
    private static <T> List<T> findDuplicates(List<T> listContainingDuplicates) {
        final List<T> rsl = new ArrayList<>();
        final Set<T> unique = new HashSet<>();
        
        for (val yourInt : listContainingDuplicates) {
            if (!unique.add(yourInt)) {
                rsl.add(yourInt);
            }
        }
        return rsl;
    }
    
}
