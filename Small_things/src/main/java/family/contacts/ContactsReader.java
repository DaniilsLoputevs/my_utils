package family.contacts;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static family.contacts.Contact.SAMSUNG_TAG_FULL_NAME;
import static family.contacts.Contact.SAMSUNG_TAG_TELEPHONE;

public class ContactsReader {
    
    @SneakyThrows public List<Contact> fromGoogleCSV(String csvFilePath, String account) {
        val googleContacts = ((List<GoogleContact>) new CsvToBeanBuilder(new FileReader(csvFilePath))
                .withSkipLines(1)
                .withType(GoogleContact.class)
                .build().parse());
        return googleContacts
                .stream()
                .map(GoogleContact.class::cast)
                .flatMap(Contact.googleContactToContact(account))
                .sorted(Contact.sortByName())
                .collect(Collectors.toList());
    }
    
    @SneakyThrows public List<Contact> fromDirWithSingleVCF(String dirPath) {
        return Arrays.stream(Objects.requireNonNull(
                Paths.get(dirPath).toFile().listFiles()))
//        return Files.list(Path.of(dirPath))
//                .filter(it -> it.getFileName().endsWith(".vcf"))
//                .map(Path::toFile)
                .filter(File::isFile)
                .sorted()
                .map(this::fileToContactVCF)
                .sorted(Contact.sortByName())
                .collect(Collectors.toList());
    }
    
    @SneakyThrows public List<Contact> fromSingleVCF(String vcfFilePath, String account) {
        var ref = new Object() {
            int contactIndex = 0;
        };
        return Files.lines(Path.of(vcfFilePath))
                .collect(Collectors.groupingBy(it -> (it.startsWith("END")) ? ref.contactIndex++ : ref.contactIndex))
                .values().stream()
                .map(Contact.ofVCFFileLinesList(account))
                .sorted(Contact.sortByName())
                .collect(Collectors.toList());
    }
    
    
    
    @SneakyThrows private Contact fileToContactVCF(File file) {
        val fileName = this.getFileNameWithoutExt(file);
        
        return Files.lines(file.toPath())
                .filter(it -> it.startsWith(SAMSUNG_TAG_FULL_NAME) || it.startsWith(SAMSUNG_TAG_TELEPHONE))
                .collect(Collectors.collectingAndThen(Collectors.toMap(
                        it -> (it.startsWith(SAMSUNG_TAG_FULL_NAME))
                                ? SAMSUNG_TAG_FULL_NAME
                                : (it.startsWith(SAMSUNG_TAG_TELEPHONE)) ? SAMSUNG_TAG_TELEPHONE : null,
                        it -> {
                            val key = it.substring(it.lastIndexOf(":") + 1);
                            return (key.contains("=")) ? fileName : key;
                        }
                ), Contact::ofVCFFileLinesMap));
    }
    
    /** RegExp : removes the last dot and all the characters that follow it. */
    private String getFileNameWithoutExt(File file) {
        return file.getName().replaceAll("\\.\\w+$", "");
    }
    
}

