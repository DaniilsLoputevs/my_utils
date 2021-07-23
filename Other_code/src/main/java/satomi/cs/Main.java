package satomi.cs;

import satomi.cs.characters.CTFirst;
import satomi.cs.characters.TerroristsFirst;
import satomi.cs.weapon.primary.AK47;
import satomi.cs.weapon.primary.M16;
import satomi.cs.weapon.primary.P90;
import satomi.cs.weapon.secondary.Glock;

public class Main {
    public static void main(String[] args) {
        Object obj = "";
        
        Character playerCharacter = new CTFirst();
        playerCharacter.setPrimaryWeapon(new M16());
        playerCharacter.setPrimaryWeapon(new P90());
        
        Character enemy = new TerroristsFirst();
        enemy.setPrimaryWeapon(new AK47());
        enemy.setSecondaryWeapon(new Glock());
        
        playerCharacter.getPrimaryWeapon().rapidShots();
        System.out.println("В пельмешку! ^_^");
        
        playerCharacter.pickUpWeapons(enemy.dead());
        
        playerCharacter.getSecondaryWeapon().shot();
        
        
    }
}

























abstract class AbsCache {
    abstract Object get(String key);
}



class Cache extends AbsCache {
    
    public Object get(String key) {
        // code here
        return null;
    }
}


class Emulator {
    private Cache cache = new Cache();
    
    public Object get(String key) {
        return cache.get(key);
    }
    
    
    
    public static void main(String[] args) {
        var emulator = new Emulator();
        emulator.get("");
    }
}


