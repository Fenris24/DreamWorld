package Game.EntityFactory;

import Game.Animations.BossAnimation;
import Game.Commands.GameController;
import Game.Events.Observer;
import Game.Fight.Hits;
import Game.Manager.CollisionBox;
import Game.Manager.Const;
import Game.Strategies.GoblinVision;
import Game.Strategies.Vision;
import Game.Strategies.VisionStrategy;

import java.awt.*;

public class Boss extends Entity
{
    Observer observer;
    BossAnimation animation;
    private GameController control;
    private Vision vision;
    boolean startTimer;
    public Boss(String name, Stats stats, int nr)
    {
        super(name, stats, nr);
        hitbox = new CollisionBox("rectangle", 191, 4, 60, 50, 120, 90);

        VisionStrategy gobVision = new GoblinVision();
        vision = new Vision(gobVision);
        visionX = 1000;
        visionY = 1000;

        worldX = Const.tileSize * 30;
        worldY = Const.tileSize * 10;
        control = getGameController();
        animation = new BossAnimation();

        observer = Observer.getInstance();
        observer.boss = this;
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
        int screenX = worldX - p.getWorldX() + Const.centerX + Const.tileSize * 5;
        int screenY = worldY - p.getWorldY() + Const.centerY + Const.tileSize * 5;
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
