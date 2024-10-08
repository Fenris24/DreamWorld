package Game.EntityFactory;

import Game.Animations.*;
import Game.Commands.GameController;
import Game.Events.Observer;
import Game.Fight.SpellEffects;
import Game.Main.KeyHandler;
import Game.Main.MouseHandler;
import Game.Manager.CollisionBox;
import Game.Manager.Const;
import Game.Fight.Hits;
import Game.Strategies.CollisionCheckerStrategy;
import Game.Strategies.EntityCollisionChecker;
import Game.UI.Changes;

import java.awt.*;


public class Player extends Entity
{
    Observer observer;
    PlayerAnimations animation;
    private GameController control;
    KeyHandler key;
    MouseHandler mouse;
    Changes ch;
    boolean startTimer;
    int spellTimer = 0;

    public Player(String name, Stats stats, int nr)
    {
        super(name, stats, nr);
        stats.setGold(100);
        hitbox = new CollisionBox("rectangle", Const.tileSize, Const.playerScale, 18, 0, 30, 20);

        effects = new SpellEffects();
        worldX = Const.tileSize * 245; //starting area x:245 y:250
        worldY = Const.tileSize * 250;
        control = getGameController();
        animation = new PlayerAnimations();
        mouse = MouseHandler.getInstance();
        key = KeyHandler.getInstance();
        observer = Observer.getInstance();
        observer.addPlayer(this);
    }

    public void update()
    {
        if(stats.getHP() <= 0)
        {
            System.exit(0);
        }
        effects.updateEffect();
        event.inGameMenu = false;
        event.comboTimer++;
        if(startTimer)
        {
            event.atkTimer++;
        }
        if(event.atkTimer >= stats.getAS())
        {
            startTimer = false;
        }
        if(event.comboTimer >= stats.getAS() + 30)
        {
            event.comboCounter1 = 0;
            event.comboCounter2 = 0;
            event.comboTimer = 0;
        }

        event.quadrant = findQuadrant();
        event.orientation = (event.quadrant + 1) % 2 + 1;

        if(mouse.rightClick)
        {
            event.abilityMenu = true;
        }
        else
        {
            event.abilityMenu = false;
        }
        if(mouse.leftClick && !startTimer)//left click  normal attacks
        {
            event.isAttacking = true;
            startTimer = true;
            event.atkTimer = 0;
        }
        if(key.shift_On)//press shift to run
        {
            if(event.orientation == 1 && !key.left_On || event.orientation == 2 && !key.right_On)
            {
                stats.setMS(Const.fastSpeed + stats.getBuffMS());
                event.isRunning = true;
            }
        }
        if(!event.isAttacking && !event.gotHit)
        {
            if(key.up_On && !key.down_On)//key to move up
            {
                control.moveUp(this);
            }
            if(key.down_On && !key.up_On)//key to move down
            {
                control.moveDown(this);
            }
            if(key.left_On && !key.right_On)//key to move left
            {
                control.moveLeft(this);
            }
            if(key.right_On && !key.left_On)//key to move right
            {
                control.moveRight(this);
            }
        }
        if(effects.curing)
        {
            if(stats.getHP() + 1 <= stats.getTrueHP())
            {
                stats.setHP(stats.getHP() + 1);
            }
        }
        stats.setMS(Const.normalSpeed + stats.getBuffMS());
    }
    public void draw(Graphics2D g2d)
    {
        animation.draw(g2d, event);
        //hitbox.drawCollision(g2d, Const.centerX, Const.centerY);
        actionDamage(g2d);
        spellDamage(g2d);
        this.stop();
    }

