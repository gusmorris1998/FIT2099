package edu.monash.fit2099.demo.conwayslife;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Tree());
		
		List<String> map = Arrays.asList(
		".......................................................6.........................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"......................................++........................................",
		".....................................++.........................................",
		"......................................+.........................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new ConwayGameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(10, 10));
			
		world.run();
	}
}
