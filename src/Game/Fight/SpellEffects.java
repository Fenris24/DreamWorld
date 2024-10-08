package Game.Fight;

import Game.Events.Observer;
import Game.EntityFactory.Player;
import Game.Main.MouseHandler;
import Game.Manager.Const;
import Game.Strategies.CollisionChecker;
import Game.Strategies.CollisionCheckerStrategy;
import Game.Strategies.EntityCollisionChecker;

public class SpellEffects
{
    int lsTimer;
    int invTimer;
    int cureTimer;
    int telTime;
    int lockTimer;
    int poisonTimer;
    int defTimer;
    int powTimer;
    int unTimer;
    public boolean lifeSteal;
    public boolean invincible;
    public boolean curing;
    public boolean teleporting;
    public boolean castLock;
    public boolean givePoison;
    public boolean defence;
    public boolean power;
    public boolean unleashed;

    public SpellEffects()
    {
        lsTimer = 0;
    }

    public void updateEffect()
    {
        lsTimer = update(lsTimer);
        lifeSteal = lsTimer != 0;

        invTimer = update(invTimer);
        invincible = invTimer != 0;

        cureTimer = update(cureTimer);
        curing = cureTimer != 0;

        lockTimer = update(lockTimer);
        castLock = lockTimer != 0;

        poisonTimer = update(poisonTimer);
        givePoison = poisonTimer != 0;

        defTimer = update(defTimer);
        defence = defTimer != 0;

        powTimer = update(powTimer);
        power = powTimer != 0;
        if(!power)
        {
            Observer o = Observer.getInstance();
            o.player.getStats().setAD(o.player.getStats().getTrueAD());
            o.player.getStats().setBuffMS(0);
        }

        unTimer = update(unTimer);
        unleashed = unTimer != 0;
        if(!unleashed)
        {
            Observer o = Observer.getInstance();
            o.player.getStats().setAD(o.player.getStats().getTrueAD());
            o.player.getEvent().comboSelect = 1;
            o.player.getEvent().comboCounter2 = 0;
        }

        telTime = update(telTime);
        teleporting = telTime == 1;

        if(teleporting)
        {
            Observer o = Observer.getInstance();
            MouseHandler m = MouseHandler.getInstance();
            CollisionChecker collisionChecker;
            CollisionCheckerStrategy entityCollision = new EntityCollisionChecker();
            collisionChecker = new CollisionChecker(entityCollision);
            int x = o.player.getWorldX();
            int y = o.player.getWorldY();
            o.player.setWorldX(m.mouseX + x - Const.centerX - Const.tileSize * 3 / 2);
            o.player.setWorldY(m.mouseY + y - Const.centerY - Const.tileSize * 2);
            if(collisionChecker.checkCollision(o.player, 2))
            {
                o.player.setWorldX(x);
                o.player.setWorldY(y);
            }
            telTime = 0;
        }
    }
    public int update(int timer)
    {
        if(timer > 0)
        {
            timer--;
            return timer;
        }
        return 0;
    }
    public void activateEffect(String effect, int timer)
    {
        if(effect.equals("life_steal"))
        {
            lifeSteal = true;
            lsTimer = timer;
        }
        if(effect.equals("invincible"))
        {
            invincible = true;
            invTimer = timer;
        }
        if(effect.equals("curing"))
        {
            curing = true;
            cureTimer = timer;
        }
        if(effect.equals("teleporting"))
        {
            telTime = timer * 6;
        }
        if(effect.equals("lock"))
        {
            castLock = true;
            lockTimer = timer * 30;
        }
        if(effect.equals("poison"))
        {
            givePoison = true;
            poisonTimer = timer;
        }
        if(effect.equals("defence"))
        {
            defence = true;
            defTimer = timer;
        }
        if(effect.equals("power"))
        {
            Observer o = Observer.getInstance();
            o.player.getStats().setAD(o.player.getStats().getAD() * 3 / 2);
            o.player.getStats().setBuffMS(4);
            power = true;
            powTimer = timer;
        }
        if(effect.equals("unleashed"))
        {
            Observer o = Observer.getInstance();
            o.player.getStats().setAD(o.player.getStats().getAD() * 2);
            o.player.getEvent().comboSelect = 2;
            unleashed = true;
            unTimer = timer;
        }
    }
}
