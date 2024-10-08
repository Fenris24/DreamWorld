package Game.EntityFactory;

import Game.Animations.Animation;
import Game.Commands.GameController;
import Game.Events.Event;
import Game.Events.Observer;
import Game.Fight.SpellEffects;
import Game.Manager.CollisionBox;
import Game.Manager.Const;
import Game.Strategies.CollisionChecker;
import Game.Strategies.CollisionCheckerStrategy;
import Game.Strategies.EntityCollisionChecker;

import java.awt.*;


public abstract class Entity
{
    protected Event event;
    protected SpellEffects effects;
    protected int worldX, worldY;
    protected int visionX;
    protected int visionY;
    protected String name;
    protected Stats stats;
    GameController commands;
    CollisionBox hitbox;
    CollisionChecker collisionChecker;
    boolean collision;

    public Entity(String name, Stats stats, int nr)
    {
        this.name = name;
        this.stats = stats;
        event = new Event();
        commands = new GameController();

        CollisionCheckerStrategy entityCollision = new EntityCollisionChecker();
        collisionChecker = new CollisionChecker(entityCollision);
    }

    public Event getEvent()
    {
        return event;
    }

    public int getVisionX()
    {
        return visionX;
    }
    public int getVisionY()
    {
        return visionY;
    }
    public int getWorldX() {return worldX; }
    public int getWorldY() {return worldY; }
    public void setWorldX(int dist) {worldX = dist;}
    public void setWorldY(int dist) {worldY = dist;}

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setStats(Stats stats)
    {
        this.stats = stats;
    }
    public Stats getStats()
    {
        return stats;
    }
    public CollisionBox getHitBox()
    {
        return hitbox;
    }

    public SpellEffects getEffects() {return effects;}

    public void move()
    {
        event.isMoving = true;
    }
    public void stop()
    {
        event.isMoving = false;
        event.isRunning = false;
    }
    public void moveToPlayer(Player p)
    {
        if(hitbox.getTrueX(1, worldX) <= p.getHitBox().getTrueX(0, p.getWorldX()))
        {
            event.orientation = 1;
            commands.moveRight(this);
        }
        else if(hitbox.getTrueX(0, worldX) >= p.getHitBox().getTrueX(1, p.getWorldX()))
        {
            event.orientation = 2;
            commands.moveLeft(this);
        }
        if(hitbox.getTrueY(0, worldY) < p.getHitBox().getTrueY(0, p.getWorldY()))
        {
            commands.moveDown(this);
        }
        else if(hitbox.getTrueY(2, worldY) > p.getHitBox().getTrueY(2, p.getWorldY()))
        {
            commands.moveUp(this);
        }
    }
    public void moveUp(int speed)
    {
        Observer o = Observer.getInstance();
        worldY -= speed;
        boolean collision1, collision2, collision3;
        collision1 = collisionChecker.checkCollision(this, 0);
        collision2 = collisionChecker.checkCollision(this, 1);
        if(o.currentLvl != 3)
        {
            collision3 = collisionChecker.checkCollision(this, 2);
        }
        else
        {
            collision3 = false;
        }
        if(collision1 || collision2 || collision3)
        {
            worldY += speed;
        }
    }
    public void moveDown(int speed)
    {
        Observer o = Observer.getInstance();
        worldY += speed;
        boolean collision1, collision2, collision3;
        collision1 = collisionChecker.checkCollision(this, 0);
        collision2 = collisionChecker.checkCollision(this, 1);
        if(o.currentLvl != 3)
        {
            collision3 = collisionChecker.checkCollision(this, 2);
        }
        else
        {
            collision3 = false;
        }
        if(collision1 || collision2 || collision3)
        {
            worldY -= speed;
        }
    }
    public void moveLeft(int speed)
    {
        Observer o = Observer.getInstance();
        worldX -= speed;
        boolean collision1, collision2, collision3;
        collision1 = collisionChecker.checkCollision(this, 0);
        collision2 = collisionChecker.checkCollision(this, 1);
        if(o.currentLvl != 3)
        {
            collision3 = collisionChecker.checkCollision(this, 2);
        }
        else
        {
            collision3 = false;
        }
        if(collision1 || collision2 || collision3)
        {
            worldX += speed;
        }
    }
    public void moveRight(int speed)
    {
        Observer o = Observer.getInstance();
        worldX += speed;
        boolean collision1, collision2, collision3;
        collision1 = collisionChecker.checkCollision(this, 0);
        collision2 = collisionChecker.checkCollision(this, 1);
        if(o.currentLvl != 3)
        {
            collision3 = collisionChecker.checkCollision(this, 2);
        }
        else
        {
            collision3 = false;
        }
        if(collision1 || collision2 || collision3)
        {
            worldX -= speed;
        }
    }
    public GameController getGameController()
    {
        return commands;
    }
    public int calcDistance(Entity b)
    {
        int xA, yA;
        int xB, yB;

        if(event.orientation == 1)
        {
            xA = hitbox.getTrueX(1, worldX);
            yA = hitbox.getTrueY(1, worldY);
            xB = b.getHitBox().getTrueX(0, b.getWorldX());
            yB = b.getHitBox().getTrueY(0, b.getWorldY());
        }
        else
        {
            xA = hitbox.getTrueX(0, worldX);
            yA = hitbox.getTrueY(0, worldY);
            xB = b.getHitBox().getTrueX(1, b.getWorldX());
            yB = b.getHitBox().getTrueY(1, b.getWorldY());
        }

        int t1 = (xB - xA) * (xB - xA);
        int t2 = (yB - yA) * (yB - yA);
        double t3 = t1 + t2;
        return (int)Math.sqrt(t3);
    }
    public void drawDistance(Graphics2D g2d, Entity b)
    {
        int xA, yA;
        int xB, yB;
        int screenX = worldX - b.getWorldX() + Const.centerX;
        int screenY = worldY - b.getWorldY() + Const.centerY;
        if(event.orientation == 1)
        {
            xA = hitbox.getTrueX(1, screenX);
            yA = hitbox.getTrueY(1, screenY);
            xB = b.getHitBox().getTrueX(0, Const.centerX);
            yB = b.getHitBox().getTrueY(0, Const.centerY);
        }
        else
        {
            xA = hitbox.getTrueX(0, screenX);
            yA = hitbox.getTrueY(0, screenY);
            xB = b.getHitBox().getTrueX(1, Const.centerX);
            yB = b.getHitBox().getTrueY(1, Const.centerY);
        }
        g2d.drawLine(xA, yA, xB, yB);
    }
}