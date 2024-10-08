package Game.MapFactory;

import Game.Manager.AssetManager;
import Game.Manager.Const;
import Game.Manager.Tile;
import Game.Parsers.MapParser;

import java.awt.*;

public class AncientForest extends MasterMap
{
    Tile[] Layer1;
    Tile[] Layer2;
    Tile[] Layer3;
    public AncientForest(MapParser.MapHandler handle)
    {
        super(handle);
        Layer1 = AssetManager.getInstance().getTiles("Ancient Forest");
        Layer2 = AssetManager.getInstance().getTiles("Trees");
        Layer3 = AssetManager.getInstance().getTiles("TreeWall");
    }
    public void draw1(Graphics2D g2d, int entityX, int entityY)
    {
        int tile_Size = 48;
        for(int worldRow = 0; worldRow < mapHeight; worldRow++)
        {
            for(int worldCol = 0; worldCol < mapWidth; worldCol++)
            {
                int worldX = worldCol * Const.tileSize;
                int worldY = worldRow * Const.tileSize;
                int screenX = worldX - entityX + Const.centerX;
                int screenY = worldY - entityY + Const.centerY;
                if (insideWindow(worldX, worldY, entityX, entityY))
                {
                    DrawLayer(g2d, Layer1, mapMatrix[0], 0, worldRow, worldCol, screenX, screenY);
                    DrawLayer(g2d, Layer2, mapMatrix[1], 256, worldRow, worldCol, screenX, screenY);
                    DrawLayer(g2d, Layer1, mapMatrix[2], 0, worldRow, worldCol, screenX, screenY);
                }
            }
        }
    }
    public void draw2(Graphics2D g2d, int entityX, int entityY)
    {
        for(int worldRow = 0; worldRow < mapHeight; worldRow++)
        {
            for(int worldCol = 0; worldCol < mapWidth; worldCol++)
            {
                int worldX = worldCol * Const.tileSize;
                int worldY = worldRow * Const.tileSize;
                int screenX = worldX - entityX + Const.centerX;
                int screenY = worldY - entityY + Const.centerY;
                if (insideWindow(worldX, worldY, entityX, entityY))
                {
                    DrawLayer(g2d, Layer3, mapMatrix[3], 2706, worldRow, worldCol, screenX, screenY);
                    DrawLayer(g2d, Layer3, mapMatrix[4], 2706, worldRow, worldCol, screenX, screenY);
                    /*
                    if(mapMatrix[0][worldRow][worldCol] > 0)
                    {
                        //Layer1[super.mapMatrix[0][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                        //Layer1[mapMatrix[0][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                    }
                    if(mapMatrix[2][worldRow][worldCol] > 0)
                    {
                        //Layer1[super.mapMatrix[0][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                        //Layer1[mapMatrix[2][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                    }*/
                }
            }
        }
    }
}
