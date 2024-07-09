package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.terrain.Bonfire;
import game.Player;
import game.enums.Status;

public class LightAction extends Action {

    private String direction;
    private Location bonfireLocation;

    public LightAction(String direction, Location bonfireLocation) {
        this.direction = direction;
        this.bonfireLocation = bonfireLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;

        player.getBonfireLocations().add(bonfireLocation);
        player.setSpawnLocation(bonfireLocation);
        bonfireLocation.getGround().addCapability(Status.LIT);

        Bonfire bonfire = (Bonfire) bonfireLocation.getGround();
        return bonfire.getBonfireName() + "'s Bonfire lit";
    }

    @Override
    public String menuDescription(Actor actor) {
        Bonfire bonfire = (Bonfire) bonfireLocation.getGround();
        return "Light " + bonfire.getBonfireName() + "'s bonfire";
    }
}
