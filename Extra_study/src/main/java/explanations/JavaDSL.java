package explanations;

import java.util.ArrayList;
import java.util.HashMap;

public class JavaDSL {
    
    public static void main(String[] args) {
//        val listLocal = new ArrayList<String>() {
//            {
//                add("a tut mozno vot-tak-vot!!!");
//                add("i e6o vot-tak!!!");
//            }
//        };
//        val listNormal = Arrays.asList("111", "222");
//
//        System.out.println("clazz = " + listLocal.getClass().getName());
//        System.out.println("values = " + listLocal);
//        System.out.println("clazz = " + listNormal.getClass().getName());
//        System.out.println("values = " + listNormal);
        System.out.println(
                new HashMap<>() {{
                    put("xxx+admin@gmail", new ArrayList<>() {{
                        add("admin");
                        add("ops");
                    }});
                    put("xxx+ops@gmail", new ArrayList<>() {{
                        add("ops");
                    }});
                }}
        );


//        System.out.println(new MySuperObj());
    }
    
    static class MySuperObj {
        static {
            System.out.println("static init stage");
        }
        
        {
            System.out.println("obj init stage");
        }
    }
}
