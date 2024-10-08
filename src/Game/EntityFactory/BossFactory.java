package Game.EntityFactory;

public class BossFactory implements EntityFactory
{
    public Boss createEntity(String name, Stats stats, int nr)
    {
        return new Boss(name, stats, nr);
    }

}
