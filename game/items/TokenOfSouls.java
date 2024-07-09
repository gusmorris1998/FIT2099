package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.interfaces.Soul;
import game.interfaces.Money;


public class TokenOfSouls extends Item implements Soul {
    /**
     * The number of souls in the TokenOfSouls
     */
    private int souls;

    /**
     * Constructor for TokenOfSouls.
     * @param souls the initial amount of souls contained
     */
    public TokenOfSouls(int souls){
        super("Token Of Souls",'$',true);
        this.souls = souls;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Tick method which transfers the souls in TokenOfSouls to the actor and
     * removes the TokenOfSouls from the actor's inventory
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        Money player = ((Money)actor);
        transferSouls(player.getWallet());
        actor.removeItemFromInventory(this);
    }

    @Override
    public void transferSouls(Soul soulObject){
        soulObject.addSouls(souls);

    }


}
