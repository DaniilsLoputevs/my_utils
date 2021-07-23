package satomi.cs.weapon.secondary;

import satomi.cs.weapon.SecondaryWeapon;

public class Glock extends SecondaryWeapon {
    @Override
    protected void shot() {
        System.out.println("pistol - Glock - shot");
    }
}
