package smallthings;

import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class ProjectDeleter {
    
    @SneakyThrows
    public static void main(String[] args) {
        deleteDir("C:/Danik/DEVELOPMENT/projects/Dark-Matter");
    }
    
    
    @SneakyThrows
    public static void deleteDir(String dirPath) {
//        Path pathToBeDeleted = TEMP_DIRECTORY.resolve(DIRECTORY_NAME);
        val pathToBeDeleted = Path.of(dirPath);
    
        Files.walk(pathToBeDeleted)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
