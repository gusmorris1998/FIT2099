package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.SwapWeaponAction;
import game.enums.Abilities;
import game.interfaces.DetailsOfWeapons;

import java.util.Random;

import static java.lang.Math.abs;

public abstract class RangedWeapon extends WeaponItem implements DetailsOfWeapons {
    protected Random rand = new Random();
    protected int criticalHitChance;
    protected int cost;
    protected int range;
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate, int range) {
        super(name, displayChar, damage, verb, hitRate);
        this.range = range;
        addCapability(Abilities.RANGED);
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
            criticalHitChance = 15;
            if (rand.nextInt(100) <= criticalHitChance) {
                return damage * 2;
            }
            return damage;
        }
        return damage;
    }

    /**
     * Checks the whether the straight line direction of the attack will be blocked if a blockable
     * ground is between the target and the actor performing the action
     * @param targetLocation
     * @param currentLocation
     * @param map
     * @return boolean value depending on whether it is blocked or not
     */
    private boolean isBlocked(Location targetLocation, Location currentLocation, GameMap map) {
        int xDistance = targetLocation.x() - currentLocation.x();
        int yDistance = targetLocation.y() - currentLocation.y();
        if ((xDistance == 0 && yDistance != 0) || (xDistance != 0 && yDistance == 0)) {
            int direction = 0;
            // direction of target from actor. 1, 2, 3, 4 -> north, east, south, west
            if (yDistance < 0) { direction = 1; } if (xDistance > 0) { direction = 2; }
            if (yDistance > 0) { direction = 3; } if (xDistance < 0) { direction = 4; }
            switch (direction) {
                case 1:
                    for (int i = 0; i < abs(yDistance); i++) {
                        if (map.at(currentLocation.x(), currentLocation.y() - i).getGround().blocksThrownObjects()) {
                            return true;
                        }
                    }
                    return false;
                case 2:
                    for (int i = 0; i < abs(xDistance); i++) {
                        if (map.at(currentLocation.x() + i, currentLocation.y()).getGround().blocksThrownObjects()) {
                            return true;
                        }
                    }
                    return false;
                case 3:
                    for (int i = 0; i < abs(yDistance); i++) {
                        if (map.at(currentLocation.x(), currentLocation.y() + i).getGround().blocksThrownObjects()) {
                            return true;
                        }
                    }
                    return false;
                case 4:
                    for (int i = 0; i < abs(xDistance); i++) {
                        if (map.at(currentLocation.x() - i, currentLocation.y()).getGround().blocksThrownObjects()) {
                            return true;
                        }
                    }
                    return false;
            }
        }
        return false;
    }

    /**
     * Checks whether there is a actor within range of the performing actor that an attackAction
     * can be performed on
     * @param currentLocation
     * @param actor
     */
    private void inRange(Location currentLocation, Actor actor) {

        int minX, maxX, minY, maxY;
        NumberRange xs, ys;
        GameMap map = currentLocation.map();

        minX = map.getXRange().min();
        maxX = map.getXRange().max();
        minY = map.getYRange().min();
        maxY = map.getYRange().max();
        xs = new NumberRange(currentLocation.x() - range, (range * 2) + 1);
        ys = new NumberRange(currentLocation.y() - range, (range * 2) + 1);
        for (int x : xs) {
            // if x goes out of range of the maps boundaries, it breaks the loop
            if (x < minX || x > maxX) { break; }
            for (int y : ys) {
                // if y goes out of range of the maps boundaries, it breaks the loop
                if (y < minY || y > maxY) {break;}
                if (map.at(x, y).containsAnActor()) {
                    Actor target = map.at(x, y).getActor();
                    if (!isBlocked(map.at(x, y), currentLocation, map) && actor != target) {
                        this.allowableActions.add(new AttackAction(target, x + ", " + y));
                    }
                }
            }
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        allowableActions.clear();
        inRange(currentLocation, actor);
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
    public WeaponItem getWeapon() {
        return this;
    }
}
