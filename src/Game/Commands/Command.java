package Game.Commands;

import Game.EntityFactory.Entity;

public interface Command
{
    void execute(Entity entity);
}