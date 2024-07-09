package game.actions;

import edu.monash.fit2099.engine.*;
import game.items.Wallet;
import game.interfaces.DetailsOfWeapons;
import game.interfaces.Money;
import game.weapons.MeleeWeapon;

import java.util.List;

public class PurchaseWeaponAction extends Action {
    /**
     * This class provides the action for player
     * to buy a certain item from the vendor
     */


    public WeaponItem weapon ;
    private String direction;
    private Location vendorLocation;

    public PurchaseWeaponAction(MeleeWeapon weapon, String direction, Location vendorLocation) {
        /**
         * constructor
         *
         * @param weapon,direction,vendorLocation
         */
        this.weapon = weapon;
        this.direction = direction;
        this.vendorLocation = vendorLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        /**
         * perform the action of buying a weapon at the vendor
         * @param actor,map
         *
         * @return a string of the outcome of the purchase
         */

        //accessing the players wallet through the money interface.
        Money player = ((Money) actor);
        Wallet wallet = player.getWallet();
        //accessing the detials of the given weapon through the detailsOfWeapons interface.
        DetailsOfWeapons weapon = ((DetailsOfWeapons)this.weapon);
        int priceOfWeapon = weapon.getPrice();

        // checking if player has enough money to buy an item.
        if (wallet.totalSouls >= priceOfWeapon) {
            wallet.subtractSouls(priceOfWeapon);
            // deducting the souls and replacing the current weapon in the actors inventory with the weapon that is purchased.
            List<Item> items = actor.getInventory();

            // loop through all inventory
            for (Item item : items) {
                if (item.asWeapon() != null) {
                    actor.removeItemFromInventory(item);
                    break; // after it removes that weapon, break the loop.
                }
            }

            actor.addItemToInventory(this.weapon);
            player.setWallet(wallet);
            return "Purchased " + weapon.getName();
        }
        else
        //message to the user saying item can not be purchased
        {return "Not enough money to buy this item";}
    }

    @Override
    public String menuDescription(Actor actor) {
        /**
         * returns a string that gives the actor to but the weapon.
         */
        DetailsOfWeapons weapon = ((DetailsOfWeapons)this.weapon);
            return "Purchase " + weapon.getName() + " for " + String.valueOf(weapon.getCost()) + " souls "  + "Damage: " + String.valueOf(weapon.damage())+ " Sucess Rate: "+  String.valueOf(weapon.getSuccessRate());

    }

    @Override
    public String hotkey() {
        /**
         * hotkey is set to the display character of the weapon
         */
        return String.valueOf(this.weapon.getDisplayChar());
    }
}