    public void actionDamage(Graphics2D g2d)
    {
        for(int i = 0; i < 20; ++i)
        {
            Entity enemy = observer.entity.get(i);
            if(observer.currentLvl == 3)
            {
                enemy = observer.boss;
            }
            CollisionBox dmgBox = animation.getActionAnimation().getBox();
            int hp = enemy.getStats().getHP();
            if(hp > 0 && event.isAttacking && !enemy.getEvent().gotHit)
            {
                if(dmgBox.getShapeInSpace(Const.centerX, Const.centerY) != null)
                {
                    //g2d.draw(dmgBox.getShapeInSpace(Const.centerX, Const.centerY));
                }
                CollisionCheckerStrategy entityCollision = new EntityCollisionChecker();
                if(!dmgBox.getName().equals("null") && dmgBox != null)
                {
                    enemy.getEvent().gotHit = entityCollision.checkCollision(dmgBox, Const.centerX, Const.centerY, enemy.getHitBox(), enemy.getWorldX() - worldX + Const.centerX, enemy.getWorldY() - worldY + Const.centerY);
                }
                enemy.getEvent().gotHit = Hits.attacks(this, dmgBox, enemy, 0);
                if(enemy.getEvent().gotHit)
                {
                    enemy.getEvent().getPoisoned = effects.givePoison;
                    hp -= stats.getAD();
                    enemy.getStats().setHP(hp);
                    if(effects.lifeSteal)
                    {
                        if(stats.getHP() + stats.getAD() > stats.getTrueHP())
                        {
                            stats.setHP(stats.getTrueHP());
                        }
                        else
                        {
                            stats.setHP(stats.getHP() + stats.getAD());
                        }
                    }
                    if(hp <= 0)
                    {
                        enemy.getEvent().isDead = true;
                        observer.nrEnt--;
                    }
                }
            }
            if(enemy.getEvent().getPoisoned)
            {
                if(enemy.getEvent().poisonTimer % 90 == 0)
                {
                    hp -= 2;
                    enemy.getStats().setHP(hp);
                }
                enemy.getEvent().poisonTimer++;
                if(hp <= 0)
                {
                    enemy.getEvent().isDead = true;
                }
            }
        }
    }
    public void spellDamage(Graphics2D g2d)
    {
        for(int i = 0; i < 20; ++i)
        {
            Entity enemy = observer.entity.get(i);
            if(observer.currentLvl == 3)
            {
                enemy = observer.boss;
            }
            Animation a = animation.getSpellAnimation();
            int damage = animation.getS()[3];
            int xS, yS;
            xS = animation.getS()[0];
            yS = animation.getS()[1];
            int damageFrame = animation.getS()[2];
            CollisionBox dmgBox = null;
            if(a != null)
            {
                 dmgBox = a.getBox();
            }
            int hp = enemy.getStats().getHP();
            if(event.isCasting)
            {
                spellTimer++;
            }
            else
            {
                spellTimer = 0;
            }
            if(hp > 0 && event.isCasting)
            {
                if(dmgBox != null && dmgBox.getShapeInSpace(xS, yS) != null)
                {
                    //g2d.draw(dmgBox.getShapeInSpace(xS, yS));
                }
                CollisionCheckerStrategy entityCollision = new EntityCollisionChecker();
                if(!dmgBox.getName().equals("null"))
                {
                    enemy.getEvent().gotHit = entityCollision.checkCollision(dmgBox, xS, yS, enemy.getHitBox(), enemy.getWorldX() - worldX + Const.centerX, enemy.getWorldY() - worldY + Const.centerY);
                }
                if(enemy.getEvent().gotHit && spellTimer % damageFrame == 0)
                {
                    enemy.getEvent().locked = effects.castLock;
                    hp -= damage;
                    enemy.getStats().setHP(hp);
                    enemy.getEvent().gotHit = false;
                    if(hp <= 0)
                    {
                        enemy.getEvent().isDead = true;
                        observer.nrEnt--;
                    }
                }
            }
            if(enemy.getEvent().locked && !effects.castLock)
            {
                enemy.getEvent().locked = false;
            }
            if(enemy.getEvent().getPoisoned && !effects.givePoison)
            {
                enemy.getEvent().getPoisoned = false;
                enemy.getEvent().poisonTimer = 0;
            }
        }
    }
    public int findQuadrant()
    {
        if((mouse.mouseX - Const.tileSize / 2 > Const.screenWidth / 2) && (mouse.mouseY - Const.tileSize / 2 < Const.screenHeight))
        {
            return 1;
        }
        else if((mouse.mouseX - Const.tileSize / 2 < Const.screenWidth / 2) && (mouse.mouseY - Const.tileSize / 2 < Const.screenHeight))
        {
            return 2;
        }
        else if((mouse.mouseX - Const.tileSize / 2 < Const.screenWidth / 2) && (mouse.mouseY - Const.tileSize / 2 > Const.screenHeight))
        {
            return 3;
        }
        else
        {
            return 4;
        }
    }

    public PlayerAnimations getPlayerAnimation()
    {
        return animation;
    }
}