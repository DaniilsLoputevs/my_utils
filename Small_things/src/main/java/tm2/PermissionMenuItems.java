package tm2;

import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionMenuItems {
    
    public static final String
            INVOICE_VIEW_MY = "",
            INVOICE_VIEW_ALL = "",
            WITHDRAWAL_VIEW_MY = "",
            WITHDRAWAL_VIEW_ALL = "";
    
    public static final String
            MENU_ITEM_INVOICES = "",
            MIS_INVOICE = ""
                    ;
    
    public static void main(String[] args) {
    
        // у нас есть за ранние
        val userPermissions = List.of(INVOICE_VIEW_MY, WITHDRAWAL_VIEW_MY);
        val allMenuItems = Map.of(MENU_ITEM_INVOICES, new Object());
        val allMenuSubItems = List.of(new Object(), new Object());
    
        // составляем список того, что Юзеру Можно показывать.
        val usersMenuItems = new ArrayList<Object>();
        val usersMenuSubItems = new ArrayList<Object>();
    
        if (userPermissions.contains(WITHDRAWAL_VIEW_MY)) {
            usersMenuItems.add(allMenuItems.get(MENU_ITEM_INVOICES));
        }
    
        // далее отдаём собранные списки и не паримся с Показывать этот или Не показывать.
    }
}
