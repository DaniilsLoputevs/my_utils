package smallthings;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

import java.util.StringJoiner;

@Getter
public class CSVRow {

//    @CsvBindByPosition(position = 0)
//    private String groupOfContracts;
//    @CsvBindByPosition(position = 1)
//    private String typeOfContract;
//    @CsvBindByPosition(position = 2)
//    private String operationType;
//    @CsvBindByPosition(position = 3)
//    private String codeOfOperation;
//    @CsvBindByPosition(position = 4)
//    private String initiator;
//    @CsvBindByPosition(position = 5)
//    private String opType;
//    @CsvBindByPosition(position = 6)
//    private String txType;
    
    @CsvBindByPosition(position = 0) private String groupOfContracts;
    @CsvBindByPosition(position = 1) private String typeOfContract;
//    @CsvBindByPosition(position = 2) private String hujnja;
    @CsvBindByPosition(position = 3) private String operationType;
    @CsvBindByPosition(position = 4) private String codeOfOperation;
//    @CsvBindByPosition(position = 5) private String initiator;
    @CsvBindByPosition(position = 6) private String opType;
    @CsvBindByPosition(position = 7) private String txType;
    
    private int opTypeCode;
    private int txTypeCode;
    
    public void resolveCodes() {
        this.opTypeCode = (opType != null && !opType.equals("")) ? Integer.parseInt(opType.substring(1)) : -100;
        this.txTypeCode = (txType != null && !txType.equals("")) ? Integer.parseInt(txType.substring(1)) : -100;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(",\r\n" , CSVRow.class.getSimpleName() + "[\r\n", "\r\n]")
                .add("groupOfContracts='" + groupOfContracts + "'")
                .add("typeOfContract='" + typeOfContract + "'")
                .add("operationType='" + operationType + "'")
                .add("codeOfOperation='" + codeOfOperation + "'")
//                .add("initiator='" + initiator + "'")
                .add("opType='" + opType + "'")
                .add("txType='" + txType + "'")
                .toString();
    }
}
