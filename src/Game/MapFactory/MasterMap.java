package Game.MapFactory;

import Game.Manager.Const;
import Game.Manager.Tile;
import Game.Parsers.MapParser;

import java.awt.*;

public abstract class MasterMap
{
    protected int mapHeight;
    protected int mapWidth;
    protected int nrLayers;
    protected int [][][]mapMatrix;
    public MasterMap(MapParser.MapHandler handle)
    {
        this.nrLayers = handle.nrLayers;
        this.mapHeight = handle.mapHeight;
        this.mapWidth = handle.mapWidth;
        this.mapMatrix = handle.LayerMatrix;
    }

    public void DrawLayer(Graphics2D g2d, Tile[]TilesSet, int [][]layer, int lim, int row, int col, int pX, int pY)
    {
        if(layer[row][col] > lim)
        {
            try
            {
                g2d.drawImage(TilesSet[layer[row][col] - (lim + 1)].getImage(), pX, pY, null);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println(row + " " + col +  " " + layer[row][col]);
            }
        }
    }
    public boolean insideWindow(int worldX, int worldY, int entityX, int entityY)
    {
        return worldX + Const.tileSize > entityX - Const.centerX &&
                worldX  - Const.tileSize * 2 < entityX + Const.centerX &&
                worldY + Const.tileSize * 2 > entityY - Const.centerY &&
                worldY - Const.tileSize * 4 < entityY+ Const.centerY;
    }
    public int getMapHeight()
    {
        return mapHeight;
    }
    public int getMapWidth()
    {
        return mapWidth;
    }
    public int[][] getLayer(int l)
    {
        return mapMatrix[l];
    }
}
