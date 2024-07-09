package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;

import java.util.Random;

public class WindSlashWeaponAction extends WeaponAction {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public WindSlashWeaponAction(WeaponItem weaponItem, Actor target, String direction) {
        super(weaponItem);
        this.target = target;
        this.direction = direction;
    }

    /**
     * Adds charge capability to the weapon associated with this class, while also performing,
     * both an Attack and Stun action on the target. It then removes the wind slash capability following
     * these actions.
     * @param actor
     * @param map
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.addCapability(Abilities.CHARGE);
        new AttackAction(target, direction).execute(actor, map);
        new StunAction(target).execute(actor, map);
        weapon.removeCapability(Abilities.WIND_SLASH);
        return actor + " releases a Wind Slash, doing heavy damage and stunning " + target + " in the process";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " wind slashes " + target + " at " + direction;
    }
}
