package game.terrain;

import edu.monash.fit2099.engine.*;
import game.actions.TradeCindersForWeapon;
import game.actions.PurchaseWeaponAction;
import game.enums.Abilities;
import game.interfaces.TradeWithWeapon;
import game.items.CindersOfLord;
import game.weapons.BroadswordWeapon;
import game.weapons.GiantAxeWeapon;
import game.weapons.StormRulerWeapon;

public class Vendor extends Ground {
    /**
     * A shop where the player can buy weapons.
     */

    public Vendor(){
        /**
         * constructor
         */
        super('V');

    }


    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        /**
         * returns a set of actions that would appear when the player is in the vicinity of the vendor.
         *
         * @param actor,location,direction
         */

        if (actor.hasCapability(Abilities.REST)){
            //adds broadsword
            Actions menu = new Actions(new PurchaseWeaponAction(new BroadswordWeapon(),direction, location));
            //adds giant axe
            menu.add(new PurchaseWeaponAction(new GiantAxeWeapon(),direction, location));
            //adds storm ruler
            menu.add(new PurchaseWeaponAction(new StormRulerWeapon(),direction,location));

            for (Item item: actor.getInventory()) {
                if (item instanceof CindersOfLord){
                    TradeWithWeapon enemyWeapon = (TradeWithWeapon)item;
                    menu.add(new TradeCindersForWeapon(enemyWeapon.getWeapon()));
                    //actor.removeItemFromInventory(item);
                    break;
                }
            }
            return menu;
        }
        else{
            //if the actor is not a player then returns an empty
            return new Actions();
        }
    }

}
