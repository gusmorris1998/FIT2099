package game.weapons;

import game.enums.Abilities;

public class DarkmoonLongbow extends RangedWeapon {
    /**
     * DarkmoonLongbow that extends from RangedWeapon abstract class
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", '}', 70, "Pierces", 80, 3);
        addCapability(Abilities.CRITICAL_STRIKE);
    }
}
