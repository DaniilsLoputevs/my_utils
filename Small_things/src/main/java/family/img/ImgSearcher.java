package family.img;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

/**
 * доп глянкть.
 * C:\Users\Admin\AppData\Roaming\Skype\live#3aalenkayledark\media_messaging\emo_cache_v2
 * C:\Users\Admin\AppData\Roaming\Skype\live#3aalenkayledark\media_messaging\media_cache_v3
 * users - documents
 * users - pictures
 */
public class ImgSearcher {
    public static final String
            PATH_TG_DOWNLOAD = "C:\\Users\\aleks\\Downloads\\Telegram Desktop",
            PATH_ROOT_DISK_C = "C:\\",
            PATH_ROOT_DISK_D = "D:\\",
            PATH_ROOT_DISK_C__USERS = "C:\\Users",
            PATH_RSL_OUTPUT = "C:\\Users\\Admin\\Desktop\\ImgSearch - cache.txt";


    // C:\Users\ALEX\Pictures
    public static void main(String[] args) throws IOException {
        MyFileVisitor visitor = new MyFileVisitor();
        Files.walkFileTree(Path.of(PATH_ROOT_DISK_C), visitor);
//        Files.walkFileTree(Path.of(PATH_ROOT_DISK_D), visitor);

//        System.out.println(visitor.images);
        System.out.println();
        visitor.images.forEach(System.out::println);
        System.out.println();
        visitor.exceptions.forEach(System.out::println);
        System.out.println();
        System.out.println("visited: " + visitor.filesVisited);
        System.out.println("images: " + visitor.images.size());
        System.out.println("exceptions: " + visitor.exceptions.size());

        Files.write(Path.of(PATH_RSL_OUTPUT), visitor.images);
    }

    public static class MyFileVisitor extends SimpleFileVisitor<Path> {
        private final List<String> exceptions = new ArrayList<>();
        private final List<String> images = new ArrayList<>();
        private long filesVisited = 0;
        private final Set<String> findExtensions = Set.of(
                ".png", ".PNG",
                ".jpg", ".JPG",
                ".jpeg", ".JPEG",
                ".webp", ".WEBP",
                ".gif", ".GIF"
        );

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            String absPath = file.toAbsolutePath().toString();
//            System.out.println(absPath);

            int indexOfFileExtDot = absPath.lastIndexOf(".");
            String ext = (indexOfFileExtDot > 0) ? absPath.substring(indexOfFileExtDot) : "";

            if (findExtensions.contains(ext)) images.add(absPath);

            filesVisited++;
            String msg = String.format(" at this moment >> files visited: %s exceptions caught: %s images founded: %s",
                    filesVisited, exceptions.size(), images.size());
            System.out.print("\r" + msg);

            return CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            String absPath = dir.toAbsolutePath().toString();
//            System.out.println("pre-visit: " + absPath);
            if (absPath.contains("\\$Recycle.Bin\\") || absPath.contains("\\$RECYCLE.BIN\\")
//                    || absPath.contains("Documents and Settings") // not work
                    || absPath.startsWith("C:\\Users\\aleks\\AppData\\Local")
                    || absPath.startsWith("C:\\Windows")
                    || absPath.startsWith("C:\\ProgramData")

//                    || absPath.startsWith("C:\\Users\\ALEX\\AppData\\Roaming")
//                    || absPath.startsWith("C:\\Users\\ALEX\\AppData\\Local\\Google\\Chrome")
                    || absPath.startsWith("C:\\Program Files (x86)\\Windows Sidebar")
                    || absPath.startsWith("C:\\Program Files (x86)\\Common Files")
                    || absPath.startsWith("C:\\Program Files (x86)\\ContentaConverter-PREMIUM")
                    || absPath.startsWith("C:\\Program Files (x86)\\Adobe\\Acrobat Reader DC")
                    || absPath.startsWith("C:\\Program Files (x86)\\Nero")
                    || absPath.startsWith("C:\\Program Files\\Windows Sidebar")
                    || absPath.startsWith("C:\\Program Files\\KMSpico\\icons")
                    || absPath.startsWith("C:\\Program Files\\FlashIntegro\\VideoEditor\\Templates\\AudioVis\\textures")
                    || absPath.startsWith("C:\\Program Files\\DVD Maker")
                    || absPath.startsWith("C:\\Program Files\\Canon\\IJ Manual\\MG3000 series")
                    || absPath.startsWith("C:\\Program Files\\AVAST Software\\Avast")
                    || absPath.startsWith("C:\\Users\\Public\\Pictures\\Sample Pictures")
                    
//                    || absPath.startsWith("D:\\Ilona-Alex\\GIMP 2\\share")
//                    || absPath.startsWith("D:\\Ilona-Alex\\GIMP 2\\32")
//                    || absPath.startsWith("D:\\Ilona-Alex\\Games\\Hyper Light Drifter")
//                    || absPath.startsWith("D:\\Ilona-Alex\\Desktop\\Музыка_")

//                    || absPath.startsWith("C:\\Users\\ALEX\\Desktop\\25.06.2022 сука все фото") // target
                    || absPath.startsWith("C:\\Danik\\Save All Files\\Arts")
                    || absPath.startsWith("C:\\Users\\Admin\\Desktop\\photoes - from")
                    || absPath.startsWith("C:\\Users\\Admin\\go")
                    || absPath.startsWith("C:\\Users\\Admin\\getting-started\\docs")
                    || absPath.startsWith("C:\\Users\\Admin\\Downloads\\Telegram Desktop")
                    || absPath.startsWith("C:\\Users\\Admin\\Documents\\My Games")
                    || absPath.startsWith("C:\\Users\\Admin\\Desktop\\Desktop- download")
                    || absPath.startsWith("C:\\Users\\Admin\\Desktop\\CODING")
                    || absPath.startsWith("C:\\Users\\Admin\\AppData\\Roaming\\Zoom")
                    || absPath.startsWith("C:\\Users\\Admin\\AppData\\Roaming\\Skype")

                    || absPath.startsWith("C:\\Users\\Public\\AccountPictures\\") // ignore
                    || absPath.contains("IntelliJ")
                    || absPath.contains("Microsoft")
                    || absPath.contains("\\Git\\")
                    || absPath.contains("\\Program Files (x86)\\")
                    || absPath.contains("\\Windows Media Player\\")
                    || absPath.contains("\\Google\\Chrome\\Application\\")
                    || absPath.contains("\\Local\\Programs\\")
                    || absPath.contains("\\Local\\Temp\\")
                    || absPath.contains("\\AppData\\Local\\")
                    || absPath.contains("\\AppData\\Roaming\\Zoom")
//                    || absPath.contains("C:\\ProgramData\\Microsoft")
            ) return SKIP_SUBTREE;
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
//            System.out.println("exception : " + file + "  ==  " + exc);
            exceptions.add(exc.toString());
            return SKIP_SUBTREE;
        }
    }
}
