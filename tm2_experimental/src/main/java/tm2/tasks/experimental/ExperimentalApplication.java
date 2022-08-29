package tm2.tasks.experimental;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class ExperimentalApplication {
//    @Autowired
//    private StoreTest storeTest;
    
    public static void main(String[] args) {
//        SpringApplication.run(ExperimentalApplication.class, args);
//        System.out.println("APP :: START");
//        val app = new ExperimentalApplication();
//        val stringBuilder = app.storeTest.getStringBuilder();
//        stringBuilder.append("111");
//        stringBuilder.append("222");
//        stringBuilder.append("333");
//
//        System.out.println(stringBuilder.toString());
//        val locEN = Locale.ENGLISH;
//        val locSC = Locale.SIMPLIFIED_CHINESE;
//        val locCN = Locale.forLanguageTag("CN");
//
//        System.out.println("one = " + locEN.getLanguage());
//        System.out.println("two = " + locSC.getLanguage());
//        System.out.println("three = " + locCN.equals(Locale.SIMPLIFIED_CHINESE));
        
        Boolean a = null;
        Boolean b = null;
//        System.out.println("rsl = " + a.equals(b));
        System.out.println("rsl = " + Objects.equals(a,b));
        
        String str = null;
        String str2 = null;
        
        
        if (str instanceof String) {
            System.out.println("str is String");
        }
        if (str == str2) {
        
        }
        
//        Objects.equals()

//        var rsl = com(a, b);
    
    
    }
    
    private static boolean com(Boolean a, Boolean b) {
        return (a != null && b == null) || (a == null && b != null);
    }
    
}
