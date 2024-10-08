package Game.Animations;

import Game.EntityFactory.Player;
import Game.Events.Event;
import Game.Events.Observer;
import Game.Fight.Spell;
import Game.Fight.SpellMenu;
import Game.Main.MouseHandler;
import Game.Manager.AssetManager;
import Game.Manager.Const;

import java.awt.*;
import java.util.Random;

public class PlayerAnimations
{
    Animation spellAnimation;
    int xS;
    int yS;
    int repS;
    int dmgS;
    int orS;
    Animation actionAnimation;
    int xA;
    int yA;
    int repA;
    int dmgA;
    int orA;
    Animation idle;
    Animation walk;
    Animation run;
    Animation hurt;
    Animation mouseQ1, mouseQ2, mouseQ3, mouseQ4;
    Animation mouseMenu;
    int mouseRepeat = 4;
    int old_state;
    public int current_state;
    int pozMX = -1;
    int pozMY = -1;
    Animation []combo1;
    Animation []combo2;
    int orientation;
    int movement;
    int dx, dy;
    Spell []spell;
    int []rep;
    Animation divineSmite;
    Animation seaDragon;
    Animation fireDance;
    Animation heavenRain;
    Animation invincible;
    Animation healing;
    Animation defence;
    Animation power;
    Animation rainBullets;
    Animation []vfx;
    Random random = new Random();
    int rVfx = random.nextInt(2);
    boolean play;
    int screenX;
    int screenY;
    boolean oneRep;
    boolean animStart = true;
    boolean manaUsed = false;

