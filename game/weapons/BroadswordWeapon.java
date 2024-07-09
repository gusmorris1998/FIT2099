package game.weapons;

import edu.monash.fit2099.engine.Weapon;
import game.enums.Abilities;

/**
 * Simple class to represent a BroadSword extending from MeleeWeapon
 */
public class BroadswordWeapon extends MeleeWeapon implements Weapon {

    public int price = 500;
    /**
     * Simple constructor that extends from MeleeWeapon. Has an added capability
     * that accounts for it's ability to perform CriticalStrikes
     */
    public BroadswordWeapon() {
        super("Broadsword", '/', 50, "cuts", 80,500);
        addCapability(Abilities.CRITICAL_STRIKE);
    }

}

