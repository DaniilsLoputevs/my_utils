package smallthings;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VCFrReader {
    public static final String
            DIR_PATH = "C:\\Users\\Admin\\Desktop\\old samsung - contacts",
            FULL_NAME = "FN",
            TELEPHONE = "TEL";
    
    public static void main(String[] args) {
        new VCFrReader()
                .dirToStream(DIR_PATH)
                .forEachOrdered(System.out::println);
    }
    
    public Stream<Contact> dirToStream(String dirPath) {
        return Arrays.stream(Objects.requireNonNull(
                Paths.get(dirPath).toFile().listFiles()))
                .sorted()
                .map(this::fileToContact);
    }
    
    @SneakyThrows private Contact fileToContact(File file) {
        val fileName = this.getFileNameWithoutExt(file);
    
        return Files.newBufferedReader(file.toPath())
                .lines()
                .filter(it -> it.startsWith(FULL_NAME) || it.startsWith(TELEPHONE))
                .collect(Collectors.collectingAndThen(Collectors.toMap(
                        it -> (it.startsWith(FULL_NAME)) ? FULL_NAME : (it.startsWith(TELEPHONE)) ? TELEPHONE : null,
                        it -> {
                            val key = it.substring(it.lastIndexOf(":") + 1);
                            return (key.contains("=") ) ? fileName : key;
                        }
                ), Contact::of));
    }
    
    /**
     * RegExp : removes the last dot and all the characters that follow it.
     */
    private String getFileNameWithoutExt(File file) {
        return file.getName().replaceAll("\\.\\w+$", "");
    }
    
//    private
}

@Data
@RequiredArgsConstructor
class Contact {
    private final String fullName;
    private final String telephone;
    
    public static Contact of(Map<String, String> lines) {
        return new Contact(
                lines.getOrDefault(VCFrReader.FULL_NAME, "Без Имени"),
                lines.get(VCFrReader.TELEPHONE)
        );
    }
    
    @Override public String toString() {
//        return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
//                .add("fullName='" + fullName + "'")
//                .add("telephone='" + telephone + "'")
//                .toString();
        return this.fullName + "   " + this.telephone;
    }
}
