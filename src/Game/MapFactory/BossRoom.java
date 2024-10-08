package Game.MapFactory;

import Game.Manager.AssetManager;
import Game.Manager.Const;
import Game.Manager.Tile;
import Game.Parsers.MapParser;

import java.awt.*;

public class BossRoom extends MasterMap
{
    Tile[] Layer;
    public BossRoom(MapParser.MapHandler handle)
    {
        super(handle);
        Layer = AssetManager.getInstance().getTiles("BossRoom");
    }
    public void draw(Graphics2D g2d, int entityX, int entityY)
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
                    DrawLayer(g2d, Layer, mapMatrix[0], 0, worldRow, worldCol, screenX, screenY);
                    DrawLayer(g2d, Layer, mapMatrix[1], 0, worldRow, worldCol, screenX, screenY);
                    /*
                    if(mapMatrix[0][worldRow][worldCol] > 0)
                    {
                        //Layer1[super.mapMatrix[0][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                        Layer[mapMatrix[0][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                    }
                    if(mapMatrix[1][worldRow][worldCol] > 0)
                    {
                        //Layer1[super.mapMatrix[0][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                        Layer[mapMatrix[1][worldRow][worldCol] - 1].getBox().drawCollision(g2d, screenX, screenY);
                    }*/
                }
            }
        }
    }
}
