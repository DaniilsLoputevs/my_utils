package satomi.foods.food;

import satomi.foods.Food;

import java.util.GregorianCalendar;

public class Strawberry extends Food {
    
    
    public Strawberry(String name, GregorianCalendar expiryDate, GregorianCalendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
