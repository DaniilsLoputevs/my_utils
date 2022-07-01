package family.contacts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.val;
import smallthings.PrintTable;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ContactsBase {
    private final Map<String, List<Contact>> base = new TreeMap<>();
    private Map<String, String> printMap;
    private Map<String, String> export;
    private final ObjectMapper mapper = new ObjectMapper();
    
    
    public MergeResult merge(List<Contact> contacts) {
        val acc = contacts.get(0).getAccount();
        
        val countContactsBefore = base.size();
        contacts.forEach(this::add);
        val countContactsAfter = base.size();
        
        return new MergeResult(acc, countContactsAfter - countContactsBefore);
    }
    
    private void add(Contact contact) {
        if (contact.getTelephone() != null)
            base.computeIfAbsent(contact.getTelephone(), __ -> new ArrayList<>()).add(contact);
        else System.err.println("exception! Phone is null! Contact = " + contact);
    }
    
    public void export(Map<String, String> phoneAndName) {
        export = phoneAndName;
    }
    
    
    @SneakyThrows public void readFromJson(String jsonPath) {
        printMap = mapper.readValue(Path.of(jsonPath).toFile(), new TypeReference<>() {});
    }
    @SneakyThrows public String  writeToJson() {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(base.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, it -> listContactJoinToString(it.getValue()))));
    }
    
    public void printBase() {
        val copy = new TreeMap<>(base);
        Optional.ofNullable(export).ifPresent(exp -> exp.keySet().forEach(copy::remove));
        
        val data = copy.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
        PrintTable.of(data)
                .name("CONTACTS BASE")
                .addColumn("PHONE", "-20", Map.Entry::getKey)
                .addColumn("NAME", "-30", this::entryGetName)
                .print();
    }
    
    public void printImportedMap() {
        val copy = new TreeMap<>(printMap);
        Optional.ofNullable(export).ifPresent(exp -> exp.keySet().forEach(copy::remove));
        
        val data = copy.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
        PrintTable.of(data)
                .name("CONTACTS BASE")
                .addColumn("PHONE", "-20", Map.Entry::getKey)
                .addColumn("NAME", "-30", Map.Entry::getValue)
                .print();
    }
    
    private String entryGetName(Map.Entry<String, List<Contact>> entry) {
        return listContactJoinToString(entry.getValue());
    }
    private String listContactJoinToString(List<Contact> contactList) {
        return contactList.stream().map(Contact::toBaseView).collect(Collectors.joining(", "));
    }
    
    @Data
    public static class MergeResult {
        private final String from;
        private final int mergedUnique;
    }
}
