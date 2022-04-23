package smallthings;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import static java.util.stream.Collectors.toList;

public class JsonToSql {
    private static final String SRC_PATH =
            "C:\\Users\\Admin\\Desktop\\TM2 - notes\\TM2-new trasport problem/migration.json";
    private static final String RSL_PATH =
            "C:\\Users\\Admin\\Desktop\\TM2 - notes\\TM2-new trasport problem/migration_sql.sql";
    
    private static final String TABLE_NAME = "user_bank_accounts";
    private static final String TABLE_ALIASE = "uba";
    private static final String  LS = System.lineSeparator();
    private static int rowNumber = 2;
    
    @SneakyThrows
    public static void main(String[] args) {
        val sqlLines = new ObjectMapper()
                .readValue(Paths.get(SRC_PATH).toFile(), new TypeReference<List<Row>>(){})
                .stream()
                .peek(System.out::println)
                .map(JsonToSql::toSqlRow)
                .collect(toList());
        createFile(RSL_PATH, sqlLines);
    }
    @SneakyThrows
    private static void createFile(String file, List<String> lines) {
        @Cleanup val writer = new FileWriter(file);
        for(String str: lines) {
            writer.write(str + LS);
        }
    }
    
    // /* UPDATE QUERY NO: 52 */
    //UPDATE
    //    user_bank_accounts
    //SET
    //    swift_code = 'RZOOAT2L330',
    //    bank_address_street_and_number = '"Schulstr.',
    //    bank_address_details = ' 2"',
    //    bank_address_city = '',
    //    bank_address_postal_code = 'Koenigswiesen',
    //    bank_address_state = '4280',
    //    bank_address_country = '',
    //    column_7 = 'Austria',
    //    column_8 = '',
    //    column_9 = '',
    //    column_10 = ''
    //WHERE
    //        swift_code = 'RZOOAT2L330';
    private static String toSqlRow(Row row) {
        return new StringJoiner(LS)
                .add("/* EXCEL LINE NO: "+ rowNumber++ + " */")
                .add("UPDATE " + TABLE_NAME)
                .add("SET")
                .add("bank_address_street_and_number = " + wrapSingleQuotesAndComma(row.bank_address_street_and_number))
                .add("bank_address_details = " +wrapSingleQuotesAndComma( row.bank_address_details))
                .add("bank_address_city = " +wrapSingleQuotesAndComma( row.bank_address_city))
                .add("bank_address_postal_code = " + wrapSingleQuotesAndComma(row.bank_address_postal_code))
                .add("bank_address_state = " + wrapSingleQuotesAndComma(row.bank_address_state))
                .add("bank_address_country = " + wrapSingleQuotes(row.bank_address_country))
                .add("WHERE swift_code = " + wrapSingleQuotes(row.swift_code) + ";")
                .add("") // empty line
                .toString();
    }
    private static String wrapSingleQuotesAndComma(String str) {
        return '\'' + str + "',";
    }
    private static String wrapSingleQuotes(String str) {
        return '\'' + str + '\'';
    }
//    @RequiredArgsConstructor
//    static class Operation<T> {
//        private final List<T> value;
//
//        public Operation map() {
//
//        }
//    }
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @ToString
    static class Row {
        @JsonAlias("swift_code=")
        private String swift_code;
        private String bank_address_street_and_number;
        private String bank_address_details;
        private String bank_address_city;
        private String bank_address_postal_code;
        private String bank_address_state;
        private String bank_address_country;
    }
}
