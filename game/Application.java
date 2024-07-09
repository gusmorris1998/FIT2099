package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.enemies.AldrichTheDevourer;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
import game.items.FogDoor;
import game.terrain.*;
import game.weapons.StormRulerWeapon;


/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery(),new Bonfire(),new Vendor(), new Fire(),new Chest());

			List<String> profaneCapitalMap = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"....c......+++.......................................................+++++......",
					"........................................................................++......",
					"................................................................c........+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#V______#......................+++............",
					"...........++.....................#_______#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.........c..............?........................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++.........c.",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+.................c.............+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");


			GameMap profaneCapital = new GameMap(groundFactory, profaneCapitalMap);
			world.addGameMap(profaneCapital);


		List<String> anorLondoMap = Arrays.asList(
				"..................................................",
				"..................................................",
				"....c................++................+..........",
				"..................................................",
				"..................................................",
				"......................................++..........",
				"..........##___####################...............",
				"..........#.........._......#.....#........c......",
				"..........#.......................#...............",
				"..........#...#............#_.....#...............",
				"....c.....#.........#...._.......#.........+......",
				"..........#.......................#...............",
				"..........##___####################...............",
				"..................................................",
				"...+..............................................");

			GameMap anorLondo =  new GameMap(groundFactory,anorLondoMap);
			world.addGameMap(anorLondo);

			FogDoor profaneDoor = new FogDoor();
			profaneDoor.addAction(new MoveActorAction(anorLondo.at(10,0),"to Anor Londo"));
			profaneCapital.at(38,25).addItem(profaneDoor);

			FogDoor anorDoor = new FogDoor();
			anorDoor.addAction(new MoveActorAction(profaneCapital.at(38,25),"to Profane Capital"));
			anorLondo.at(10,0).addItem(anorDoor);
			anorLondo.at(20, 9).addActor(new AldrichTheDevourer());


			profaneCapital.at(38,11).setGround(new Bonfire("Firelink Shrine",true));
			anorLondo.at(18,0).setGround(new Bonfire("Anor Londo",false));



			Location playerSpawn = profaneCapital.at(38,12);
			Player player = new Player("Unkindled (Player)", '@', 100, playerSpawn);
			world.addPlayer(player, profaneCapital.at(38, 12));
			//world.addPlayer(player, anorLondo.at(17, 9));

			player.getBonfireLocations().add(profaneCapital.at(38,11));

			// Place Yhorm the Giant/boss in the map
			profaneCapital.at(6, 25).addActor(new YhormTheGiant("Yhorm the Giant", 'Y', 500));
			profaneCapital.at(7, 25).addItem(new StormRulerWeapon());

			// Place 4 - 12 skeletons manually on the map
			profaneCapital.at(11, 19).addActor(new Skeleton("Skeleton", profaneCapital.at(11, 24)));
			profaneCapital.at(32, 7).addActor(new Skeleton("Skeleton", profaneCapital.at(32, 7)));
			profaneCapital.at(44, 22).addActor(new Skeleton("Skeleton", profaneCapital.at(44, 22)));
			profaneCapital.at(32, 24).addActor(new Skeleton("Skeleton", profaneCapital.at(37, 24)));
			profaneCapital.at(16, 3).addActor(new Skeleton("Skeleton", profaneCapital.at(16, 3)));
			profaneCapital.at(3, 16).addActor(new Skeleton("Skeleton", profaneCapital.at(3, 16)));
			profaneCapital.at(22, 16).addActor(new Skeleton("Skeleton", profaneCapital.at(22, 16)));
			profaneCapital.at(43, 16).addActor(new Skeleton("Skeleton", profaneCapital.at(43, 16)));


			world.run();

	}
}
