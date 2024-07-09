package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * WeaponAction for EmberForm.
 */
public class EmberFormAction extends WeaponAction {


    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public EmberFormAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Upon execution adds Rage capability to the WeaponItem assocciated with this Action,
     * Also executes a BurnGroundAction.
     * @param actor
     * @param map
     * @return String representing the action that has occurred.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.addCapability(Abilities.RAGE);
        new BurnGroundAction().execute(actor, map);
        return actor + " enrages and unleashes Ember Form";
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + "goes into Ember Form burning the surroundings";
    }
}
