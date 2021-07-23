package satomi.cs;

public abstract class Weapon {
    private String name;
    private double damage;
    private double accuracy;
    private int shots;
    private double fireRate;
    
    protected abstract void shot();
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getDamage() {
        return damage;
    }
    
    public void setDamage(double damage) {
        this.damage = damage;
    }
    
    public double getAccuracy() {
        return accuracy;
    }
    
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
    
    public int getShots() {
        return shots;
    }
    
    public void setShots(int shots) {
        this.shots = shots;
    }
    
    public double getFireRate() {
        return fireRate;
    }
    
    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }
}
