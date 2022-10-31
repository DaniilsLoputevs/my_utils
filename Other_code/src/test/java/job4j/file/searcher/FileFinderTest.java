package job4j.file.searcher;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.xml.bind.ValidationException;
import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * name = "test_copy_file.txt"
 * mask = "^test_copy.*$"
 * regexp = ".*txt"
 */
public class FileFinderTest {
    @Rule public TemporaryFolder folder = new TemporaryFolder();
    private File fileOne;
    private File fileTwo;
    private File fileThree;
    private File fileOutputResult;
    
    @Before @SneakyThrows
    public void setup() {
        fileOne = folder.newFile("test_copy_file.txt");
        fileTwo = folder.newFile("test_copy_file1.txt");
        fileThree = folder.newFile("regexp_test_copy_file.txt");
        fileOutputResult = folder.newFile("test_output_result.txt");
    }
    
    @After
    public void tearDown() {
        fileOne.delete();
        fileTwo.delete();
        fileThree.delete();
        fileOutputResult.delete();
    }
    
    @Test(expected = ValidationException.class)
    public void testValidationRequiredCommand() {
        FileFinder.main(new String[]{
                "-d", null,
                "-n", null,
//                "-t", null,
                "-o", null
        });
    }
    @Test(expected = ValidationException.class)
    public void testValidationRequiredCommandValue() {
        FileFinder.main(new String[]{
                "-d", null,
                "-n", null,
                "-t", null,
                "-o", null
        });
    }
    
    
    @Test
    @SneakyThrows
    public void testSearchByName() {
        FileFinder.main(new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", "test_copy_file.txt",
                "-t", "name",
                "-o", fileOutputResult.getAbsolutePath()
        });
        
        val rsl = Files.readAllLines(fileOutputResult.toPath());
        System.out.println(rsl);
        
        assertEquals(1, rsl.size());
        assertEquals(fileOne.getAbsolutePath(), rsl.get(0));
    }
    
    @Test
    @SneakyThrows
    public void testSearchByMask() {
        FileFinder.main(new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", "^test_copy.*$",
                "-t", "mask",
                "-o", fileOutputResult.getAbsolutePath()
        });
        
        val rsl = Files.readAllLines(fileOutputResult.toPath());
        System.out.println(rsl);
        
        assertEquals(2, rsl.size());
        assertEquals(fileOne.getAbsolutePath(), rsl.get(0));
        assertEquals(fileTwo.getAbsolutePath(), rsl.get(1));
    }
    
    @Test
    @SneakyThrows
    public void testSearchByRegExp() {
        FileFinder.main(new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", ".*txt",
                "-t", "regexp",
                "-o", fileOutputResult.getAbsolutePath()
        });
        
        val rsl = Files.readAllLines(fileOutputResult.toPath());
        System.out.println(rsl);
        
        assertEquals(4, rsl.size());
        assertTrue(rsl.contains(rsl.get(0)));
        assertTrue(rsl.contains(rsl.get(1)));
        assertTrue(rsl.contains(rsl.get(2)));
        assertTrue(rsl.contains(rsl.get(3)));
    }
    
}