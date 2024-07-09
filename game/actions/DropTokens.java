package game.actions;

import edu.monash.fit2099.engine.*;
import game.items.TokenOfSouls;

import java.util.List;
import java.util.Random;

/**
 * This class handles the the act of dropping either one two or three tokens that each
 * have a certain amount of souls.
 */
public class DropTokens {

    /**
     * This function takes in a location , and dropes either one two or three tokens next to the given location.
     * The choice is detrmined randomly and the positions asre dtermined using the get exists function
     * @param location
     */
    public void DropTokens(Location location) {
        Random rn = new Random();
        int choice = rn.nextInt(3);
        List<Exit> exits = location.getExits();

        for (int i = 0;i < choice+1;i++){
            exits.get(i).getDestination().addItem(new TokenOfSouls(100));
        }

    }

}
