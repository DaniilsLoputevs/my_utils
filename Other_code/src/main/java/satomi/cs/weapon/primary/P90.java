package satomi.cs.weapon.primary;

import satomi.cs.weapon.PrimaryWeapon;

public class P90 extends PrimaryWeapon {
    @Override
    protected void shot() {
        System.out.println("shot - P90");
    }
    
    @Override
    public void rapidShots() {
        System.out.println("tra-ta-ta - P90");
    }
}
