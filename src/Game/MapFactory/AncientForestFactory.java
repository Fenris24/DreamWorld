package Game.MapFactory;

import Game.Parsers.MapParser;

public class AncientForestFactory implements MapFactory
{
    public AncientForest createMap(MapParser.MapHandler handle)
    {
        return new AncientForest(handle);
    }
}
