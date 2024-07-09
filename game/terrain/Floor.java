package game.terrain;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor.getDisplayChar() == '@') {
			return true;
		} else {
			return false;
		}

	}
}
