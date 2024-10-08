package Game.Commands;

import Game.EntityFactory.Entity;

public class MoveCommand implements Command
{
    private Direction dir;

    public MoveCommand(Direction dir)
    {
        this.dir = dir;
    }

    @Override
    public void execute(Entity entity)
    {
        entity.move();
        int speed = entity.getStats().getMS();
        switch (dir)
        {
            case UP:
                entity.moveUp(speed);
                break;
            case DOWN:
                entity.moveDown(speed);
                break;
            case LEFT:
                entity.moveLeft(speed);
                break;
            case RIGHT:
                entity.moveRight(speed);
                break;
            default:
                break;
        }
    }
}

