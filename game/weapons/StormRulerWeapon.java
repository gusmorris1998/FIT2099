package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.ChargeWeaponAction;
import game.actions.WindSlashWeaponAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Storm Ruler Weapon that extends from MeleeWeapon, contains an integer class
 * attributes, charges that is relevant to the weapon action.
 */
public class StormRulerWeapon extends MeleeWeapon implements Weapon {
    private int charges;

    /**
     * Constructor. Has capability to perform critical strike and also has a charge weapon action.
     */
    public StormRulerWeapon() {
        super("Storm Ruler", '7', 70, "cuts", 60,2000);

        addCapability(Abilities.CRITICAL_STRIKE);
        addCapability(Abilities.CHARGE);
    }



    /**
     * If the weapon has capability charging it will increment class attributes charges until it
     * is charged , to which it will then remove charging capability and add wind slash capability.
     * will also add ChargeWeaponAction when relevant
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Where the charging occurs of the weapon.

        if (hasCapability(Status.CHARGING)) {
            if (charges == 0) {
                allowableActions.clear();
                charges += 1;
            } else if (charges < 3) {
                charges += 1;
            } else {
                charges = 0;
                removeCapability(Status.CHARGING);
                addCapability(Abilities.WIND_SLASH);
            }
        }
        if (hasCapability(Abilities.CHARGE) && allowableActions.size() == 0) {
            allowableActions.add(new ChargeWeaponAction(this));
        }
    }

    /**
     * Overrides parent method to account for when skill is activated.
     * @return int: Chance to hit.
     */
    @Override
    public int chanceToHit() {
        // The only point in which Charge and WindSlash are both active are within Windslash.execute,
        //      before AttackAction.execute is performed.
        if (this.hasCapability(Abilities.WIND_SLASH) && this.hasCapability(Abilities.CHARGE)) {
            hitRate = 100;
            return hitRate;
        }
        return super.chanceToHit();
    }

    /**
     * Overrides parent method to account for when skill is activated.
     * @return int: damage
     */
    @Override
    public int damage() {
        // The only point in which Charge and WindSlash are both active are within Windslash.execute,
        //      before AttackAction.execute is performed.
        if (this.hasCapability(Abilities.WIND_SLASH) && this.hasCapability(Abilities.CHARGE)) {
            return damage*2;
        }
        return super.damage();
    }

    /**
     * When called returns the active skill for this weapon(WindSlash)
     * @param target the target actor
     * @param direction the direction of target, e.g. "north"
     * @return new WindSlash action when called.
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new WindSlashWeaponAction(this, target, direction);
    }
}

