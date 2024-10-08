package Game.EntityFactory;

import Game.Main.KeyHandler;
import Game.Main.MouseHandler;

public class GoblinFactory implements EntityFactory
{
    @Override
    public Goblin createEntity(String name, Stats stats, int nr)
    {
        return new Goblin(name, stats, nr);
    }
}
