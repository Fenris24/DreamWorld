package Game.Strategies;

import Game.EntityFactory.Entity;

public class GoblinVision implements VisionStrategy
{
    @Override
    public boolean checkVision(Entity a, Entity b)
    {
        boolean inSight = a.getEvent().inSight;
        int bL = b.getHitBox().getTrueX(0, b.getWorldX()); // B's LEFT side
        int bR = b.getHitBox().getTrueX(1, b.getWorldX()); // B's RIGHT side
        int bM = b.getHitBox().getTrueY(0, b.getWorldY()) + b.getHitBox().getHeight() / 2; // B's Middle on Y axis
        int aL = a.getHitBox().getTrueX(0, a.getWorldX()); // A's LEFT side
        int aR = a.getHitBox().getTrueX(1, a.getWorldX()); // A's RIGHT side
        int aM = a.getHitBox().getTrueY(0, a.getWorldY()) + a.getHitBox().getHeight() / 2; // B's Middle on Y axis
        int aW = a.getHitBox().getWidth(); // A's width
        int aDir = a.getEvent().orientation;
        if(aDir == 1 && (bL >= aR - aW) && (bL <= aR - aW + a.getVisionX()) && (bM >= aM - a.getVisionY()) && (bM <= aM + a.getVisionY()))
        {
            inSight = true;
        }
        else if(aDir == 2 && (bR >= aL - a.getVisionX()) && (bR <= aL + aW) && (bM >= aM - a.getVisionY()) && (bM <= aM + a.getVisionY()))
        {
            inSight = true;
        }
        else
        {
            if(inSight && aDir == 2 && (bR >= aR - aW) && (bR <= aR + a.getVisionX()) && (bM >= aM - a.getVisionY()) && (bM <= aM + a.getVisionY()))
            {
                a.getEvent().orientation = 1;
            }
            else if(inSight && aDir == 1 && (bL >= aL - a.getVisionX()) && (bL <= aL + aW ) && (bM >= aM - a.getVisionY()) && (bM <= aM + a.getVisionY()))
            {
                a.getEvent().orientation = 2;
            }
            else
            {
                inSight = false;
            }
        }
        return inSight;
    }
}
