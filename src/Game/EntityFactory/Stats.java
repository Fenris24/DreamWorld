package Game.EntityFactory;

public class Stats
{
    private int gold;
    private int exp = 0;
    private int expCap = 10;
    private int lvl = 1;
    private int hp; //Health Points
    private int mp; //Mana Points
    private int ms; //Movement Speed
    private int ad; //Attack Damage
    private int as; //Attack Speed
    private int ac; //Ability Cooldown
    private int trueHp;
    private int trueMp;
    private int trueAd;
    private int buffMs;
    public Stats(int ...stats)
    {
        trueHp = stats[0];
        trueMp = stats[1];
        buffMs = 0;
        trueAd = stats[3];
        hp = stats[0];
        mp = stats[1];
        ms = stats[2];
        ad = stats[3];
        as = stats[4];
        ac = stats[5];
    }
    public int getGold() { return gold;}
    public void setGold(int gold) { this.gold = gold;}

    public int getEXP() { return exp;}
    public void setEXP(int exp) { this.exp = exp;}

    public int getEXPCap() { return expCap;}
    public void setEXPCap(int expCap) { this.expCap = expCap;}

    public int getLVL() { return lvl;}
    public void setLVL(int lvl) { this.lvl = lvl;}

    public int getHP() { return hp;}
    public void setHP(int hp) { this.hp = hp;}

    public int getMP() { return mp;}
    public void setMP(int mp) { this.mp = mp;}

    public int getMS() { return ms;}
    public void setMS(int ms) { this.ms = ms; }

    public void setBuffMS(int trueMS) {this.buffMs = trueMS;}

    public int getBuffMS() {return buffMs;}

    public int getTrueAD() {return trueAd;}

    public void setTrueAD(int trueAd) {this.trueAd = trueAd;}

    public int getAD() { return ad;}
    public void setAD(int ad) { this.ad = ad;}

    public int getAS() { return as; }
    public void setAS(int as) { this.as = as;}

    public int getAC() { return ac; }
    public void setAC(int ac) { this.ac = ac;}

    public int getTrueHP() { return trueHp;}
    public void setTrueHP(int trueHp) { this.trueHp = trueHp;}

    public int getTrueMP() { return trueMp;}
    public void setTrueMP(int trueMp) { this.trueMp = trueMp;}
}
