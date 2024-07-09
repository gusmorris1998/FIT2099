package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class FogDoor extends Item {

    public FogDoor() {
        super("Fog Door", '=', false);
    }

    public void addAction(Action action){
        this.allowableActions.add(action);
    }
}
