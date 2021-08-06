package satomi.foods.food;


import satomi.foods.Food;

import java.util.GregorianCalendar;

public class Milk extends Food {
    
    
    public Milk(String name, GregorianCalendar expiryDate, GregorianCalendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
