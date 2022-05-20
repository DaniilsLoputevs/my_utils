package smallthings;

import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum OpType {
    _00(0, TxType._00),
    _01(1, TxType._01),
    _02(2, TxType._02),
    _03(3, TxType._03),
    _04(4, TxType._03),
    _05(5, TxType._04),
    _06(6, TxType._05),
    
    // Exchange
    _07(7, TxType._06),
    _08(8, TxType._06),
    _09(9, TxType._06),
    
    _10(10, TxType._07),
    _11(11, TxType._08),
    _12(12, TxType._09),
    
    // approve bidOrder
    /** HOLD -> покупатель получает свой купленный ассет. */
    _13(13, TxType._10),
    /** HOLD -> продавец получает оплату без комиссий. */
    _14(14, TxType._10),
    /** HOLD -> продавец получает issuerTax. */
    _15(15, TxType._10),
    /** HOLD -> SYS FEE. */
    _16(16, TxType._10),
    /** HOLD -> Listing broker.*/
    _17(17, TxType._10),
    
    _18(18, TxType._11),
    
    _19(19, TxType._12),
    _20(20, TxType._12),
    
    
    _21(21, TxType._13, "1. Deposit", "1.2. Deposit via Card",
            "Creating deposit request by card", "1.2.1.1"),
    
    _22(22, TxType._14),
    
    
    _23(23, TxType._15),
    _24(24, TxType._15),
    
    _25(25, TxType._16),
    
    _26(26, TxType._17),
    
    _27(27, TxType._18),
    
    _28(28, TxType._19),
    
    
    _29(29, TxType._20),
    _30(30, TxType._20),
    
    
    _31(31, TxType._21),
    
    _32(32, TxType._22),
    _33(33, TxType._22),
    
    
    _34(34, TxType._23),
    
    _35(35, TxType._24),
    _36(36, TxType._25),
    _37(37, TxType._26),
    _38(38, TxType._27),
    METAL_TO_SECONDARY_REQUEST(39, TxType.METAL_TO_SECONDARY_TRANSFER),
    METAL_TO_HOLD_REQUEST(58, TxType.METAL_TO_SECONDARY_TRANSFER),
    _40(40, TxType._29),
    _41(41, TxType._30),
    _42(42, TxType._31),
    _43(43, TxType._32),
    
    // process Withdrawal
    _44(44, TxType._33),
    _45(45, TxType._33),
    
    _46(46, TxType._34),
    _47(47, TxType._35),
    
    _48(48, TxType._36),
    _49(49, TxType._37, "1. Deposit", "1.1. Deposit via Wire transfer",
            "Updating clients balance by wire transfer", "1.1.1.1"),
    
    _50(50, TxType._38),
    _51(51, TxType._39),
    _52(52, TxType._40),
    _53(53, TxType._41),
    _54(54, TxType._41),
    CM_DEPOSIT(55, TxType.CM_EXTENDED_TRANSACTION),
    CM_WITHDRAW(56, TxType.CM_EXTENDED_TRANSACTION),
    CM_TRANSFER(57, TxType.CM_EXTENDED_TRANSACTION),
    
    ;
    
    private final int code;
    private final TxType txType;
    @Setter private String groupOfContracts;
    @Setter private String typeOfContract;
    @Setter private String operationType;
    @Setter private String codeOfOperation;
    
}
class TxTypesContainer {
    private final List<TxTypeDto> dtos = Arrays.stream(OpType.values())
            .map(it -> new TxTypeDto(it.name(), it.getCode(), it.getTxType()))
            .collect(Collectors.toList());
    
    public void apply(Map<Integer, CSVRow> csvRows) {
        val rsl = new StringJoiner("\r\n");
        
        for (val opType : dtos) {
            Optional.ofNullable(csvRows.get(opType.getCode())).ifPresent(it -> {
                opType.groupOfContracts = "\"" + it.getGroupOfContracts() + "\"";
                opType.typeOfContract = "\"" + it.getTypeOfContract() + "\"";
                opType.operationType = "\"" + it.getOperationType() + "\"";
                opType.codeOfOperation = "\"" + it.getCodeOfOperation() + "\"";
            });
            val str = (opType.groupOfContracts != null)
                    ? String.format("    %s(%s, TxType.%s, %s, %s, %s, %s),",
                    opType.name, opType.code, opType.txType.name(),
                    opType.groupOfContracts, opType.typeOfContract, opType.operationType, opType.codeOfOperation)
                    : String.format("    %s(%s, TxType.%s),",
                    opType.name, opType.code, opType.txType.name());
            rsl.add(str);
        }
        
        System.out.println(rsl);
    }
}
@RequiredArgsConstructor
class TxTypeDto {
    final String name;
    @Getter
    final int code;
    final TxType txType;
    
    String groupOfContracts;
    String typeOfContract;
    String operationType;
    String codeOfOperation;
}
