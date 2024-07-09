package game.weapons;

import edu.monash.fit2099.engine.Weapon;
import game.actions.SpinAttackWeaponAction;
import game.enums.Status;

/**
 * Class for Giant Axe that extends from MeleeWeapon.
 */
public class GiantAxeWeapon extends MeleeWeapon implements Weapon {
    /**
     * Constructor. Adds a new allowable action within it; SpinAttackWeaponAction.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public int price = 1000;
    public GiantAxeWeapon() {
        super("Giant Axe", 'P', 50, "swings", 80,1000);
        allowableActions.add(new SpinAttackWeaponAction(this));
    }

    /**
     * As calling SpinAttackAction is akin to calling AttackAction for all actor exits,
     * the damage must be halved if AttackActions are coming from SpinAttackAction.
     * @return the damage of Weapon depending on capabilities
     */
    @Override
    public int damage() {
        if (hasCapability(Status.SKILL_ACTIVE)) {
            return damage/2;
        }
        return damage;
    }

    /**
     * As calling SpinAttackAction is akin to calling AttackAction for all actor exits,
     * the chanceToHit must be %100 if being called from SpinAttackAction.
     * @return the chanceToHit of Weapon depending on capabilities
     */
    @Override
    public int chanceToHit() {
        if (hasCapability(Status.SKILL_ACTIVE)) {
            return 100;
        }
        return hitRate;

    }
}

