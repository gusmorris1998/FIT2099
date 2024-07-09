package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

/**
 * StunAction that extends Action.
 */
public class StunAction extends Action {

    /**
     * The Actor that is to be stunned.
     */
    protected Actor target;

    public StunAction(Actor target) {
        this.target = target;
    }

    /**
     * Adds capability to the target stunned that will return a DoNothingAction in a seperate
     * method.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.addCapability(Status.STUNNED);
        return target + " stunned!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
