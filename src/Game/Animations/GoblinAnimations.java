package Game.Animations;

import Game.EntityFactory.Player;
import Game.Events.Event;
import Game.Events.Observer;
import Game.Manager.AssetManager;
import Game.Manager.Const;

import java.awt.*;
import java.util.Random;

public class GoblinAnimations
{
    Animation currentAnimation;
    Animation idle;
    Animation getHit;
    Animation run;
    Animation attack;
    Animation death;
    int deathRepeat = 10;
    Animation []vfx1;
    Animation []vfx2;
    Animation locked;
    Animation poison;
    int lockRep = 5;
    Random random = new Random();
    int rVfx = random.nextInt(2);
    boolean play;
    public GoblinAnimations()
    {
        AssetManager assets = AssetManager.getInstance();
        idle = new Animation(assets.getEntity("Goblin_Idle"), 4, Const.tileSize, Const.tileSize, Const.goblinScale);
        getHit = new Animation(assets.getEntity("Goblin_Hit"), 4, Const.tileSize, Const.tileSize, Const.goblinScale);
        run = new Animation(assets.getEntity("Goblin_Run"), 8, Const.tileSize, Const.tileSize, Const.goblinScale);
        attack = new Animation(assets.getEntity("Goblin_Attack"), 8, Const.tileSize * 2, Const.tileSize, Const.goblinScale);
        death = new Animation(assets.getEntity("Goblin_Death"), 4, Const.tileSize, Const.tileSize, Const.goblinScale);
        vfx1 = new Animation[2];
        vfx1[0] = new Animation(assets.getVfx("Hit_1"), 4, Const.tileSize, Const.tileSize, 2);
        vfx1[1] = new Animation(assets.getVfx("Hit_2"), 4, Const.tileSize, Const.tileSize, 2);

        vfx2 = new Animation[4];
        vfx2[0] = new Animation(assets.getVfx("Hit_51"), 4, Const.tileSize * 2, Const.tileSize * 2, 2);
        vfx2[1] = new Animation(assets.getVfx("Hit_52"), 4, Const.tileSize * 2, Const.tileSize * 2, 2);
        vfx2[2] = new Animation(assets.getVfx("Hit_53"), 4, Const.tileSize * 2, Const.tileSize * 2, 2);
        vfx2[3] = new Animation(assets.getVfx("Hit_54"), 4, Const.tileSize * 2, Const.tileSize * 2, 2);


        locked = new Animation(assets.getSpell("Light_Lock"), 28, Const.tileSize * 2, Const.tileSize * 2, 2);
        poison = new Animation(assets.getSpell("Poison_Hit"), 5, Const.tileSize, Const.tileSize, 2);
    }
    public void draw(Graphics2D g2d, Event e, int pozX, int pozY)
    {
        Observer o = Observer.getInstance();
        if(e.poisonTimer % 60 == 0 && e.getPoisoned)
        {
            e.gotHit = true;
        }
        if(e.isDead)
        {
            if(death.draw(g2d, pozX, pozY, true, deathRepeat, e.orientation))
            {
                deathRepeat = -1;
            }
        }
        else if(e.gotHit)
        {
            currentAnimation = getHit;
            if(!e.locked)
            {
                e.gotHit = !currentAnimation.draw(g2d, pozX, pozY, true, 8, e.orientation);
            }
            else
            {
                e.gotHit = false;
            }
            if(o.player.getEvent().comboSelect == 2)
            {
                vfx2[o.player.getEvent().comboCounter2].draw(g2d, pozX - Const.tileSize, pozY - Const.tileSize / 2, play, 4, e.orientation);
            }
            else if(!e.getPoisoned && !e.locked && vfx1[rVfx].draw(g2d, pozX, pozY, play, 4, e.orientation))
            {
                rVfx = random.nextInt(2);
                play = false;
            }
            if(e.getPoisoned)
            {
                poison.draw(g2d, pozX, pozY, play, 6, e.orientation);
            }
            e.inSight = true;
        }
        else if(!e.isMoving && !e.isAttacking)
        {
            play = true;
            currentAnimation = idle;
            currentAnimation.draw(g2d, pozX, pozY, true, 10, e.orientation);
        }
        else if(e.isAttacking)
        {
            play = true;
            currentAnimation = attack;
            e.isAttacking = !currentAnimation.draw(g2d, pozX - Const.tileSize, pozY, true, 6, e.orientation);
        }
        else if(e.inSight)
        {
            play = true;
            currentAnimation = run;
            if(!e.locked)
            {
                currentAnimation.draw(g2d, pozX, pozY, true, 10, e.orientation);
            }
        }
        if(e.locked)
        {
            if(locked.draw(g2d, pozX - Const.tileSize, pozY - Const.tileSize, true, lockRep, e.orientation))
            {
                lockRep = -1;
            }
        }
        else
        {
            lockRep = 5;
        }
    }
    public Animation getCurrentAnimation()
    {
        return currentAnimation;
    }
}
