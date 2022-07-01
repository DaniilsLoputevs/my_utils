package family.contacts;

import lombok.val;

import java.net.URL;
import java.util.Map;
import java.util.Optional;

/**
 * TODO : Export to CSV
 * TODO : -- formatting
 * TODO : From Samsung ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * TODO : From Huawei
 * TODO : From Nokia Sinij(Mama)
 * TODO : From aleksandLoputevs@gmail.com ^^^^^^^^^^^^^^^^^^^^
 * TODO : From loputevi67@gmail.com ^^^^^^^^^^^^^^^^^^^^^^^^^^
 * TODO : From
 * TODO :
 * TODO :
 * TODO : From laiwiense@gmail.com
 * TODO : From LG (Danik)
 * TODO : From Nokia Belij(Danik)
 * TODO : From Redmi MT (Danik)
 * TODO :
 * TODO :
 */
public class Main {
    public static final String
            SAMSUNG_DIR_PATH = "C:\\Users\\Admin\\Desktop\\contacts base\\src\\old samsung - contacts",
            ALEX_GOOGLE_CSV_PATH = "C:\\Users\\Admin\\Desktop\\contacts base\\src\\ALEX.csv",
            LOPUTEVI67_GOOGLE_CSV_PATH = "C:\\Users\\Admin\\Desktop\\contacts base\\src\\LOPUTEVI67.csv",
            NOKIA_GOOGLE_CSV_PATH = "C:\\Users\\Admin\\Desktop\\contacts base\\src\\NOKIA_MAMA_ALL_IN_ONE.vcf",
            HUAWEI_GOOGLE_CSV_PATH = "C:\\Users\\Admin\\Desktop\\contacts base\\src\\HUAWEI_ALEX_ALL_IN_ONE.vcf";
    
    /**
     * https://www.webatic.com/quoted-printable-convertor
     */
//    @SneakyThrows
//    public static void main(String[] args) {
//        val defaultSystemOutput = System.out;
//        System.setOut(new PrintStream(new ByteArrayOutputStream()));
//
//
//        val reader = new ContactsReader();
//
//        val samsung = reader.fromDirWithSingleVCF(SAMSUNG_DIR_PATH);
//
//        val aleksandrLoputevs = reader.fromGoogleCSV(ALEX_GOOGLE_CSV_PATH, "ALEX-GOOGLE");
//        aleksandrLoputevs.add(new Contact("Женя(2-ой номер)", "25 954 266", "ALEX-GOOGLE"));
//        aleksandrLoputevs.add(new Contact("Ира(2-ой номер)", "26 802 660", "ALEX-GOOGLE"));
//        aleksandrLoputevs.sort(Comparator.comparing(Contact::getFullName));
//
//        val loputevi67 = reader.fromGoogleCSV(LOPUTEVI67_GOOGLE_CSV_PATH, "LOPUTEVI67-GOOGLE");
//        val nokiaPlus = reader.fromSingleVCF(NOKIA_GOOGLE_CSV_PATH, "NOKIA 3.1");
//        val huawei = reader.fromSingleVCF(HUAWEI_GOOGLE_CSV_PATH, "HUAWEI");
//
//
//        System.setOut(defaultSystemOutput);
//
//
////        PrintTable.of(aleksandrLoputevs)
////                .name("ALEX-GOOGLE")
////                .addColumn("PHONE", "-20", Contact::getTelephone)
////                .addColumn("NAME", "-30", Contact::getFullName)
////                .addColumn("ACCOUNT", "-13", Contact::getAccount)
////                .print();
//        aleksandrLoputevs.forEach(Contact::compactPhone);
//
////        PrintTable.of(loputevi67)
////                .name("LOPUTEVI67-GOOGLE")
////                .addColumn("PHONE", "-20", Contact::getTelephone)
////                .addColumn("NAME", "-30", Contact::getFullName)
////                .addColumn("ACCOUNT", "-13", Contact::getAccount)
////                .print();
//        loputevi67.forEach(Contact::compactPhone);
//
////        PrintTable.of(samsung)
////                .name("SAMSUNG")
////                .addColumn("PHONE", "-20", Contact::getTelephone)
////                .addColumn("NAME", "-30", Contact::getFullName)
////                .addColumn("ACCOUNT", "-13", Contact::getAccount)
////                .print();
//
////        PrintTable.of(nokiaPlus)
////                .name("NOKIA 3.1")
////                .addColumn("PHONE", "-20", Contact::getTelephone)
////                .addColumn("NAME", "-30", Contact::getFullName)
////                .addColumn("ACCOUNT", "-13", Contact::getAccount)
////                .print();
//        nokiaPlus.forEach(Contact::compactPhone);
//
////        PrintTable.of(huawei)
////                .name("HUAWEI")
////                .addColumn("PHONE", "-20", Contact::getTelephone)
////                .addColumn("NAME", "-30", Contact::getFullName)
////                .addColumn("ACCOUNT", "-13", Contact::getAccount)
////                .print();
//        huawei.forEach(Contact::compactPhone);
//
//
//        val base = new ContactsBase();
//        System.out.println(base.merge(samsung));
//        System.out.println(base.merge(aleksandrLoputevs));
//        System.out.println(base.merge(loputevi67));
//        System.out.println(base.merge(nokiaPlus));
//        System.out.println(base.merge(huawei));
//
//        base.export(Map.ofEntries(
//                entry("*#1345#", "Balance Check(HUAWEI)")
//        ));
//        base.printBase();
//
//        val jsonExport = base.writeToJson();
//        System.out.println();
//        System.out.println();
//        System.out.println(jsonExport);
//
//        Files.writeString(Path.of("C:\\Users\\Admin\\Desktop\\contacts base\\trg\\export_to_json.json"), jsonExport);
//
//    }
    
    /* From other comps */
    public static void main(String[] args) {
        val base = new ContactsBase();
        base.readFromJson(getAbsPathOfResource("family/contacts/export_to_json.json"));
        base.export(Map.ofEntries(
//                entry("*#1345#", "Balance Check(HUAWEI)")
        ));
        base.printImportedMap();
    }
    
    private static String getAbsPathOfResource(String resourcePath) {
        val rsl =  Optional.ofNullable(Main.class.getClassLoader().getResource(resourcePath))
                .map(URL::getPath).orElseThrow(() -> new RuntimeException("Resource not found!"));
        return rsl.substring(1);
    }
    
}
