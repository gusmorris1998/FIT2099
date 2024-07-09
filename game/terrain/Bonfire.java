package game.terrain;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.actions.RestAction;
import game.actions.LightAction;
import game.actions.TeleportAction;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;

public class Bonfire extends Ground {
    /**
     * The bonfire's name
     */
    private String bonfireName;

    /**
     * Constructor
     */
    public Bonfire(){
        super('B');
    }

    /**
     * Constructor
     * @param bonfireName Bonfire's name
     * @param lit Boolean value which determines if the bonfire requires the LightAction
     */
    public Bonfire(String bonfireName, boolean lit){
        super('B');
        this.bonfireName = bonfireName;
        if (lit){
            this.addCapability(Status.LIT);
        }

    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return An Actions list containing a LightAction if the Bonfire is not lit, otherwise
     *         return an Actions list containing a RestAction and TeleportAction(s)
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        // If actor is on the Bonfire, and it is the player
        if (actor == location.getActor() && actor instanceof Player) {
            Player player = (Player) actor;

            // If Bonfire is not lit, provide LightAction
            if (!this.hasCapability(Status.LIT)) {
                actions.add(new LightAction(direction, location));
            }

            // If Bonfire is lit and the actor has the capability to rest, provide the RestAction and TeleportAction
            if (actor.hasCapability(Abilities.REST) && this.hasCapability(Status.LIT)) {
                actions.add(new RestAction(direction, location));

                ArrayList<Location> bonfireLocations = player.getBonfireLocations();;

                // Iterate through the player's bonfireLocations array
                for(int i = 0; i < bonfireLocations.size(); i++){
                    Location bonfireLocation = bonfireLocations.get(i);

                    // If the bonfireLocation is not the current Bonfire
                    if (bonfireLocation != location){
                        Bonfire bonfire = (Bonfire) bonfireLocation.getGround();
                        bonfire.getBonfireName();
                        actions.add(new TeleportAction(bonfireLocation,bonfire.getBonfireName() + "'s Bonfire"));
                    }
                }
            }

        }
        return actions;
    }

    public String getBonfireName() {
        return bonfireName;
    }
}
