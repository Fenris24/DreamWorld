package Game.MapFactory;

import Game.Parsers.MapParser;

public class DarkDimensionFactory implements MapFactory
{
    public DarkDimension createMap(MapParser.MapHandler handle)
    {
        return new DarkDimension(handle);
    }

}
