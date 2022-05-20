package explanations;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MultiCast {
    
    public static void main(String[] args) {
        val ints = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
//        val ints = List.of(0,9,8,7,6,5,4,3,2,1);
//
//        System.out.println(ints.subList(5, ints.size() -1));
//        val rsl = ints.stream()
//                .sorted(Integer::compareTo)
//                .limit(5)
//                .collect(Collectors.toList());
//        System.out.println(rsl);


//        AtomicInteger id = new AtomicInteger();
//        val rsl2 =Stream.of(new MutObj(0, "aaa"),new MutObj(1, "bbb"),new MutObj(2, "ccc") )
//                .peek(mutObj -> mutObj.setId(id.getAndIncrement()))
//        .collect(Collectors.toList());
//        System.out.println(rsl2);
//
        val src = "USD";
        System.out.println(src.substring(src.indexOf("/") + 1));
        System.out.println(Map.of("111", "aaa", "222", "bbb"));
        LocalDateTime.now().isBefore(null);
        
        
        val o = (Object) new MyConfig();
        val r = (ToJSON & ToXML) o;
//        r.toXML();
//        r.toJSON();
    }
    
    static class MyConfig implements ToJSON, ToXML {
        
        @Override public String toXML() {
            return null;
        }
        
        @Override public String toJSON() {
            return null;
        }
    }
    
    interface ToXML {
        String toXML();
    }
    
    interface ToJSON {
        String toJSON();
    }
    
    @AllArgsConstructor @Getter @Setter @Data class MutObj {
        private int id;
        private String name;
    }
    
}
