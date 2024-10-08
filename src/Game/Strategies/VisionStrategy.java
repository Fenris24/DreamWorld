package Game.Strategies;

import Game.EntityFactory.Entity;

public interface VisionStrategy
{
    boolean checkVision(Entity a, Entity b);
}
