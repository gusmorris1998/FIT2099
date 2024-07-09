package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.SwapWeaponAction;
import game.enums.Abilities;
import game.interfaces.DetailsOfWeapons;

import java.util.Random;

public abstract class MeleeWeapon extends WeaponItem implements DetailsOfWeapons {
    protected Random rand = new Random();
    protected int criticalHitChance;
    protected int cost;
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */

    public MeleeWeapon(String name, char displayChar, int damage, String verb, int hitRate,int cost) {
        super(name, displayChar, damage, verb, hitRate);
        this.cost = cost;

    }

    /**
     * Used to disable DropAction for all weapon. All current WeaponItems in current
     * iteration of game extend from this class, so this method is therefore applicable
     * to all current weapons.
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Overriden method for damage that implements the Critical Strike ability for all Weapon with
     * the appropriate capability.
     * @return an integer to represent damage, depending on whether a critical strike or not.
     */
    @Override
    public int damage() {
        if (hasCapability(Abilities.CRITICAL_STRIKE)) {
            criticalHitChance = 20;
            if (rand.nextInt(100) <= criticalHitChance) {
                return damage * 2;
            }
            return damage;
        }
        return damage;
    }

    @Override
    public SwapWeaponAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }


    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSuccessRate() {
        return this.hitRate;
    }

    @Override
    public int getPrice() {
        return this.cost;
    }

    @Override
    public MeleeWeapon getWeapon() {
        return this;
    }
}
