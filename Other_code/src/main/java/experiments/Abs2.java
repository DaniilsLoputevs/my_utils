package experiments;

import java.util.ArrayList;
import java.util.function.Supplier;

public  class Abs2 extends Abs1 {
    
    private static abstract class AA {
    
    }
    private class BB extends AA{
    
        @Override
        public String toString() {
            var t = new Object[10];
            t[5] = "";
            
            var g = new ArrayList();
            return super.toString();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("run");
    
        Supplier<String> meth = () -> {
            return "meth run";
        };
        
        System.out.println("end");
        
        System.out.println(meth.get());
    }
    
    private static String tryReturn() {
        return "";
    }
}
