package experiments;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileRepair {
    private static final String  SRC_PATH = "C:\\Users\\Admin\\Desktop\\temp1/Ashe3.jpg";
    private static final String  TRG_PATH = "C:\\Users\\Admin\\Desktop\\temp1/Ashe.png";
    
    private static final Map<String, Path> dirs = new HashMap<>();
    
    
    public static void main(String[] args) {
//        var src = new File(SRC_PATH);
//        var bytes = FileIOHelper.bytes(src);
//        var trg = FileIOHelper.bytesToFile(TRG_PATH, bytes);
    
    
//        Path startingDir = Path.of("C:\\Users\\Admin\\Desktop\\temp1");
//        Path startingDir = Path.of("C:\\Users\\Admin\\Desktop\\Games");
//        PrintFiles pf = new PrintFiles();
//        walk(startingDir, pf);
//
//        dirs.forEach((key, val) -> {
//            System.out.println(key);
//        });
        
        
        
      
        var obj = new Example();
        System.out.println("obj = " + obj);
        obj.show();
    }
    
    static class Example {
        
        public void show() {
            System.out.println("this = " + this);
        }
    }
    
    
    
    
    
    
    
    
    
    
    private static void walk(Path startingDir, FileVisitor<? super Path> visitor) {
        try {
            Files.walkFileTree(startingDir, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    public static class PrintFiles extends SimpleFileVisitor<Path> {
        
        // Print information about
        // each type of file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            
            if (attr.isSymbolicLink()) {
//                System.out.format("Symbolic link: %s ", file);
            } else if (attr.isRegularFile()) {
//                System.out.format("Regular file: %s ", file);
            } else {
//                System.out.format("Other: %s ", file);
            }
//            System.out.println("(" + attr.size() + "bytes)");
            return CONTINUE;
        }
        
        // Print each directory visited.
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
//            System.out.format("Directory: %s%n", dir);
            dirs.put(dir.toString(), dir);
            return CONTINUE;
        }
        
        // If there is some error accessing
        // the file, let the user know.
        // If you don't override this method
        // and an error occurs, an IOException
        // is thrown.
        @Override
        public FileVisitResult visitFileFailed(Path file,
                                               IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }
    
    
    
    
    
    public static class FileIOHelper {
        
        /* --- Private Things --- */

    private static byte[] bytes(File file) {
        byte[] rsl = null;
        try {
            rsl = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

        /* --- commented because it should be useful --- */

    private static File bytesToFile(String path, byte[] fileBytes) {
        File rsl = new File(path);
        try {
            FileUtils.writeByteArrayToFile(rsl, fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
    }
}
