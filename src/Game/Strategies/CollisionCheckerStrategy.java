package Game.Strategies;

import Game.EntityFactory.Entity;
import Game.Manager.CollisionBox;
import Game.Manager.Tile;
import Game.MapFactory.MasterMap;

public interface CollisionCheckerStrategy
{
    boolean checkCollision(Entity entity, int layer);
    boolean checkCollision(CollisionBox A, int pXa, int pYa, CollisionBox B, int pXb, int pYb);
}
