package satomi.foods;

import java.util.GregorianCalendar;

public abstract class Food {
    private String name;
    private GregorianCalendar createDate;
    private GregorianCalendar expiryDate;
    private double price;
    private double discount;
    
    public Food(String name, GregorianCalendar createDate, GregorianCalendar expiryDate, double price, double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }
    
    public double getQualityPercents() {
        long create = createDate.getTimeInMillis();
        long now = System.currentTimeMillis();
        long expire = expiryDate.getTimeInMillis();
        if (now > expire) return 100.00;
        long valueOfMaxPercents = expire - create;
        long currentPercents = now - create;
        return ((double) currentPercents) / ((double) valueOfMaxPercents);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public GregorianCalendar getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(GregorianCalendar createDate) {
        this.createDate = createDate;
    }
    
    public GregorianCalendar getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(GregorianCalendar expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
