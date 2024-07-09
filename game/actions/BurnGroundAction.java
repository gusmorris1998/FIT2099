package game.actions;

import edu.monash.fit2099.engine.*;
import game.terrain.Fire;

import java.util.List;

/**
 * Simple action class that burns all dirt Ground objects
 * around execution point.
 */
public class BurnGroundAction extends Action {

    /**
     * Execution method that checks all Exits of the actor, and
     * if they happen to be dirt, those said Ground objects are
     * changed to Fire(Ground) objects.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing its action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        List<Exit> exits = here.getExits();
        for (Exit exit : exits) {
            if (exit.getDestination().getGround().getDisplayChar() == '.') {
                exit.getDestination().setGround(new Fire());
            }
        }
        return "Dirt is burnt around " + actor;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Burn Ground";
    }
}
