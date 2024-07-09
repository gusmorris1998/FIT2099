package game.items;

import edu.monash.fit2099.engine.*;
import game.interfaces.Resettable;

import java.lang.Math;
public class EstusFlask extends Action implements Resettable {
    /**
     *This class carries out all the duties that related to the Estus flask in the game. This includes
     * healing the player as well as managing the charges . The object also provides the action to the user to
     * use the flask extending the action class. This object is resettable after the player is dead or rests at the bonfire.
     */


    // intialising the number of total charges and availble charges integers.
    public int availablecharges;
    public int totalCharges;
    public int maxHP;

    public EstusFlask(int maxHP) {
        /**
         * Constructor
         * @param maxHP
         */
        this.totalCharges = 3;
        this.availablecharges = this.totalCharges;
        this.maxHP = maxHP;
        this.registerInstance();

    }


    public String execute(Actor actor, GameMap map) {
        /**
         * Performs the action of consuming one charge of the flask.
         * @param actor,map
         * @returns a string output on hte outcome of the task when it is performed
         */

        // checking whether any flasks are available

        if (this.availablecharges > 0) {

            //performing healing
            actor.heal((int) Math.round(0.4 * this.maxHP));
            this.availablecharges = this.availablecharges - 1;
            return actor + "is healed";
        }
        //returns message if no flasks are available
        else
            return "No more charges available";
    }


    public String menuDescription(Actor actor) {
        /**
         * method that outputs the option of an actor performing the action of consuming the flask.
         *
         * @param actor
         * @return string saying how many flasks are available and that they can be consumed.
         */
        return actor + " consumes estus flask " + this.availablecharges + "/" + this.totalCharges;
    }

    public String hotkey() {
        /**
         * returns the hotkey that performs the execute action
         *
         */
        return "e";
    }

    @Override
    public void resetInstance(){
        /**
         * restets any instance of the class by making reverting the number of avialble charges.
         */
        this.availablecharges = this.totalCharges;

    }
    @Override
    public boolean isExist(){
        return true;

    }
}
