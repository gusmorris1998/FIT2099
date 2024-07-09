package game.terrain;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.enums.Abilities;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

    public Valley() {
        super('+');
        this.addCapability(Abilities.FALL);
    }

    /**
     *
     * @param actor the Actor to check
     * @return true if the actor is a Player, else false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof Player) {
            return true;
        }
        return false;

    }

    /**
     * Tick method for Valley which deals lethal damage to any actor standing
     * on location
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if (location.containsAnActor()){

            Actor actor = location.getActor();
            actor.hurt(1000);

        }
    }

}
