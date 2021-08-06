package satomi.cache;

import java.nio.file.Paths;

public class Temp {
//    public static void main(String[] args) {
//       /* Path path1 = Path.of("answer.txt");
//        Path nameFile =  path1.getFileName();//имя файла
//        String n = nameFile.toString();
//        System.out.println("Имя после стринга : " + n);*/
//        Path path = Path.of("EmulatorNewFile.txt").toAbsolutePath();
//        System.out.println(path);
//        Path path1 = Path.of("Names.txt").toAbsolutePath();
//        System.out.println(path1);
//
//    }
    
    /**
     * DL - main
     */
    public static void main(String[] args) {
        var cacheDirPath = Paths.get("src", "main", "resources").toFile().getAbsolutePath();
//        var cacheDirPath = Paths.get("Other_code", "src", "main", "resources", "cache").toFile().getAbsolutePath();
        
        System.out.println("cacheDirPath = " + cacheDirPath);
        var emulator = new Emulator(cacheDirPath);
        
        // пое**ть, загружен файл или нет, мы его найдём, даже есть он Смылся.
//        var content = emulator.get("log4j.properties");
        var content = emulator.get("Address.txt");
        System.out.println("content = " + content);
    }
    
}
