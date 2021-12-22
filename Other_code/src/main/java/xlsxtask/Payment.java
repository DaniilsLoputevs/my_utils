package xlsxtask;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
class PaymentRow {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String type;
    private Long senderId;
    private String senderName;
    private Long recipientId;
    private String recipientName;
    private BigDecimal amount;
    private BigDecimal fee;
    private String currency;
}

@Data
public class Payment {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String type;
    private User sender;
    private User recipient;
    private BigDecimal amount;
    private BigDecimal fee;
    private Currency currency;
}



class User {
    private Long id;
    private String name;
}
class Currency {
    private Long id;
    private String label;
}
