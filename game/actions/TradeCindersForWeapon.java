package game.actions;

import edu.monash.fit2099.engine.*;
import game.interfaces.DetailsOfWeapons;
import game.items.CindersOfLord;

import java.util.List;

/**
 * This Class is reponsible for trading the cinder for a weapon.
 */
public class TradeCindersForWeapon extends Action {

    //Protected attribute which will only be available to the player once it has been traded.
    protected WeaponItem weaponItem;

    //constructor
    public TradeCindersForWeapon(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    /**
     * The execute method for this action will remove the current weapon from the player's inventory
     *
     * The cinder in player's inventory will also be removed.
     *
     * The Enemies weapon is now added to the player's inventory
     *
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        List<Item> items = actor.getInventory();

        // loop through all inventory
        for (Item item : items) {
            if (item.asWeapon() != null) {
                actor.removeItemFromInventory(item);
                break; // after it removes that weapon, break the loop.
            }
        }
        for (Item item : items) {
            if (item instanceof CindersOfLord) {
                actor.removeItemFromInventory(item);
                break; // after it removes that weapon, break the loop.
            }
        }


        actor.addItemToInventory(this.weaponItem);
        return "Purchased " + weaponItem.toString();
    }

    @Override
    public String menuDescription(Actor actor) {
        /**
         * returns a string that gives the actor to but the weapon.
         */
        DetailsOfWeapons weapon = ((DetailsOfWeapons) this.weaponItem);
        return "Obtain " + weapon.getName() + " for Cinders of Lord";

    }
}
