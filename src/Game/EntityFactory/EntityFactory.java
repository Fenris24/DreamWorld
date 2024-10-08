package Game.EntityFactory;

import Game.Main.KeyHandler;
import Game.Main.MouseHandler;

public interface EntityFactory
{
    //based
    Entity createEntity(String name, Stats stats, int nr);
}
