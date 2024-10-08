package Game.MapFactory;

import Game.Parsers.MapParser;

public class BossRoomFactory implements MapFactory
{
    public BossRoom createMap(MapParser.MapHandler handle)
    {
        return new BossRoom(handle);
    }
}
