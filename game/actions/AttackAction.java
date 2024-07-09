package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.items.CindersOfLord;
import game.Player;
import game.items.Wallet;
import game.enemies.Enemy;
import game.enemies.LordOfCinder;
import game.enemies.Mimic;
import game.enums.Abilities;
import game.interfaces.EnemyDetails;
import game.interfaces.DetailsOfWeapons;
import game.interfaces.Money;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		/**
		 * This method executes the attack action on a given target
		 * @param actor = the actor that performs the attack,map = current state of the map
		 * @return result which is a string that outputs the outcome of the attack. This could be whether the
		 * target is attacked , killed or whether the actor itself has been killed.
		 *
		 */
		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		target.hurt(damage);
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
//		Target dies and is not the player
		if (!target.isConscious()) {
			//Checks if the target that has died is a mimic and then drops the tokens
			if (target instanceof Mimic){
				DropTokens drop = new DropTokens();
				drop.DropTokens(map.locationOf(target));
			}

			if (!(target instanceof Player)) {
				Actions dropActions = new Actions();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);

				//transfers souls into the players wallet if the player has killed the enemy
				if (actor instanceof Player && target instanceof Enemy) {
					Money player = ((Money) actor);
					EnemyDetails enemy = (EnemyDetails) this.target;
					Wallet wallet = player.getWallet();
					wallet.addSouls(enemy.getSouls());
					player.setWallet(wallet);
				}

				//if the player has managed to kill a lord of cinder, Cinder of Lord is dropped
				if (actor instanceof Player && target instanceof LordOfCinder) {
					// loop through all inventory
					for (Item item : target.getInventory()){
						if (item instanceof WeaponItem){
								DetailsOfWeapons enemyWeapon = (DetailsOfWeapons)item;
								CindersOfLord cinders = new CindersOfLord(enemyWeapon.getWeapon());

								if (map.locationOf(actor).getGround().hasCapability(Abilities.FALL)) {
										map.locationOf(actor).addItem(cinders);
									}
								else {
										map.locationOf(actor).addItem(cinders);
									}
								break;
							}

						}
					}

			}


			result += System.lineSeparator() + target + " is killed.";


			//adding money to players wallet after a player has killed an enemy by using the money interface which is implemented by the player class

		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		/**
		 * Shows the option to execute an attack action in the menu
		 * @param actor
		 * @return string saying what the player can do
		 */
		if(target instanceof Enemy){
			EnemyDetails enemy = (EnemyDetails)target;
			if (target.getWeapon() instanceof IntrinsicWeapon){
				return actor + " attacks " + target + " at " + direction + "      Enemy HP : " + enemy.getHP() + "   Enemy Weapon : Fist";
			}
			else return actor + " attacks " + target + " at " + direction + "      Enemy HP : " + enemy.getHP() + "   Enemy Weapon : " + target.getWeapon().toString();
		}
		return actor + " attacks " + target + " at " + direction;
	}
}
