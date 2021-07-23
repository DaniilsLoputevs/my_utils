package satomi.cs;

import satomi.cs.weapon.PrimaryWeapon;
import satomi.cs.weapon.SecondaryWeapon;

import java.util.List;

public abstract class Character {
    private PrimaryWeapon primaryWeapon;
    private SecondaryWeapon secondaryWeapon;
    
    public List<Weapon> dead() {
        var rsl = List.of(primaryWeapon, secondaryWeapon);
        primaryWeapon = null;
        secondaryWeapon = null;
        return rsl;
    }
    public void pickUpWeapons(List<Weapon> weapons) {
        weapons.forEach( w -> {
            if (primaryWeapon == null && w instanceof PrimaryWeapon) {
                setPrimaryWeapon((PrimaryWeapon) w);
            } else  if ( secondaryWeapon == null && w instanceof SecondaryWeapon) {
                setSecondaryWeapon((SecondaryWeapon) w);
            }
        });
    }
    
    public PrimaryWeapon getPrimaryWeapon() {
        return primaryWeapon;
    }
    
    public void setPrimaryWeapon(PrimaryWeapon primaryWeapon) {
        this.primaryWeapon = primaryWeapon;
    }
    
    public SecondaryWeapon getSecondaryWeapon() {
        return secondaryWeapon;
    }
    
    public void setSecondaryWeapon(SecondaryWeapon secondaryWeapon) {
        this.secondaryWeapon = secondaryWeapon;
    }
}
