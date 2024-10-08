package Game.Events;

import Game.EntityFactory.Boss;
import Game.EntityFactory.Entity;
import Game.EntityFactory.EntityFactory;
import Game.EntityFactory.Player;
import Game.Manager.AssetManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Observer
{
    private static Observer instance = null;

    public int nrEnt = 0;
    public int currentLvl = 1;
    public Player player;
    public Map <Integer, Entity> entity;
    public Entity boss;
    public String currentMap;
    private Observer() throws IOException
    {
        entity = new HashMap<>();
        player = null;
    }
    public static Observer getInstance()
    {
        if(instance == null)
        {
            try
            {
                instance = new Observer();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
    public void addPlayer(Player p)
    {
        player = p;
    }
    public void addEntity(Integer val, Entity e)
    {
        entity.put(val, e);
    }
}
