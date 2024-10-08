package Game.EntityFactory;

public class DarklingFactory implements EntityFactory
{
    public Darkling createEntity(String name, Stats stats, int nr)
    {
        return new Darkling(name, stats, nr);
    }
}
