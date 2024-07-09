package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.ResetManager;
import game.interfaces.Spawn;
import game.terrain.Bonfire;

public class RestAction extends Action {

    private String direction;
    private Location bonfireLocation;

    public RestAction(String direction, Location bonfireLocation){
        this.direction = direction;
        this.bonfireLocation = bonfireLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map){
        Spawn player = ((Spawn)actor) ;
        player.setSpawnLocation(bonfireLocation);
        ResetManager.getInstance().run();

        Bonfire bonfire = (Bonfire) bonfireLocation.getGround();
        return actor + " rests at " + bonfire.getBonfireName() + "'s Bonfire";
    }

    @Override
    public String menuDescription(Actor actor) {
        Bonfire bonfire = (Bonfire) bonfireLocation.getGround();
        return "Rest at " + bonfire.getBonfireName() + "'s Bonfire";
    }
}
