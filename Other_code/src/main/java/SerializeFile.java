import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * THis class requires library: apache commons-fileupload
 * mvn dep:
 * <dependency>
 * <groupId>commons-fileupload</groupId>
 * <artifactId>commons-fileupload</artifactId>
 * <version>1.4</version>
 * </dependency>
 */
public class SerializeFile {
    public static byte[] bytes(File file) {
        byte[] rsl = null;
        try {
            rsl = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
    
    /* --- commented because it should be useful --- */
    
    public static File bytesToFile(String path, byte[] fileBytes) {
        File rsl = new File(path);
        try {
            FileUtils.writeByteArrayToFile(rsl, fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