    public PlayerAnimations()
    {
        AssetManager assets = AssetManager.getInstance();
        idle = new Animation(assets.getEntity("Idle_Sword"), 4, Const.tileSize, Const.tileSize, Const.playerScale);
        walk = new Animation(assets.getEntity("Walk_Sword"), 6, Const.tileSize, Const.tileSize, Const.playerScale);
        run = new Animation(assets.getEntity("Run_Sword"), 6, Const.tileSize, Const.tileSize, Const.playerScale);
        hurt = new Animation(assets.getEntity("Hurt_Sword"), 4, Const.tileSize, Const.tileSize, Const.playerScale);
        mouseMenu = new Animation(assets.getMenu("Mouse_Menu"), 4, Const.tileSize * 2, Const.tileSize * 2, Const.mouseMenuScale);
        mouseQ1 = new Animation(assets.getMenu("Mouse_Q1"), 4, Const.tileSize * 2, Const.tileSize * 2, Const.mouseMenuScale);
        mouseQ2 = new Animation(assets.getMenu("Mouse_Q2"), 4, Const.tileSize * 2, Const.tileSize * 2, Const.mouseMenuScale);
        mouseQ3 = new Animation(assets.getMenu("Mouse_Q3"), 4, Const.tileSize * 2, Const.tileSize * 2, Const.mouseMenuScale);
        mouseQ4 = new Animation(assets.getMenu("Mouse_Q4"), 4, Const.tileSize * 2, Const.tileSize * 2, Const.mouseMenuScale);
        combo1 = new Animation[3];
        combo1[0] = new Animation(assets.getEntity("Combo1_Attack1_Sword"), 5, Const.tileSize, Const.tileSize, Const.playerScale);
        combo1[1] = new Animation(assets.getEntity("Combo1_Attack2_Sword"), 6, Const.tileSize, Const.tileSize, Const.playerScale);
        combo1[2] = new Animation(assets.getEntity("Combo1_Attack3_Sword"), 6, Const.tileSize, Const.tileSize, Const.playerScale);

        combo2 = new Animation[4];
        combo2[0] = new Animation(assets.getEntity("Combo2_Attack1_Sword"), 6, Const.tileSize, Const.tileSize, Const.playerScale);
        combo2[1] = new Animation(assets.getEntity("Combo2_Attack2_Sword"), 4, Const.tileSize, Const.tileSize, Const.playerScale);
        combo2[2] = new Animation(assets.getEntity("Combo2_Attack3_Sword"), 4, Const.tileSize, Const.tileSize, Const.playerScale);
        combo2[3] = new Animation(assets.getEntity("Combo2_Attack4_Sword"), 4, Const.tileSize, Const.tileSize, Const.playerScale);
        divineSmite = new Animation(assets.getSpell("Divine_Smite"), 11, Const.tileSize * 4, Const.tileSize * 4, 3);
//        seaDragon = new Animation(assets.getSpell("Sea_Dragon"), 19, Const.tileSize * 4, Const.tileSize * 4, 3);
//        fireDance = new Animation(assets.getSpell("Fire_Dance"), 15, Const.tileSize * 4, Const.tileSize * 4, 3);
//        heavenRain = new Animation(assets.getSpell("Heaven_Rain"), 20, Const.tileSize * 4, Const.tileSize * 4, 2);
//        rainBullets = new Animation(assets.getSpell("Rain_Bullets"), 19, Const.tileSize, Const.tileSize, 3);
        invincible = new Animation(assets.getSpell("Invincible"), 60, Const.tileSize * 2, Const.tileSize * 2, 2);
        healing = new Animation(assets.getSpell("Healing"), 12, Const.tileSize * 2, Const.tileSize * 2, 2);
        defence = new Animation(assets.getSpell("Aegis"), 7, Const.tileSize, Const.tileSize, 3);
        power = new Animation(assets.getSpell("Full_Might"), 7, Const.tileSize * 2, Const.tileSize * 2, 2);

        vfx = new Animation[2];
        vfx[0] = new Animation(assets.getVfx("Hit_3"), 4, Const.tileSize, Const.tileSize, 2);
        vfx[1] = new Animation(assets.getVfx("Hit_4"), 4, Const.tileSize, Const.tileSize, 2);
        spell = new Spell[4];
        rep = new int[4];
    }
    public void draw(Graphics2D g2d, Event e)
    {
        Observer o = Observer.getInstance();
        if(o.player.getEffects().invincible)
        {
            invincible.draw(g2d, Const.centerX - Const.tileSize / 2, Const.centerY, true, 2, e.orientation);
        }
        if(e.gotHit)
        {
            actionAnimation = hurt;
            if(!o.player.getEffects().invincible)
            {
                e.gotHit = !actionAnimation.draw(g2d, Const.centerX, Const.centerY, true, 8, e.orientation);
            }
            else
            {
                e.gotHit = false;
            }
            if(o.player.getEffects().defence)
            {
                defence.draw(g2d, Const.centerX, Const.centerY + Const.tileSize / 2, true, 6, e.orientation);
            }
            if(!o.player.getEffects().invincible && vfx[rVfx].draw(g2d, Const.centerX + Const.tileSize / 2, Const.centerY + Const.tileSize, play, 4, e.orientation))
            {
                rVfx = random.nextInt(2);
                play = false;
            }
        }
        else if(!e.isMoving && !e.isAttacking)
        {
            play = true;
            actionAnimation = idle;
            actionAnimation.draw(g2d, Const.centerX, Const.centerY, true, 12, e.orientation);
        }
        else if(e.isAttacking)
        {
            play = true;
            e.comboTimer = 0;
            if(e.comboSelect == 1)
            {
                actionAnimation = combo1[e.comboCounter1];
            }
            else
            {
                actionAnimation = combo2[e.comboCounter2];
            }
            if(o.player.getEffects().lifeSteal)
            {
                spellAnimation.draw(g2d, Const.centerX, Const.centerY + Const.tileSize / 2, true, 6, orientation);
            }
            if(actionAnimation.draw(g2d, Const.centerX, Const.centerY, !e.isMoving, 5, e.orientation))
            {
                if(e.comboSelect == 1)
                {
                    e.comboCounter1++;
                }
                else
                {
                    e.comboCounter2++;
                }
                e.isAttacking = false;
            }
        }
        else
        {
            play = true;
            if(e.isRunning)
            {
                actionAnimation = run;
                actionAnimation.draw(g2d, Const.centerX, Const.centerY, true, 4, e.orientation);
            }
            else
            {
                actionAnimation = walk;
                actionAnimation.draw(g2d, Const.centerX, Const.centerY, true, 6, e.orientation);
            }
        }
        if(e.comboCounter1 == 3)
        {
            e.comboCounter1 = 0;
        }
        if(e.comboCounter2 == 4)
        {
            e.comboCounter2 = 0;
        }
        if(o.player.getEffects().curing)
        {
            healing.draw(g2d, Const.centerX - Const.tileSize / 2, Const.centerY, true, 4, e.orientation);
        }
        if(o.player.getEffects().power)
        {
            power.draw(g2d, Const.centerX - Const.tileSize / 2, Const.centerY, true, 6, e.orientation);
        }
    }
    public void drawMouse(Graphics2D g2d, Event e)
    {
        MouseHandler mouse = MouseHandler.getInstance();
        Observer o = Observer.getInstance();
        SpellMenu s = SpellMenu.getInstance();
        s.update();
        spell[0] = s.getSlot()[0];
        spell[1] = s.getSlot()[1];
        spell[2] = s.getSlot()[2];
        spell[3] = s.getSlot()[3];
        if(!e.isCasting && e.abilityMenu && !e.gotHit && !e.isAttacking)
        {
            oneRep = true;
            for(int i = 0; i < 4; ++i)
            {
                if(spell[i] != null)
                {
                    rep[i] = spell[i].getRep();
                }
            }
            movement = 0;
            dx = o.player.getWorldX();
            dy = o.player.getWorldY();
            orientation = e.orientation;
            play = true;
            old_state = current_state;
            current_state = getMouseQ(pozMX + Const.tileSize * 2, pozMY + Const.tileSize * 2);
            if(old_state != current_state)
            {
                mouseRepeat = 4;
            }
            switch(current_state)
            {
                case 1:
                    spellAnimation = mouseQ1;
                    break;
                case 2:
                    spellAnimation = mouseQ2;
                    break;
                case 3:
                    spellAnimation = mouseQ3;
                    break;
                case 4:
                    spellAnimation = mouseQ4;
                    break;
                case 0:
                    spellAnimation = mouseMenu;
                    break;
            }
            setAnimationS(pozMX, pozMY, mouseRepeat, 0, 1);
            if(spellAnimation.draw(g2d, pozMX, pozMY, true, mouseRepeat, 1))
            {
                mouseRepeat = -1;
            }
        }
        else
        {
            e.abilityMenu = false;
            pozMX = mouse.mouseX - Const.tileSize * 2;
            pozMY = mouse.mouseY - Const.tileSize * 2;
            mouseRepeat = 4;
            screenX = dx - o.player.getWorldX();
            screenY = dy - o.player.getWorldY();
            switch(current_state)
            {
                case 1:
                    if(spell[0] != null && (o.player.getStats().getMP() >= spell[0].getManaCost() || manaUsed))
                    {
                        if(!manaUsed)
                        {
                            o.player.getStats().setMP(o.player.getStats().getMP() - spell[0].getManaCost());
                            manaUsed = true;
                        }
                        if(spell[0].getDist() == -2 && spell[0].hasEffect() && oneRep)
                        {
                            o.player.getEffects().activateEffect(spell[0].getEffect(), spell[0].getRep());
                            oneRep = false;
                        }
                        if(spell[0].getMov() != 0)
                        {
                            screenX += (orientation == 1) ? Const.centerX + Const.tileSize / 2 + movement : Const.tileSize * 2 - movement;
                            if(movement < spell[0].getDist())
                            {
                                movement += spell[0].getMov();
                            }
                        }
                        spellAnimation = spell[0].getAnim();
                        setAnimationS(screenX + spell[0].getpX(), screenY + spell[0].getpY(), rep[0], spell[0].getDmg(), orientation);
                        if(spellAnimation.draw(g2d, screenX + spell[0].getpX(), screenY + spell[0].getpY(), true, rep[0], orientation))
                        {
                            manaUsed = false;
                            if(spell[0].getDist() != -2 && spell[0].hasEffect())
                            {
                                o.player.getEffects().activateEffect(spell[0].getEffect(), spell[0].getTimer());
                            }
                            if(spell[0].projectile())
                            {
                                rep[0] = -1;
                                spellAnimation = spell[0].getProjectile().getAnim();
                                int movX = spell[0].getProjectile().getpX();
                                int movY = spell[0].getProjectile().getpY();
                                int speed = spell[0].getProjectile().getMov();
                                int ogSpeed = spell[0].getProjectile().getOgMov();
                                int dist = spell[0].getProjectile().getDist();
                                if(dist > 0 && speed < dist)
                                {
                                    movX += orientation == 1 ? speed : -speed;
                                    spell[0].getProjectile().setMov(speed + ogSpeed);
                                }
                                setAnimationS(movX, movY, spell[0].getProjectile().getRep(), spell[0].getDmg(), orientation);
                                if((dist - speed) > ogSpeed || dist == -1)
                                {
                                    if(spellAnimation.draw(g2d, movX, movY, true, spell[0].getProjectile().getRep(), orientation) && dist == -1)
                                    {
                                        rep[0] = spell[0].getRep();
                                        current_state = 0;
                                        e.isCasting = false;
                                    }
                                }
                                else
                                {
                                    spell[0].getProjectile().setMov(ogSpeed);
                                    rep[0] = spell[0].getRep();
                                    current_state = 0;
                                    e.isCasting = false;
                                }
                            }
                            else
                            {
                                current_state = 0;
                                e.isCasting = false;
                            }
                        }
                        else
                        {
                            e.isCasting = true;
                        }
                    }
                    else
                    {
                        animStart = false;
                    }
                    break;
                case 2:
                    if(spell[1] != null && (o.player.getStats().getMP() >= spell[1].getManaCost() || manaUsed))
                    {
                        if(!manaUsed)
                        {
                            o.player.getStats().setMP(o.player.getStats().getMP() - spell[1].getManaCost());
                            manaUsed = true;
                        }
                        if(spell[1].getDist() == -2 && spell[1].hasEffect() && oneRep)
                        {
                            o.player.getEffects().activateEffect(spell[1].getEffect(), spell[1].getRep());
                            oneRep = false;
                        }
                        if(spell[1].getMov() != 0)
                        {
                            screenX += (orientation == 1) ? Const.centerX + Const.tileSize / 2 + movement : Const.tileSize * 2 - movement;
                            if(movement < spell[1].getDist())
                            {
                                movement += spell[1].getMov();
                            }
                        }
                        spellAnimation = spell[1].getAnim();
                        setAnimationS(screenX + spell[1].getpX(), screenY + spell[1].getpY(), rep[1], spell[1].getDmg(), orientation);
                        if(spellAnimation.draw(g2d, screenX + spell[1].getpX(), screenY + spell[1].getpY(), true, rep[1], orientation))
                        {
                            manaUsed = false;
                            if(spell[1].getDist() != -2 && spell[1].hasEffect())
                            {
                                o.player.getEffects().activateEffect(spell[1].getEffect(), spell[1].getTimer());
                            }
                            if(spell[1].projectile())
                            {
                                rep[1] = -1;
                                spellAnimation = spell[1].getProjectile().getAnim();
                                int movX = spell[1].getProjectile().getpX();
                                int movY = spell[1].getProjectile().getpY();
                                int speed = spell[1].getProjectile().getMov();
                                int ogSpeed = spell[1].getProjectile().getOgMov();
                                int dist = spell[1].getProjectile().getDist();
                                if(dist > 0 && speed < dist)
                                {
                                    movX += orientation == 1 ? speed : -speed;
                                    spell[1].getProjectile().setMov(speed + ogSpeed);
                                }
                                setAnimationS(movX, movY, spell[1].getProjectile().getRep(), spell[1].getDmg(), orientation);
                                if((dist - speed) > ogSpeed || dist == -1)
                                {
                                    if(spellAnimation.draw(g2d, movX, movY, true, spell[1].getProjectile().getRep(), orientation) && dist == -1)
                                    {
                                        rep[1] = spell[1].getRep();
                                        current_state = 0;
                                        e.isCasting = false;
                                    }
                                }
                                else
                                {
                                    spell[1].getProjectile().setMov(ogSpeed);
                                    rep[1] = spell[1].getRep();
                                    current_state = 0;
                                    e.isCasting = false;
                                }
                            }
                            else
                            {
                                current_state = 0;
                                e.isCasting = false;
                            }
                        }
                        else
                        {
                            e.isCasting = true;
                        }
                    }
                    else
                    {
                        animStart = false;
                    }
                    break;
                case 3:
                    if(spell[2] != null && (o.player.getStats().getMP() >= spell[2].getManaCost() || manaUsed))
                    {
                        if(!manaUsed)
                        {
                            o.player.getStats().setMP(o.player.getStats().getMP() - spell[2].getManaCost());
                            manaUsed = true;
                        }
                        if(spell[2].getDist() == -2 && spell[2].hasEffect() && oneRep)
                        {
                            o.player.getEffects().activateEffect(spell[2].getEffect(), spell[2].getRep());
                            oneRep = false;
                        }
                        if(spell[2].getMov() != 0)
                        {
                            screenX += (orientation == 1) ? Const.centerX + Const.tileSize / 2 + movement : Const.tileSize * 2 - movement;
                            if(movement < spell[2].getDist())
                            {
                                movement += spell[2].getMov();
                            }
                        }
                        spellAnimation = spell[2].getAnim();
                        setAnimationS(screenX + spell[2].getpX(), screenY + spell[2].getpY(), rep[2], spell[2].getDmg(), orientation);
                        if(spellAnimation.draw(g2d, screenX + spell[2].getpX(), screenY + spell[2].getpY(), true, rep[2], orientation))
                        {
                            manaUsed = false;
                            if(spell[2].getDist() != -2 && spell[2].hasEffect())
                            {
                                o.player.getEffects().activateEffect(spell[2].getEffect(), spell[2].getTimer());
                            }
                            if(spell[2].projectile())
                            {
                                rep[2] = -1;
                                spellAnimation = spell[2].getProjectile().getAnim();
                                int movX = spell[2].getProjectile().getpX();
                                int movY = spell[2].getProjectile().getpY();
                                int speed = spell[2].getProjectile().getMov();
                                int ogSpeed = spell[2].getProjectile().getOgMov();
                                int dist = spell[2].getProjectile().getDist();
                                if(dist > 0 && speed < dist)
                                {
                                    movX += orientation == 1 ? speed : -speed;
                                    spell[2].getProjectile().setMov(speed + ogSpeed);
                                }
                                setAnimationS(movX, movY, spell[2].getProjectile().getRep(), spell[2].getDmg(), orientation);
                                if((dist - speed) > ogSpeed || dist == -1)
                                {
                                    if(spellAnimation.draw(g2d, movX, movY, true, spell[2].getProjectile().getRep(), orientation) && dist == -1)
                                    {
                                        rep[2] = spell[2].getRep();
                                        current_state = 0;
                                        e.isCasting = false;
                                    }
                                }
                                else
                                {
                                    spell[2].getProjectile().setMov(ogSpeed);
                                    rep[2] = spell[2].getRep();
                                    current_state = 0;
                                    e.isCasting = false;
                                }
                            }
                            else
                            {
                                current_state = 0;
                                e.isCasting = false;
                            }
                        }
                        else
                        {
                            e.isCasting = true;
                        }
                    }
                    else
                    {
                        animStart = false;
                    }
                    break;
                case 4:
                    if(spell[3] != null && (o.player.getStats().getMP() >= spell[3].getManaCost() || manaUsed))
                    {
                        if(!manaUsed)
                        {
                            o.player.getStats().setMP(o.player.getStats().getMP() - spell[3].getManaCost());
                            manaUsed = true;
                        }
                        if(spell[3].getDist() == -2 && spell[3].hasEffect() && oneRep)
                        {
                            o.player.getEffects().activateEffect(spell[3].getEffect(), spell[3].getRep());
                            oneRep = false;
                        }
                        if(spell[3].getMov() != 0)
                        {
                            screenX += (orientation == 1) ? Const.centerX + Const.tileSize / 2 + movement : Const.tileSize * 2 - movement;
                            if(movement < spell[3].getDist())
                            {
                                movement += spell[3].getMov();
                            }
                        }
                        spellAnimation = spell[3].getAnim();
                        setAnimationS(screenX + spell[3].getpX(), screenY + spell[3].getpY(), spell[3].getRep(), spell[3].getDmg(), orientation);
                        if(spellAnimation.draw(g2d, screenX + spell[3].getpX(), screenY + spell[3].getpY(), true, rep[3], orientation))
                        {
                            manaUsed = false;
                            if(spell[3].getDist() != -2 && spell[3].hasEffect())
                            {
                                o.player.getEffects().activateEffect(spell[3].getEffect(), spell[3].getTimer());
                            }
                            if(spell[3].projectile())
                            {
                                rep[3] = -1;
                                spellAnimation = spell[3].getProjectile().getAnim();
                                int movX = spell[3].getProjectile().getpX();
                                int movY = spell[3].getProjectile().getpY();
                                int speed = spell[3].getProjectile().getMov();
                                int ogSpeed = spell[3].getProjectile().getOgMov();
                                int dist = spell[3].getProjectile().getDist();
                                if(dist > 0 && speed < dist)
                                {
                                    movX += orientation == 1 ? speed : -speed;
                                    spell[3].getProjectile().setMov(speed + ogSpeed);
                                }
                                setAnimationS(movX, movY, spell[3].getProjectile().getRep(), spell[3].getDmg(), orientation);
                                if((dist - speed) > ogSpeed || dist == -1)
                                {
                                    if(spellAnimation.draw(g2d, movX, movY, true, spell[3].getProjectile().getRep(), orientation) && dist == -1)
                                    {
                                        rep[3] = spell[3].getRep();
                                        current_state = 0;
                                        e.isCasting = false;
                                    }
                                }
                                else
                                {
                                    spell[3].getProjectile().setMov(ogSpeed);
                                    rep[3] = spell[3].getRep();
                                    current_state = 0;
                                    e.isCasting = false;
                                }
                            }
                            else
                            {
                                current_state = 0;
                                e.isCasting = false;
                            }
                        }
                        else
                        {
                            e.isCasting = true;
                        }
                    }
                    else
                    {
                        animStart = false;
                    }
                    break;
                default:
                    current_state = 0;
            }
        }
    }
    public int getMouseQ(int x, int y)
    {
        if(x == -1 && y == -1)
        {
            return 0;
        }
        MouseHandler m = MouseHandler.getInstance();
        if(m.mouseX > x && m.mouseY < y)
        {
            return 1;
        }
        else if(m.mouseX < x && m.mouseY < y)
        {
            return 2;
        }
        else if(m.mouseX < x && m.mouseY > y)
        {
            return 3;
        }
        else if(m.mouseX > x && m.mouseY > y)
        {
            return 4;
        }
        return 0;
    }
    public Animation getActionAnimation()
    {
        return actionAnimation;
    }
    public void resetCurrent_state(){ current_state = 0;}
    public Animation getSpellAnimation()
    {
        if(spellAnimation != null)
        {
            return spellAnimation;
        }
        return null;
    }
    public int[] getA(){ return new int[]{xA, yA, repA, dmgA, orA};}
    public int[] getS(){ return new int[]{xS, yS, repS, dmgS, orS};}
    public void setAnimationA(int x, int y, int rep, int dmg, int ori)
    {
        xA = x;
        yA = y;
        repA = rep;
        dmgA = dmg;
        orA = ori;
    }
    public void setAnimationS(int x, int y, int rep, int dmg, int ori)
    {
        xS = x;
        yS = y;
        repS = rep;
        dmgS = dmg;
        orS = ori;
    }
}