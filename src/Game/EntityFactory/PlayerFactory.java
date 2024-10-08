package Game.EntityFactory;

import Game.Main.KeyHandler;
import Game.Main.MouseHandler;

public class PlayerFactory implements EntityFactory
{
    @Override
    public Player createEntity(String name, Stats stats, int nr)
    {
        return new Player(name, stats, nr);
    }
}
