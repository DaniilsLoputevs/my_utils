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

public class ImgSearcher {
    public static final String
            PATH_TG_DOWNLOAD = "C:\\Users\\aleks\\Downloads\\Telegram Desktop",
            PATH_ROOT_DISK_C = "C:\\",
            PATH_ROOT_DISK_D = "D:\\",
            PATH_ROOT_DISK_C__USERS = "C:\\Users";


    // C:\Users\ALEX\Pictures
    public static void main(String[] args) throws IOException {
        MyFileVisitor visitor = new MyFileVisitor();
//        Files.walkFileTree(Path.of(PATH_ROOT_DISK_C), visitor);
        Files.walkFileTree(Path.of(PATH_ROOT_DISK_D), visitor);

//        System.out.println(visitor.images);
        System.out.println();
        visitor.images.forEach(System.out::println);
        System.out.println();
        visitor.exceptions.forEach(System.out::println);
        System.out.println();
        System.out.println("visited: " + visitor.filesVisited);
        System.out.println("images: " + visitor.images.size());
        System.out.println("exceptions: " + visitor.exceptions.size());

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
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String absPath = file.toAbsolutePath().toString();
//            System.out.println(absPath);

            int indexOfFileExtDot = absPath.lastIndexOf(".");
            String ext = (indexOfFileExtDot > 0) ? absPath.substring(indexOfFileExtDot) : "";

            if (findExtensions.contains(ext)) images.add(absPath);
//            if (absPath.endsWith(".png") ||absPath.endsWith(".jpg") ||absPath.endsWith(".jpeg")) {
//                System.out.println("found: " + file.toAbsolutePath());
//                images.add(absPath);
//            }
            filesVisited++;
            String msg = String.format("files visited at this moment: %s exceptions caught: %s",
                    filesVisited, exceptions.size());
            System.out.print("\r" + msg);
//            System.out.print("\r files visited at this moment: " + filesVisit);

//            if (absPath.contains("IntelliJ")) return SKIP_SUBTREE;
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

                    || absPath.startsWith("C:\\Users\\ALEX\\AppData\\Roaming")
                    || absPath.startsWith("C:\\Users\\ALEX\\AppData\\Local\\Google\\Chrome")
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
                    || absPath.startsWith("D:\\Ilona-Alex\\GIMP 2\\share")
                    || absPath.startsWith("D:\\Ilona-Alex\\GIMP 2\\32")
                    || absPath.startsWith("D:\\Ilona-Alex\\Games\\Hyper Light Drifter")
                    || absPath.startsWith("D:\\Ilona-Alex\\Desktop\\Музыка_")

                    || absPath.startsWith("C:\\Users\\ALEX\\Desktop\\25.06.2022 сука все фото") // target

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
