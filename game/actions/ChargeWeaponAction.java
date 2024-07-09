package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

public class ChargeWeaponAction extends WeaponAction {


    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public ChargeWeaponAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.removeCapability(Abilities.CHARGE);
        weapon.addCapability(Status.CHARGING);
        return "charging";
        }


    @Override
    public String menuDescription(Actor actor) {
        return "Charge " + weapon + " "  + "/3";
    }
}
