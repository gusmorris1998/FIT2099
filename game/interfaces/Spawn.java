package game.interfaces;

import edu.monash.fit2099.engine.Location;

public interface Spawn {
    /**
     *Given a apecific location, this method sets the spawn location of a given actor that implements this method.
     */
    public void setSpawnLocation(Location spawnLocation) ;
}
