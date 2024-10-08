package Game.MapFactory;

import Game.Parsers.MapParser;

public interface MapFactory
{
    MasterMap createMap(MapParser.MapHandler handle);
}
