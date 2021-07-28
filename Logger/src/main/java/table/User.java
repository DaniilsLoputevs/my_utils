package table;

class User {
    private final int id;
    private final String name;
    private final String years;
    private final Double price;
    
    public User(int id, String name, String years, Double price) {
        this.id = id;
        this.name = name;
        this.years = years;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getYears() {
        return years;
    }
    
    public Double getPrice() {
        return price;
    }
    
}
