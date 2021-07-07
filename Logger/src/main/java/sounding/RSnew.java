package sounding;

import lombok.SneakyThrows;
import lombok.val;
import lombok.var;
import sounding.experement.data.Address;
import sounding.experement.data.User;
import sounding.info.ObjectInfo;

@Deprecated // V2 - всё как бы норм, но вариант что "object == Map<String, Object>"
// видится Перспективнее и проще с точки зрения кода.
public class RSnew {
    public static void main(String[] args) {
        var user = new User(1, "my",
                new Address(44, "SPB", "Find this address in SPB! XD")
        );
//        sounding("user", new BigDecimal(111));
        
        Long id = 0L;
        
        
        System.out.println("rsl = " + id);
    }
    
    @SneakyThrows
    public static void sounding(String objName, Object obj) {


//        var rsl = new StringBuilder();
        var clazz = obj.getClass();
        
        val rsl = new ObjectInfo(null, obj);
//
//        rsl.append(objName).append(" => ").append(clazz.getSimpleName()).append(" {");
//
//        collectFields(rsl, obj, setReflectionAccess(clazz.getDeclaredFields()), lvlOffset(1));
//
//        rsl.append(LS).append("}");
//
//        System.out.println(rsl.toString());
    }
}
