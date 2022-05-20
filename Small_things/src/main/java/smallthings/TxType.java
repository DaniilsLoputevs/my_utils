package smallthings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TxType {
    _00(0),
    _01(1),
    _02(2),
    _03(3),
    _04(4),
    _05(5),
    _06(6),
    _07(7),
    _08(8),
    _09(9),
    _10(10),
    _11(11),
    _12(12),
    _13(13),
    _14(14),
    _15(15),
    _16(16),
    _17(17),
    _18(18),
    _19(19),
    _20(20),
    _21(21),
    _22(22),
    _23(23),
    _24(24),
    _25(25),
    _26(26),
    _27(27),
    METAL_TO_SECONDARY_TRANSFER(28),
    _29(29),
    _30(30),
    _31(31),
    _32(32),
    _33(33),
    _34(34),
    _35(35),
    _36(36),
    _37(37),
    _38(38),
    _39(39),
    _40(40),
    _41(41),
    _42(42),
    CM_EXTENDED_TRANSACTION(43),

    ;

    private final int code;
    private final String initiator;

    TxType(int code) {
        this.code = code;
        this.initiator = "";
    }

    public static TxType byCode(Integer code) {
        return Arrays.stream(values()).filter(it -> it.code == code).findFirst().orElse(null);
    }
}
