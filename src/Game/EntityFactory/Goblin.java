package Game.EntityFactory;

import Game.Animations.GoblinAnimations;
import Game.Commands.GameController;
import Game.Events.Observer;
import Game.Fight.Hits;
import Game.Main.KeyHandler;
import Game.Main.MouseHandler;
import Game.Manager.CollisionBox;
import Game.Manager.Const;
import Game.Strategies.*;

import java.awt.*;
import java.sql.PseudoColumnUsage;
import java.util.Random;

public class Goblin extends Entity
{
    Observer observer;
    GoblinAnimations animation;
    private GameController control;
    private Vision vision;
    boolean startTimer;
    public Goblin(String name, Stats stats, int nr)
    {
        super(name, stats, nr);
        hitbox = new CollisionBox("rectangle", Const.tileSize, Const.goblinScale, 20, 10, 30, 30);

        VisionStrategy gobVision = new GoblinVision();
        vision = new Vision(gobVision);
        visionX = Const.goblinVisionX;
        visionY = Const.goblinVisionY;

        worldX = Const.tileSize * 260 + nr;
        worldY = Const.tileSize * 240 + nr;
        control = getGameController();
        animation = new GoblinAnimations();

        observer = Observer.getInstance();
        observer.addEntity(observer.nrEnt++, this);
    }
    public void update()
    {
        event.isMoving = false;
        Player p = observer.player;
        event.inSight = vision.inSight(this, p);

        if(startTimer)
        {
            event.atkTimer++;
        }
        if(event.atkTimer >= stats.getAS())
        {
            startTimer = false;
        }
        if(event.inSight && !event.isAttacking && !event.gotHit && !event.locked)
        {
            moveToPlayer(p);
        }
        if(calcDistance(p) <= Const.tileSize && !event.isAttacking && !startTimer)
        {
            event.atkTimer = 0;
            event.isAttacking = true;
            startTimer = true;
        }
    }
    public void draw(Graphics2D g2d)
    {
        Player p = observer.player;
        int screenX = worldX - p.getWorldX() + Const.centerX;
        int screenY = worldY - p.getWorldY() + Const.centerY;
        //drawDistance(g2d, p);
        animation.draw(g2d, event, screenX, screenY);
        Entity player = observer.player;
        CollisionBox dmgBox = animation.getCurrentAnimation().getBox();
        int hp = player.getStats().getHP();
        if(dmgBox.getShapeInSpace(screenX, screenY) != null)
        {
            //g2d.draw(dmgBox.getShapeInSpace(screenX - Const.tileSize, screenY));
        }
        if(hp > 0 && event.isAttacking && !player.getEvent().gotHit && !player.effects.invincible)
        {
            if(dmgBox != null)
            {
                player.getEvent().gotHit = Hits.attacks(this, dmgBox, player, Const.tileSize);
            }
            if(player.getEvent().gotHit)
            {
                if(player.getEffects().defence)
                {
                    hp -= stats.getAD() / 2;
                }
                else
                {
                    hp -= stats.getAD();
                }
                player.getStats().setHP(hp);
            }
        }
        //hitbox.drawCollision(g2d, screenX, screenY);
        this.stop();
    }
}