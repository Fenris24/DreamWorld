package Game.Events;

import Game.Commands.Direction;

public class Event
{
    public boolean vfx;
    public boolean getPoisoned;
    public int poisonTimer;
    public boolean lifeSteal;
    public boolean locked;
    public boolean isMoving;
    public boolean isRunning;
    public boolean isAttacking;
    public boolean isDead;
    public boolean isCasting;
    public boolean abilityMenu;
    public boolean inGameMenu;
    public boolean mainMenu;
    public boolean gotHit;
    public int orientation = 1;
    public int quadrant = 1;
    public int atkTimer = 0;
    public int comboSelect = 1;
    public int comboTimer = 0;
    public int comboCounter1 = 0;
    public int comboCounter2 = 0;
    public boolean inSight;
}
