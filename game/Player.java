package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.*;
import game.items.CindersOfLord;
import game.items.EstusFlask;
import game.items.TokenOfSouls;
import game.items.Wallet;
import game.weapons.BroadswordWeapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable, Money, Spawn {
    /**
     * The player's last location
     */
    private Location lastLocation;
    /**
     * The player's spawn location
     */
    private Location spawnLocation;

    /**
     * Array List of locations where the player can teleport to
     */
    private ArrayList<Location> bonfireLocations;

    private final Menu menu = new Menu();
    public EstusFlask flask = new EstusFlask(this.maxHitPoints);
    public Wallet wallet = new Wallet(100000);


    /**
     * Constructor.
     *
     * @param name          Name to call the player in the UI
     * @param displayChar   Character to represent the player in the UI
     * @param hitPoints     Player's starting number of hitpoints
     * @param spawnLocation Where the player will spawn when they die
     */
    public Player(String name, char displayChar, int hitPoints, Location spawnLocation) {

        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Abilities.REST);
        this.spawnLocation = spawnLocation;
        this.lastLocation = spawnLocation;
        this.registerInstance();
        this.bonfireLocations = new ArrayList<Location>();
        inventory.add(new BroadswordWeapon());
    }


    public void setSpawnLocation(Location spawnLocation) {
        /**
         * Given a location, this method sets the spawn location attribute to the given location.
         * @param spawnLocation
         */
        this.spawnLocation = spawnLocation;
    }


    public Location getLastLocation() {
        return lastLocation;
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        /**
         * This method is resposible for showing a player their potential actions and
         * how they can perform them
         *
         * @param actions,lastAction,map,display
         *
         * @return Action that is chosen by the player
         */

        //printing the inventory
        List<Item> inventory = this.getInventory();
        if (inventory.size() != 0) {
            display.println("Item in Inventory : \n");
            for (Item item : inventory) {
                if (item instanceof CindersOfLord) {
                    TradeWithWeapon weapon = (TradeWithWeapon) item;
                    display.println("* " + item.toString() + " can be traded to gain " + weapon.getWeapon().toString() + "\n");
                } else display.println("* " + item.toString() + "\n");
            }
        } else display.println("No items in inventory");


        // If the player is dead
        if (!this.isConscious()) {
            //Reset procedure takes place
            display.println("YOU DIED");
            ResetManager.getInstance().run();
            //the current souls are dropped as a token
            TokenOfSouls tokenOfSouls = new TokenOfSouls(wallet.totalSouls);
            // token is added to the map.
            if (map.locationOf(this).getGround().hasCapability(Abilities.FALL)) {
                lastLocation.addItem(tokenOfSouls);
            } else {
                map.locationOf(this).addItem(tokenOfSouls);
            }
            // moves actor to the spawn location
            map.moveActor(this, spawnLocation);
            wallet.totalSouls = 0;
            return new DoNothingAction();
        }


        // Print current HP
        display.println(this.name + " (" + (this.hitPoints) + "/" + this.maxHitPoints + ")");

        // Print current souls
        display.println("You have a total of " + this.wallet.totalSouls + " souls");

        // Update the last location of player
        lastLocation = map.locationOf(this);

        // adds the Estus flask actions to the current list of actions.
        actions.add(flask);
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        return menu.showMenu(this, actions, display);
    }

    @Override
    public void resetInstance() {
        /**
         * resets the HP to max
         */
        this.hitPoints = this.maxHitPoints;

    }

    @Override
    public boolean isExist() {
        return true;
    }


    @Override
    public Wallet getWallet() {
        /**
         * getter for player wallet
         */
        return this.wallet;
    }

    @Override
    public void setWallet(Wallet wallet) {
        /**
         * setter for player wallet
         */
        this.wallet = wallet;
    }

    public ArrayList<Location> getBonfireLocations() {
        return bonfireLocations;
    }
}
