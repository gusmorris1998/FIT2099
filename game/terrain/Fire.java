package game.terrain;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.enums.Abilities;

/**
 * Fire class that extends from ground. Does damage to actors when standing on it.
 */
public class Fire extends Ground {
    protected int burnTime;
    /**
     * Constructor.
     *
     *
     */
    public Fire() {
        super('M');
        burnTime = 0;
    }

    /**
     * As Fire only burns for three turns, it is converted back to Dirt class after
     * three ticks. Also does damage to any applicable Actors standing on it doing
     * 25 damage per turn standing on it.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (burnTime == 3) {
            location.setGround(new Dirt());
        }
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            // Yhorms Machete has capability of Rage following EmberForm, and
            //      therefore immune to any damage Fire does
            if (!actor.hasCapability(Abilities.RAGE)) {
                if (!(actor instanceof Player)) {
                    actor.hurt(25);
                    if (!actor.isConscious()) {
                        Actions dropActions = new Actions();
                        // drop all items
                        for (Item item : actor.getInventory())
                            dropActions.add(item.getDropAction(actor));
                        for (Action drop : dropActions)
                            drop.execute(actor, location.map());
                    }
                }
            }

        }
        burnTime += 1;

    }
}
