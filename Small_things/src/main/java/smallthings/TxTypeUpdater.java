package smallthings;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TxTypeUpdater {
    @SneakyThrows
    public static void main(String[] args) {
        var list = (List<String>) new ArrayList<String >();
        list.add("123");
        list.add(null);
        list.add("123");
        System.out.println(list);
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(list);
        
//        String fileName = TxTypeUpdater.class.getClassLoader()
//                .getResource("op-type/Transactions_and_operations-Type.csv").getPath();
//        System.out.println("fileName = " + fileName);
//
//        List<CSVRow> beans = new CsvToBeanBuilder(new FileReader(fileName))
//                .withSkipLines(4)
//                .withType(CSVRow.class)
//                .build()
//                .parse();
//
//        System.out.println(beans.get(0));
//        beans.forEach(CSVRow::resolveCodes);
//
//        val rsl = beans.stream()
//                .peek(CSVRow::resolveCodes)
//                .filter(it -> it.getTxTypeCode() > 0)
//                .collect(Collectors.toMap(
//                        it -> it.getOpTypeCode(),
//                        it -> it
//                ));
////                .collect(Collectors.toList());
//        new TxTypesContainer().apply(rsl);
    }
}
