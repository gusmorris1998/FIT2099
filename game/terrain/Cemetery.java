package game.terrain;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;

import java.util.List;
import java.util.Random;

public class Cemetery extends Ground {

    public Cemetery() {
        super('c');
    }

    /**
     * Tick method to have a chance to spawn an Undead every turn.
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        //All the locations around Cemetery(exits)
        List<Exit> exits = location.getExits();
        Random rand = new Random();
        int randomNumberOne = rand.nextInt(100);
        //25% chance to enter if statement
        int spawnChance = 25;
        if (randomNumberOne <= spawnChance) {
            //Picks at random an exit location from the available
            int randomNumberTwo = rand.nextInt(exits.size());
            Location spawn = exits.get(randomNumberTwo).getDestination();
            //Checks if actor can be placed in the randomly selected exit
            if (spawn.canActorEnter(new Undead("Undead"))) {
                spawn.addActor(new Undead("Undead"));
            }
        }
    }
}

