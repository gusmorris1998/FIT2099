package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

import java.util.List;

/**
 * SpinAttackWeaponAction that extends from WeaponAction.
 */
public class SpinAttackWeaponAction extends WeaponAction {
    /**
     * Constructor.
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public SpinAttackWeaponAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Designates an AttackAction to all exits of the actor executing. Also adds
     * a capability that will decrease damage while performing this action and making
     * the hit chance 100%
     * @param actor
     * @param map
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.addCapability(Status.SKILL_ACTIVE);
        Location here = map.locationOf(actor);
        List<Exit> exits = here.getExits();
        for (Exit exit : exits) {
            if (exit.getDestination().containsAnActor()) {
                Actor otherActor = exit.getDestination().getActor();
                new AttackAction(otherActor, exit.getName()).execute(actor, map);
            }
        }
        weapon.removeCapability(Status.SKILL_ACTIVE);
        return actor + " swings axe in a 360 degree motion for " + weapon.damage()/2 + " damage to all adjacent targets";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " swings axe in a 360 degree motion";
    }
}

