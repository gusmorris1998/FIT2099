package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.terrain.Dirt;
import game.enemies.Mimic;

import java.util.Random;


/**
 *This class handles the action of opening the chest that is marked as a ? on the map
 */
public class OpenChestAction extends Action {

    /**
     * The location of the chest is stored as an attribute.
     * This location is later used and referenced to execute this action
     */
    protected Location chestLocation;

    /**
     * constructor
     */
    public OpenChestAction(Location location) {
        this.chestLocation = location;
    }

    /**
     * This execute function takes in an actor and map as usual and randomly selects whether a new enemy will be spawned or whether
     * tokens are dropped. Mimic constructors and DropTokens class are initiated accordingly
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rn = new Random();
        int choice = rn.nextInt(1);

        if (choice == 0 ){
            this.chestLocation.addActor(new Mimic(chestLocation));
            this.chestLocation.setGround(new Dirt());
        }

        else {
            DropTokens drop = new DropTokens();
            drop.DropTokens(this.chestLocation);
        }

        //removes the chesta fter it is once opened
        chestLocation.setGround(new Dirt());
        return actor + " opens chest";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Open Chest";
    }
}
