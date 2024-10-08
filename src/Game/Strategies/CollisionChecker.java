package Game.Strategies;

import Game.EntityFactory.Entity;
import Game.Manager.CollisionBox;
import Game.Manager.Tile;
import Game.MapFactory.MasterMap;

public class CollisionChecker
{
    private CollisionCheckerStrategy strategy;

    public CollisionChecker(CollisionCheckerStrategy strategy)
    {
        this.strategy = strategy;
    }
    public boolean checkCollision(Entity entity, int layer)
    {
        return strategy.checkCollision(entity, layer);
    }
    public boolean checkCollision(CollisionBox A, int pXa, int pYa, CollisionBox B, int pXb, int pYb)
    {
        return strategy.checkCollision(A, pXa, pYa, B, pXb, pYb);
    }
}