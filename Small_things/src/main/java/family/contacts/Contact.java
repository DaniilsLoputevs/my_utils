package family.contacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.codec.net.QuotedPrintableCodec;
import util.AppUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
class Contact {
    public static final String
            SAMSUNG_TAG_FULL_NAME = "FN",
            SAMSUNG_TAG_TELEPHONE = "TEL";
    
    private final String fullName;
    private String telephone;
    private final String account;
    
    public static void main(String[] args) {
        var ref = new Object() {
            int contactIndex = 0;
        };
        
        val rsl = Arrays.stream(
                ("BEGIN:VCARD\n" +
                        "VERSION:2.1\n" +
                        "N:;Ljuba;;;\n" +
                        "FN:Ljuba\n" +
                        "TEL;VOICE:+37129551302\n" +
                        "END:VCARD\n" +
                        "BEGIN:VCARD\n" +
                        "VERSION:2.1\n" +
                        "N:Ira;Tetja;;;\n" +
                        "FN:Tetja Ira\n" +
                        "TEL;HOME;PREF:+37126802660\n" +
                        "END:VCARD\n" +
                        "BEGIN:VCARD\n" +
                        "VERSION:2.1\n" +
                        "N:;Lena;;;\n" +
                        "FN:Lena\n" +
                        "TEL;HOME:+37129703275\n" +
                        "END:VCARD\n" +
                        "BEGIN:VCARD\n" +
                        "VERSION:2.1\n" +
                        "N:Zhenja;Tetja;;;\n" +
                        "FN:Tetja Zhenja\n" +
                        "TEL;HOME:+37125954266\n" +
                        "END:VCARD").split("\n")
        ).collect(Collectors.groupingBy(it -> (it.startsWith("END")) ? ref.contactIndex++ : ref.contactIndex));
        System.out.println(rsl);
        System.out.println(rsl);
        
    }
    private static final QuotedPrintableCodec codec = new QuotedPrintableCodec();
    
    public static Contact ofVCFFileLinesMap(Map<String, String> lines) {
        return new Contact(
                lines.getOrDefault(SAMSUNG_TAG_FULL_NAME, "Без Имени"),
                lines.get(SAMSUNG_TAG_TELEPHONE),
                "Samsung"
        );
    }
    
    public static Function<List<String>, Contact> ofVCFFileLinesList(String account) {
        return list -> ofVCFFileLinesList(list, account);
    }
    
    @SneakyThrows public static Contact ofVCFFileLinesList(List<String> lines, String account) {
        String name = null;
        String phone = null;
        
        for (val line : lines) {
            if (line.startsWith(SAMSUNG_TAG_FULL_NAME)) name = line.substring(line.lastIndexOf(":") + 1);
            if (line.startsWith(SAMSUNG_TAG_TELEPHONE)) phone = line.substring(line.lastIndexOf(":") + 1);
            if (phone != null && name != null) break;
        }
    
        System.out.println(phone);
        // contains printable-quote
        if (name != null && phone != null && name.contains("=")) {
            if (phone.equals("+37129617439")) name = "Родители(Синий)(Лат)";
            if (phone.equals("+37129838645")) name = "Олег Леончик";
            if (phone.equals("+37129755705")) name = "Шура Голован";
            if (phone.equals("+37126521595")) name = "Валера Доставка - Англ.";
            if (phone.equals("+447776156272")) name = "Наш Номер Телефона";
            if (phone.equals("+37120062860")) name = "Сергей Отопление9";
            if (phone.equals("+37129444537")) name = "Директор Шура";
            if (phone.equals("+37126050249") || name.equals("=D0=91=D0=BE=D1=80=D0=B8=D1=81=20=D0=9E=D0=B7=D0=B5=D1=80=D1=81=D0=BA=")) name = "Борис Озерский";
            if (phone.equals("+37129617439")) name = "Родители Синий What'sApp";
            if (name.equals("=D0=9E=D0=BB=D0=B5=D0=B3=20=D0=9B=D0=B5=D0=BE=D0=BD=D1=87=D0=B8=D0=BA=")) name = "Олег Леончик";
            if (name.equals("=D0=A8=D1=83=D1=80=D0=B0=20=D0=93=D0=BE=D0=BB=D0=BE=D0=B2=D0=B0=D0=BD=")) name = "Шура Голован";
            if (name.equals("=D0=90=D0=BD=D0=B3=D0=BB=D0=B8=D1=8F")) name = "Англия";
            else {
                try {
//                    val fnLine = AppUtil.find(lines, it -> it.startsWith(SAMSUNG_TAG_FULL_NAME));
                    val finalName = name;
                    val fnLine = AppUtil.find(lines, it -> it.contains(finalName));
                    if (fnLine.isPresent()) {
                        val extraLine = lines.get(lines.indexOf(fnLine.get())+1);
                        if (extraLine.startsWith("="))
                            name += "\r\n"+extraLine;
                    }
//                    name = name.replaceAll(" ", "");
                    name = codec.decode(name);
                } catch (Throwable e) {
                    System.err.println(name);
//                    name = name.replaceAll(" ", "");
                    name = codec.decode(name+"\r\n");
                }
            }
        }
        
        return new Contact(
                Optional.ofNullable(name).orElse("Без Имени"),
                Optional.ofNullable(phone).orElse("Без Номера"),
                account
        );
    }
    
    public static Function<GoogleContact, Stream<Contact>> googleContactToContact(String account) {
        return googleContact -> googleContactToContact(googleContact, account);
    }
    
    public static Stream<Contact> googleContactToContact(GoogleContact googleContact, String account) {
        val rsl = new ArrayList<Contact>();
        rsl.add(new Contact(
                googleContact.name,
                correctPhone(googleContact.phone1Value),
                account));
        if (googleContact.phone2Value != null && !googleContact.phone2Value.equals(""))
            rsl.add(new Contact(
                    googleContact.name + "(2-ой номер)",
                    correctPhone(googleContact.phone2Value),
                    account));
        return rsl.stream();
    }
    
    public static Comparator<Contact> sortByName() {
        return Comparator.comparing(Contact::getFullName);
    }
    
    private static String correctPhone(String phone) {
        if (phone.equals("")) return null;
        if (phone.contains(" ::: ")) return phone.substring(0, phone.indexOf(" ::: "));
        return phone;
    }
    
    public void compactPhone() {
        if (telephone == null) return;
        this.telephone = telephone.replaceAll(" ", "");
    }
    
    public String toBaseView() {
        return this.fullName + "(" + this.account + ")";
    }
    
    @Override public String toString() {
        return this.fullName + "   " + this.telephone;
    }
    
}
