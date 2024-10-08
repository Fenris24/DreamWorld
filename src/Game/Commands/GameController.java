package Game.Commands;

import Game.EntityFactory.Entity;

public class GameController
{
    private Command moveUpCommand;
    private Command moveDownCommand;
    private Command moveLeftCommand;
    private Command moveRightCommand;
    public GameController()
    {
        setMoveCommands();
    }

    public void setMoveUpCommand(Command command)
    {
        this.moveUpCommand = command;
    }

    public void setMoveDownCommand(Command command)
    {
        this.moveDownCommand = command;
    }

    public void setMoveLeftCommand(Command command)
    {
        this.moveLeftCommand = command;
    }

    public void setMoveRightCommand(Command command)
    {
        this.moveRightCommand = command;
    }

    public void moveUp(Entity e) { this.moveUpCommand.execute(e); }

    public void moveDown(Entity e)
    {
        this.moveDownCommand.execute(e);
    }

    public void moveLeft(Entity e)
    {
        this.moveLeftCommand.execute(e);
    }

    public void moveRight(Entity e)
    {
        this.moveRightCommand.execute(e);
    }
    public void setMoveCommands()
    {
        this.setMoveUpCommand(new MoveCommand(Direction.UP));
        this.setMoveDownCommand(new MoveCommand(Direction.DOWN));
        this.setMoveLeftCommand(new MoveCommand(Direction.LEFT));
        this.setMoveRightCommand(new MoveCommand(Direction.RIGHT));
    }
}
