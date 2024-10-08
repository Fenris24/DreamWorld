package Game.Strategies;

import Game.EntityFactory.Entity;
import Game.Events.Observer;
import Game.Manager.AssetManager;
import Game.Manager.CollisionBox;
import Game.Manager.Const;
import Game.Manager.Tile;
import Game.Parsers.MapParser;

import java.awt.*;

public class EntityCollisionChecker implements CollisionCheckerStrategy
{
    @Override
    public boolean checkCollision(Entity e, int layer)
    {
        Observer o = Observer.getInstance();
        AssetManager asset = AssetManager.getInstance();
        MapParser.MapHandler map = asset.getMap(o.currentMap);
        Tile[] tiles = asset.getTiles(o.currentMap);

        int[][] m = map.LayerMatrix[layer]; //get the layer of the maps which we want to check
        Shape hitBox = e.getHitBox().getShapeInSpace(e.getWorldX(), e.getWorldY()); //get the hitbox of the player in space
        int pozX = (e.getWorldX() / Const.tileSize + 1) % map.mapWidth; //get coordinates behind the entity
        int pozY = (e.getWorldY() / Const.tileSize + 1) % map.mapHeight; //to then check around it
        for (int i = pozY; i < pozY + 4; ++i)
        {
            for (int j = pozX; j < pozX + 3; ++j)
            {
                if (i >= 0 && j >= 0 && m[i][j] > 0)
                {
                    Shape object = tiles[m[i][j] - 1].getBox().getShapeInSpace(j * Const.tileSize, i * Const.tileSize);
                    if (object != null)
                    {
                        if(object.intersects(hitBox.getBounds2D()))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    @Override
    public boolean checkCollision(CollisionBox A, int pXa, int pYa, CollisionBox B, int pXb, int pYb)
    {
        Observer o = Observer.getInstance();
        AssetManager asset = AssetManager.getInstance();
        MapParser.MapHandler map = asset.getMap(o.currentMap);
        Shape hitBoxA = A.getShapeInSpace(pXa, pYa);
        Shape hitBoxB = B.getShapeInSpace(pXb, pYb);
        int pozX = (pXa / Const.tileSize + 1) % map.mapWidth;
        int pozY = (pYa / Const.tileSize + 1) % map.mapHeight;
        for (int i = pozY; i < pozY + 4; ++i)
        {
            for (int j = pozX; j < pozX + 3; ++j)
            {
                if(hitBoxA.intersects(hitBoxB.getBounds2D()))
                {
                    return true;
                }
            }
        }
        return false;
    }
}