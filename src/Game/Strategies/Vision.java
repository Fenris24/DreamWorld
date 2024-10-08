package Game.Strategies;

import Game.EntityFactory.Entity;

public class Vision
{
    private VisionStrategy strategy;

    public Vision(VisionStrategy strategy)
    {
        this.strategy = strategy;
    }
    public boolean inSight(Entity a, Entity b)
    {
        return strategy.checkVision(a, b);
    }
}
