package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;
import game.actions.EmberFormAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Yhorms Great Machete class that extend MeleeWeapon
 */
public class YhormsMachete extends MeleeWeapon implements Weapon {
    /**
     * Constructor.
     */
    public YhormsMachete() {
        super("Yhorm's Great Machete", 'J', 95, "slashes", 60,0);
    }

    /**
     * If the holder is enraged the hit rate of the weapon increase to 90%
     * @return int
     */
    @Override
    public int chanceToHit() {
        if (hasCapability(Abilities.RAGE)) {
            this.hitRate = 90;
            return hitRate;
        }
        return super.chanceToHit();
    }

    /**
     * If the actor holding this weapon has EmberForm capability and weapon does not
     * have rage activated, it executes new EmberFormAction.
     * NOTE: This will only occur once.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Rage is activated within EmberFormAction.execute so it will only happen once
        //      which is when EmberForm capability is added when Yhorm drops below %50 hp
        if (actor.hasCapability(Abilities.EMBER_FORM) && !hasCapability(Abilities.RAGE)) {
            new EmberFormAction(this).execute(actor, currentLocation.map());
        }
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && allowableActions.size() == 0){
            allowableActions.add(new EmberFormAction(this));
        }
    }
}
