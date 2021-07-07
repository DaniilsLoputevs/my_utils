package satomi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


//TODO Дополните тест проверками на чтение файла с комментарием,
// а также файла с нарушением шаблона ключ=значение (напр. ключ=)
// в этом случае нужно ожидать исключение IllegalArgumentException

public class ConfigTest {
//    @Test
//    public void whenPairWithoutComment() {
//        String path = "app.properties";
//        Config config = new Config(path);
//        config.load();
//        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
//    }
    
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    @Test //(expected = IllegalArgumentException.class)
//    @Test(expected = IllegalArgumentException.class)
    public void whenValueIsEmpty() {
        exceptionRule.expect(IllegalArgumentException.class);
        String path = "testApp.properties";
        Config config = new Config(this.getResourcePath(path));
//        config.load();
        Throwable exception = assertThrows(IllegalArgumentException.class, config::load);
        assertEquals("expected messages", exception.getMessage());
    }
    
    @Test
    public void testFooThrowsIndexOutOfBoundsException() {
//        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> foo.doStuff());
//        assertEquals("expected messages", exception.getMessage());
    }
    
    
    /**
     * Copy from
     * https://stackoverflow.com/questions/5529532/how-to-get-a-test-resource-file
     * * Ctrl + click - open link
     */
    public String getResourcePath(String resourcePath) {
        final var pathURL = Optional.ofNullable(
                Thread.currentThread().getContextClassLoader().getResource(resourcePath)
        );
        return pathURL.map(url -> new File(url.getPath()).getPath()).orElse(null);
    }
}

