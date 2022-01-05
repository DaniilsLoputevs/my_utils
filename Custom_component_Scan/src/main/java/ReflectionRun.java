import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReflectionRun {
    
    public static void main(String[] args) throws ClassNotFoundException, IOException {
//        Class<List> temp = List.class;
//        Class<?>[] interfaces = temp.getInterfaces();
//
//        List<Method> ourMethods = new ArrayList<>();
//        System.out.println("LOG: interfaces=" + Arrays.toString(interfaces));
//
//        for (Class<?> anInterface : interfaces) {
//            ourMethods.addAll(Arrays.asList(anInterface.getDeclaredMethods()));
//        }
//
//        for (Method method : ourMethods) {
//            System.out.println(method.getName());
//        }
        
//        var tClass = ClassLoader.getSystemClassLoader().loadClass("job4j.rest.chat.services.RoomServiceImpl");
//        System.out.println("LOG: tClass=" + tClass.getName());
        
        
        
        log("Main", "RUN");
        new ComponentScan().scanAllDirs(ReflectionRun.class);
        log("Main", "END");
        
    }
    
    
    
    static class ComponentScan {
        private List<String> registeredClasses;
        
        
        public void scanAllDirs(Class<?> classInMainApplicationPackage) throws IOException, ClassNotFoundException {
            LinkedList walk = new LinkedList<String>();
            
            
            var arg = classInMainApplicationPackage.getSimpleName() + ".class";
            log("Scan", "arg", arg);
            String mainClassAbsPath = classInMainApplicationPackage.getResource(arg).getPath();
            log("Scan", "mainClassAbsPath", mainClassAbsPath);
            
            
//            log("Scan", "replace", getAbsPath(classInMainApplicationPackage));

            var temp = new File(getAbsPath(classInMainApplicationPackage));
            log("Scan", "exists", temp.exists());
//            var cl = ClassLoader.getSystemClassLoader().loadClass(getAbsPath(classInMainApplicationPackage));
            log("Scan", "ReflectionRun.class.getName()", ReflectionRun.class.getName());
            var cl = ClassLoader.getSystemClassLoader().loadClass(ReflectionRun.class.getName());
            log("Scan", "cl", cl);
            
    
        }
        
        private String getAbsPath(Class<?> anyClass) {
            return anyClass.getResource(anyClass.getSimpleName() + ".class").getPath();
        }
        
        
    }
    
    
//    public static void otherWay() throws IOException {
//        log("METHOD", "RUN");
//        var path = ReflectionRun.class.getSimpleName() + ".class";
//
//        Set<Resource> result = new LinkedHashSet<>(16);
//
//        Enumeration<URL> resourceUrls = ClassLoader.getSystemResources(path);
//        while (resourceUrls.hasMoreElements()) {
//            URL url = resourceUrls.nextElement();
//            result.add(new UrlResource(url));
//        }
//
//
//        log("METHOD", "size ", result.size());
//        result.forEach(each -> log("METHOD", "RSL", each));
//        log("METHOD", " END");
//    }
    
    
    
    
    public static void log(String place, String info, Object obj) {
        System.err.println("LOG :: [" + place + "] -- " + info + " : " + obj);
    }
    
    public static void log(String place, String info) {
        System.err.println("LOG :: [" + place + "] -- " + info);
    }
    
    
}
