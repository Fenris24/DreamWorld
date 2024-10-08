package Game.Fight;

import Game.Animations.Animation;
import Game.Main.MouseHandler;

public class Spell
{
    String name;
    String effect;
    int pX;
    int pY;
    int ogMov;
    int mov;
    int dist;
    int dmg;
    int rep;
    int timer;
    int manaCost;
    boolean hasProjectile;
    boolean hasEffect;
    Animation anim;
    Spell projectile;
    MouseHandler m = MouseHandler.getInstance();
    public Spell(String name, String effect, int pX, int pY, int mov, int dist, int dmg, int rep, Animation anim, int manaCost)
    {
        this.name = name;
        this.effect = effect;
        this.pX = pX;
        this.pY = pY;
        this.mov = mov;
        ogMov = mov;
        this.dist = dist;
        this.dmg = dmg;
        this.rep = rep;
        this.anim = anim;
        hasProjectile = false;
        if(!effect.equals("null"))
        {
            hasEffect = true;
        }
        this.manaCost = manaCost;
    }
    public Spell(String name, String effect, int pX, int pY, int rep, int timer, Animation anim, int manaCost)
    {
        this.name = name;
        this.effect = effect;
        this.pX = pX;
        this.pY = pY;
        this.rep = rep;
        this.timer = timer;
        this.anim = anim;
        hasProjectile = false;
        if(!effect.equals("null"))
        {
            hasEffect = true;
        }
        this.manaCost = manaCost;
    }
    public Spell(String name, int pX, int pY, int mov, int dist, int dmg, int rep, Animation anim, Spell projectile, int manaCost)
    {
        this.name = name;
        this.pX = pX;
        this.pY = pY;
        this.mov = mov;
        ogMov = mov;
        this.dist = dist;
        this.dmg = dmg;
        this.rep = rep;
        this.anim = anim;
        hasProjectile = true;
        this.projectile = projectile;
        hasEffect = false;
        this.manaCost = manaCost;
    }

    public String getName() {return name;}
    public int getpX()
    {
        if(dist == -1 || dist == -2)
        {
            return pX + m.mouseX;
        }
        return pX;
    }
    public int getpY()
    {
        if(dist == -1 || dist == -2)
        {
            return pY + m.mouseY;
        }
        return pY;
    }
    public int getMov() {return mov;}
    public void setMov(int val) {mov = val;}
    public int getOgMov() {return ogMov;}
    public int getDist() {return dist;}
    public int getDmg() {return dmg;}
    public int getTimer() {return timer;}
    public int getManaCost() {return manaCost;}
    public boolean hasEffect() {return hasEffect;}
    public String getEffect() {return effect;}
    public int getRep() {return rep;}
    public Animation getAnim() {return anim;}
    public boolean projectile(){return hasProjectile;}
    public Spell getProjectile() {return projectile;}
}
