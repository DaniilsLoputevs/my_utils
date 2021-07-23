package satomi.cs.weapon.secondary;

import satomi.cs.weapon.SecondaryWeapon;

public class Usp extends SecondaryWeapon {
    @Override
    protected void shot() {
        System.out.println("pistol - USP - shot");
    }
}
