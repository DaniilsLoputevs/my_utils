package smallthings;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import static java.util.stream.Collectors.toList;

public class JsonToSql {
    private static final String SRC_PATH =
            "C:\\Users\\Admin\\Desktop\\TM2-mig/user-bank-account-src-json.json";
    private static final String RSL_PATH =
            "C:\\Users\\Admin\\Desktop\\TM2-mig/migration_sql.sql";
    
    private static final String TABLE_NAME = "user_bank_accounts";
    private static final String TABLE_ALIASE = "uba";
    private static final String LS = System.lineSeparator();
    private static int rowNumber = 2;
    
    @SneakyThrows
    public static void main(String[] args) {
        val sqlLines = new ObjectMapper()
                .readValue(Paths.get(SRC_PATH).toFile(), new TypeReference<List<Row>>() {})
                .stream()
                
//                .peek(System.out::println)
                .map(JsonToSql::toSqlRow)
//                .peek(System.out::println)
                .collect(toList());
        createFile(RSL_PATH, sqlLines);
    }
    
    @SneakyThrows
    private static void createFile(String file, List<String> lines) {
        @Cleanup val writer = new FileWriter(file);
        for (String str : lines) {
            writer.write(str + LS);
        }
    }
    
    /* EXCEL LINE NO: 2 */
//    UPDATE user_bank_accounts
//    SET
//            bank_address_street_and_number = '1 Southampton Row',
//            bank_address_details = null,
//            bank_address_city = 'London',
//            bank_address_postal_code = 'WC1B 5HA',
//            bank_address_state = null,
//            bank_address_country = 'United Kingdom (GB)'
//    WHERE swift_code = 'MYMBGB2L';
    private static String toSqlRow(Row row) {
        if (row.bank_address_street_and_number == null || StringUtils.isBlank(row.bank_address_street_and_number))
            row.bank_address_street_and_number = "missing";
        
        if (row.bank_address_details == null || StringUtils.isBlank(row.bank_address_details))
            row.bank_address_details = null;
        
        if (row.bank_address_city == null || StringUtils.isBlank(row.bank_address_city))
            row.bank_address_city = "missing";
        
        if (row.bank_address_postal_code == null || StringUtils.isBlank(row.bank_address_postal_code))
            row.bank_address_postal_code = "missing";
        
        if (row.bank_address_state == null || StringUtils.isBlank(row.bank_address_state))
            row.bank_address_state = null;
        
        if (row.bank_address_country == null || StringUtils.isBlank(row.bank_address_country))
            row.bank_address_country = "missing";
        
        val building = row.bank_address_details;
        val state = row.bank_address_state;
        return new StringJoiner(LS)
                .add("/* EXCEL LINE NO: " + rowNumber++ + " */")
                .add("UPDATE " + TABLE_NAME)
                .add("SET")
                .add("bank_address_street_and_number = " + wrapSingleQuotesAndComma(row.bank_address_street_and_number))
                .add("bank_address_details = " + ((building == null) ? (null + ",") : wrapSingleQuotesAndComma(row.bank_address_details)))
                .add("bank_address_city = " + wrapSingleQuotesAndComma(row.bank_address_city))
                .add("bank_address_postal_code = " + wrapSingleQuotesAndComma(row.bank_address_postal_code))
                .add("bank_address_state = " + ((state == null) ? (null + ",") : wrapSingleQuotesAndComma(row.bank_address_state)))
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
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @ToString
    static class Row {
        @JsonAlias("swift_code=")
        private String swift_code;
        private String bank_address_street_and_number; // Street and Number*
        private String bank_address_details; // Suite/Unit/Building/Floor
        private String bank_address_city; // City*
        private String bank_address_postal_code; // Postal code*
        private String bank_address_state; // State - RED
        private String bank_address_country; // Country*
    }
}
