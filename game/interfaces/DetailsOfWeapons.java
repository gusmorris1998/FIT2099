package game.interfaces;

import edu.monash.fit2099.engine.WeaponItem;

public interface DetailsOfWeapons {
    /**
     * This interface lays the foundation of a weapon class and the getters that it must have in order
     * for the weapon to be intergrated with other modules.
     *
     */


    // returns the attributes of the weapon that are required for the player to see before purchasing the weapon.
     int getCost();
     String getName();
     int getSuccessRate();
     int getPrice();
     int damage();
     WeaponItem getWeapon();
}
