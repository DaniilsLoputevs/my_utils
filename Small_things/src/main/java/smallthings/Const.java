package smallthings;

public class Const {
    public static final String
            WHEN_EVT_NOT_FOUND ="111",
            WHEN_ID_PM_NOT_FOUND = "222",
            WHEN_STATUS_INCORRECT = "333";
    
    public static final String  whenEvtNotFound(String evtFromNri) {
        return  WHEN_EVT_NOT_FOUND.replaceFirst(":evtFromNri:", evtFromNri);
    }
}
