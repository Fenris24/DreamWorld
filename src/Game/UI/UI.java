package Game.UI;

import Game.Animations.Animation;
import Game.Manager.AssetManager;
import Game.EntityFactory.Player;
import Game.Events.Event;
import Game.Events.Observer;
import Game.Manager.Const;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI
{
    AssetManager a;
    BufferedImage []hpBar;
    BufferedImage []mpBar;
    Player p;
    Event e;
    public UI()
    {
        p = Observer.getInstance().player;
        e = p.getEvent();
        a = AssetManager.getInstance();
        hpBar = new BufferedImage[4];
        mpBar = new BufferedImage[6];
        for(int i = 0; i < 4; ++i)
        {
            hpBar[i] = a.getBar("Health_Bar")[i].getImage();
        }
        for(int i = 0; i < 6; ++i)
        {
            mpBar[i] = a.getBar("Mana_Bar")[i].getImage();
        }
    }

    public void draw(Graphics2D g2d)
    {
        int i = 0;
        int hp = p.getStats().getTrueHP();
        int mp = p.getStats().getTrueMP();
        g2d.drawImage(hpBar[0], Const.tileSize / 2, -Const.tileSize / 2, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
        for(; i < hp; ++i)
        {
            g2d.drawImage(hpBar[1], Const.tileSize / 2 + i * Const.barScale + 57, -Const.tileSize / 2, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
            if(i < p.getStats().getHP())
            {
                g2d.drawImage(hpBar[3], Const.tileSize / 2 + i * Const.barScale + 57, -Const.tileSize / 2, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
            }
        }
        g2d.drawImage(hpBar[2], Const.tileSize / 2 + i * Const.barScale + 57, -Const.tileSize / 2, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);

        g2d.drawImage(mpBar[0], Const.tileSize * 2 - Const.tileSize / 4 - 6, -Const.tileSize / 4, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
        if(p.getStats().getMP() > 0)
        {
            g2d.drawImage(mpBar[3], Const.tileSize * 2 - Const.tileSize / 4 - 3, -Const.tileSize / 4, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
        }
        for(i = 1; i < mp - 1; ++i)
        {
            g2d.drawImage(mpBar[1], Const.tileSize * 2 - Const.tileSize / 4 + (i - 1) * 3, -Const.tileSize / 4, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
            if(i < p.getStats().getMP())
            {
                g2d.drawImage(mpBar[4], Const.tileSize * 2 - Const.tileSize / 4 + (i - 1) * 3, -Const.tileSize / 4, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
            }
        }
        g2d.drawImage(mpBar[2], Const.tileSize * 2 - Const.tileSize / 4 + (i - 1) * 3, -Const.tileSize / 4, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
        if(i + 1 == p.getStats().getMP())
        {
            g2d.drawImage(mpBar[5], Const.tileSize * 2 - Const.tileSize / 4 + (i - 1) * 3, -Const.tileSize / 4, Const.tileSize * Const.barScale, Const.tileSize * Const.barScale, null);
        }
    }
}