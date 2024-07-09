package game.items;

import edu.monash.fit2099.engine.*;
import game.interfaces.TradeWithWeapon;

/**
 * This class handles the Cinders Of Lord that are dropped when a Lord of Cinder is defeated
 */
public class CindersOfLord extends Item implements TradeWithWeapon {

    /**
     * This class will have an attribute of MeleeWeapon as when this item is collected we will be
     * able to obtain this weapon that is the enemy's weapon.
     */

    protected WeaponItem enemiesWeapon;

    /***
     * Constructor.
     */

    public CindersOfLord(WeaponItem enemiesWeapon) {
        super("Cinders of Lord", '!', true);
        this.enemiesWeapon = enemiesWeapon;
    }

    /**
     * getter to be obtain the enemies weapon
     * @return
     */
    @Override
    public WeaponItem getWeapon() {
        return this.enemiesWeapon;
    }
}
