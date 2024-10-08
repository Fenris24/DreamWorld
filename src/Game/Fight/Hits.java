package Game.Fight;

import Game.EntityFactory.Entity;
import Game.Manager.CollisionBox;
import Game.Strategies.CollisionCheckerStrategy;
import Game.Strategies.EntityCollisionChecker;


public class Hits
{
    public static boolean attacks(Entity a, CollisionBox dmgArea, Entity b, int centering)
    {
        if(dmgArea.getName().equals("null"))
        {
            return false;
        }
        CollisionCheckerStrategy entityCollision = new EntityCollisionChecker();
        return entityCollision.checkCollision(dmgArea, a.getWorldX() - centering, a.getWorldY(), b.getHitBox(), b.getWorldX(), b.getWorldY());
    }
}
