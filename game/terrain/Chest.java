package game.terrain;

import edu.monash.fit2099.engine.*;
import game.actions.OpenChestAction;
import game.enums.Abilities;


/**
 * This Class handles the CHEST which is represented by '?' on the map
 * This will extend the Ground class as only players are allowed to interact with the
 */
public class Chest extends Ground {
    /**
     * Opening the chest allows the user to either claim either a reward or spawns
     * an enemy (MIMIC)
     */

    public Chest(){
        /**
         * constructor
         */
        super('?');

    }


    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        /**
         * If the player interacts with the chest , an option to open the chest is returned
         *
         * @param actor,location,direction
         */

        if (actor.hasCapability(Abilities.REST)){
            Actions menu = new Actions(new OpenChestAction(location));
            return menu;
        }
        else{
            //if the actor is not a player then returns an empty
            return new Actions();
        }
    }

}
